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


fun main(args: Array<String>) {
    // Preparing IgniteConfiguration using Java APIs
    // Preparing IgniteConfiguration using Java APIs
    val cfg = IgniteConfiguration()

    // The node will be started as a client node.

    // The node will be started as a client node.
    cfg.setClientMode(true)

    // Classes of custom Java logic will be transferred over the wire from this app.

    // Classes of custom Java logic will be transferred over the wire from this app.
    cfg.isPeerClassLoadingEnabled = true
    cfg.setDeploymentMode(DeploymentMode.CONTINUOUS);

    // Setting up an IP Finder to ensure the client can locate the servers.

    // Setting up an IP Finder to ensure the client can locate the servers.
    val ipFinder = TcpDiscoveryMulticastIpFinder()
    ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"))
    cfg.discoverySpi = TcpDiscoverySpi().setIpFinder(ipFinder)

    // Starting the node

    // Starting the node
    val ignite = Ignition.start(cfg)

    // Create an IgniteCache and put some values in it.

    // Create an IgniteCache and put some values in it.
    val cache = ignite.getOrCreateCache<Int, String>("myCache")
    cache.put(1, "Hello")
    cache.put(2, "World!")

    println(">> Created the cache and add the values.")

    // Executing custom Java compute task on server nodes.

    // Executing custom Java compute task on server nodes.
    ignite.compute(ignite.cluster().forServers()).broadcast(RemoteTask())

    println(">> Compute task is executed, check for output on the server nodes.")

    // Disconnect from the cluster.

    // Disconnect from the cluster.
    ignite.close()
}

private class RemoteTask constructor() : IgniteRunnable {
    @IgniteInstanceResource
    var ignite: Ignite? = null
    override fun run() {
        println(">> Executing the compute task")
        println(
            ("   Node ID: " + ignite!!.cluster().localNode().id() + "\n" +
                    "   OS: " + java.lang.System.getProperty("os.name") +
                    "   JRE: " + java.lang.System.getProperty("java.runtime.name"))
        )
        val cache: IgniteCache<Int, String> = ignite!!.cache("myCache")
        println(">> " + cache.get(1) + " " + cache.get(2))
    }
}
