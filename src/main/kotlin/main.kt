import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.apache.ignite.Ignite
import org.apache.ignite.IgniteCache
import org.apache.ignite.Ignition
import org.apache.ignite.configuration.DeploymentMode
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.lang.IgniteRunnable
import org.apache.ignite.resources.IgniteInstanceResource
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder
import java.sql.DriverManager
import java.util.*

fun main() {
    // Register JDBC driver.
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

    val cfg = IgniteConfiguration()
    cfg.isClientMode = true
    cfg.isPeerClassLoadingEnabled = true
    cfg.deploymentMode = DeploymentMode.CONTINUOUS;
    val ipFinder = TcpDiscoveryMulticastIpFinder()
    ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"))
    cfg.discoverySpi = TcpDiscoverySpi().setIpFinder(ipFinder)
    val ignite = Ignition.start(cfg)

    // list caches
    for(name in ignite.cacheNames()) {
        println(name)
    }
    val region = ignite.getOrCreateCache<Int, Object>("SQL_PUBLIC_REGION")
    val res = region.get(0)

    // Create an IgniteCache and put some values in it.
    val cache = ignite.getOrCreateCache<Int, String>("myCache")
    cache.put(1, "Hello")
    cache.put(2, "World!")
    println(">> Created the cache and add the values.")

    // Executing custom Java compute task on server nodes.
    ignite.compute(ignite.cluster().forServers()).broadcast(RemoteTask())
    println(">> Compute task is executed, check for output on the server nodes.")

    // Disconnect from the cluster.
    ignite.close()
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
