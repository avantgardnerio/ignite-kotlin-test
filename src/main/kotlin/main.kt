import org.apache.ignite.Ignite
import org.apache.ignite.IgniteCache
import org.apache.ignite.Ignition
import org.apache.ignite.configuration.DeploymentMode
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.lang.IgniteRunnable
import org.apache.ignite.resources.IgniteInstanceResource
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder
import java.util.*

fun main() {
    val cfg = IgniteConfiguration()
    cfg.isClientMode = true
    cfg.isPeerClassLoadingEnabled = true
    cfg.deploymentMode = DeploymentMode.CONTINUOUS;
    val ipFinder = TcpDiscoveryMulticastIpFinder()
    ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"))
    cfg.discoverySpi = TcpDiscoverySpi().setIpFinder(ipFinder)
    val ignite = Ignition.start(cfg)

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
