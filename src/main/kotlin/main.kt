import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.apache.ignite.Ignite
import org.apache.ignite.IgniteCache
import org.apache.ignite.Ignition
import org.apache.ignite.binary.BinaryObject
import org.apache.ignite.cache.query.ScanQuery
import org.apache.ignite.configuration.CacheConfiguration
import org.apache.ignite.configuration.ClientConfiguration
import org.apache.ignite.lang.IgniteRunnable
import org.apache.ignite.resources.IgniteInstanceResource
import java.sql.DriverManager
import java.util.*


class BinConfig : CacheConfiguration<Int, BinaryObject>()

fun main() {
    // -------------------------- JDBC ----------------------
    Class.forName("org.apache.ignite.IgniteJdbcThinDriver")
    val con = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1")
    val rs1 = con.createStatement().executeQuery("select 10")
    rs1.next()
    println(rs1.getInt(1))

    con.createStatement().execute("drop table if exists region")

    val regionDdl = """
        create table if not exists REGION (
            R_REGIONKEY INTEGER not null constraint R_REGIONKEY primary key,
            R_NAME      VARCHAR(max),
            R_COMMENT   VARCHAR(max)
        );
    """.trimIndent()
    con.createStatement().execute(regionDdl)

    val reader = csvReader {
        delimiter = '|'
    }
    reader.open("./data/region.tbl") {
        readAllAsSequence().forEach { row ->
            val sql = "insert into REGION (R_REGIONKEY,R_NAME,R_COMMENT) values (?,?,?)"
            val updateSales = con.prepareStatement(sql)
            updateSales.setInt(1, row[0].toInt())
            updateSales.setString(2, row[1])
            updateSales.setString(3, row[2])
            updateSales.execute()
        }
    }
    
    // ----------- thin client
    val cfg = ClientConfiguration().setAddresses("127.0.0.1:10800")
    Ignition.startClient(cfg).use { client ->

        val cache = client.getOrCreateCache<Int, String>("myCache")
        cache.put(1, "Hello")
        cache.put(2, "World!")

        val names = client.cacheNames()
        for(name in names) {
            println("cache: $name")
            if(name != "myCache") {
                continue
            }

            val cache = client.cache<Int, String>(name)
            for(entity in cache.configuration.queryEntities) {
                println("entity: ${entity.tableName}")
            }

            val query = ScanQuery<Int, String>()
            cache.query(query).use { cursor ->
                val records = cursor.all
                for(entry in records) {
                    println("${entry.key}=${entry.value}")
                }
            }
        }
    }
}

private class RemoteTask : IgniteRunnable {
    @IgniteInstanceResource
    var ignite: Ignite? = null
    override fun run() {
        println(">> Executing the compute task")
        println(
            """   
    Node ID: ${ignite!!.cluster().localNode().id()}
    OS: ${System.getProperty("os.name")}
    JRE: ${System.getProperty("java.runtime.name")}
"""
        )
        val cache: IgniteCache<Int, String> = ignite!!.cache("myCache")
        println(">> ${cache.get(1)} ${cache.get(2)}")
    }
}
