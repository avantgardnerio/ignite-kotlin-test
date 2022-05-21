# Apache Ignite Test

Prove we can connect to Apache Ignite using kotlin.

## Resources

- https://ignite.apache.org/docs/latest/quick-start/java
- https://ignite.apache.org/docs/latest/installation/installing-using-docker
- https://ignite.apache.org/docs/latest/code-deployment/peer-class-loading
- https://ignite.apache.org/docs/latest/understanding-configuration

## Example server output

```shell
sudo docker run --mount type=bind,source=$(pwd)/config-file.xml,target=/config-file.xml -e CONFIG_URI=/config-file.xml apacheignite/ignite
[18:00:20]    __________  ________________ 
[18:00:20]   /  _/ ___/ |/ /  _/_  __/ __/ 
[18:00:20]  _/ // (7 7    // /  / / / _/   
[18:00:20] /___/\___/_/|_/___/ /_/ /x___/  
[18:00:20] 
[18:00:20] ver. 2.13.0#20220420-sha1:551f6ece
[18:00:20] 2022 Copyright(C) Apache Software Foundation
[18:00:20] 
[18:00:20] Ignite documentation: https://ignite.apache.org
[18:00:20] 
[18:00:20] Quiet mode.
[18:00:20]   ^-- Logging to file '/opt/ignite/apache-ignite/work/log/ignite-28a32ae3.0.log'
[18:00:20]   ^-- Logging by 'JavaLogger [quiet=true, config=null]'
[18:00:20]   ^-- To see **FULL** console log here add -DIGNITE_QUIET=false or "-v" to ignite.{sh|bat}
[18:00:20] 
[18:00:20] OS: Linux 5.15.0-27-generic amd64
[18:00:20] VM information: OpenJDK Runtime Environment 1.8.0_212-b04 IcedTea OpenJDK 64-Bit Server VM 25.212-b04
[18:00:20] Please set system property '-Djava.net.preferIPv4Stack=true' to avoid possible problems in mixed environments.
[18:00:20] Configured plugins:
[18:00:20]   ^-- None
[18:00:20] 
[18:00:20] Configured failure handler: [hnd=StopNodeOrHaltFailureHandler [tryStop=false, timeout=0, super=AbstractFailureHandler [ignoredFailureTypes=UnmodifiableSet [SYSTEM_WORKER_BLOCKED, SYSTEM_CRITICAL_OPERATION_TIMEOUT]]]]
[18:00:20] Message queue limit is set to 0 which may lead to potential OOMEs when running cache operations in FULL_ASYNC or PRIMARY_SYNC modes due to message queues growth on sender and receiver sides.
[18:00:22] Data Regions Started: 4
[18:00:22] 
[18:00:22]     ^--   sysMemPlc region [type=internal, persistence=false, lazyAlloc=false,
[18:00:22]       ...  initCfg=40MB, maxCfg=100MB, usedRam=0MB, freeRam=100%, allocRam=40MB]
[18:00:22]     ^--   default region [type=default, persistence=false, lazyAlloc=true,
[18:00:22]       ...  initCfg=256MB, maxCfg=12834MB, usedRam=0MB, freeRam=100%, allocRam=0MB]
[18:00:22]     ^--   TxLog region [type=internal, persistence=false, lazyAlloc=false,
[18:00:22]       ...  initCfg=40MB, maxCfg=100MB, usedRam=0MB, freeRam=100%, allocRam=40MB]
[18:00:22]     ^--   volatileDsMemPlc region [type=user, persistence=false, lazyAlloc=true,
[18:00:22]       ...  initCfg=40MB, maxCfg=100MB, usedRam=0MB, freeRam=100%, allocRam=0MB]
[18:00:22] Security status [authentication=off, sandbox=off, tls/ssl=off]
[18:00:22] Performance suggestions for grid  (fix if possible)
[18:00:22] To disable, set -DIGNITE_PERFORMANCE_SUGGESTIONS_DISABLED=true
[18:00:22]   ^-- Switch to the most recent 11 JVM version
[18:00:22]   ^-- Enable G1 Garbage Collector (add '-XX:+UseG1GC' to JVM options)
[18:00:22]   ^-- Set max direct memory size if getting 'OOME: Direct buffer memory' (add '-XX:MaxDirectMemorySize=<size>[g|G|m|M|k|K]' to JVM options)
[18:00:22]   ^-- Speed up flushing of dirty pages by OS (alter vm.dirty_expire_centisecs parameter by setting to 500)
[18:00:22]   ^-- Reduce pages swapping ratio (set vm.swappiness=10 or less)
[18:00:22] Refer to this page for more performance suggestions: https://ignite.apache.org/docs/latest/perf-and-troubleshooting/memory-tuning
[18:00:22] 
[18:00:22] To start Console Management & Monitoring run ignitevisorcmd.{sh|bat}
[18:00:22] 
[18:00:22] Ignite node started OK (id=28a32ae3)
[18:00:22] Topology snapshot [ver=1, locNode=28a32ae3, servers=1, clients=0, state=ACTIVE, CPUs=12, offheap=13.0GB, heap=1.0GB]
[18:00:22]   ^-- Baseline [id=0, size=1, online=1, offline=0]
[18:00:30] Topology snapshot [ver=2, locNode=28a32ae3, servers=1, clients=1, state=ACTIVE, CPUs=24, offheap=13.0GB, heap=15.0GB]
[18:00:30]   ^-- Baseline [id=0, size=1, online=1, offline=0]
>> Executing the compute task
   Node ID: 28a32ae3-add1-415b-a42b-8100b6a0b843
   OS: Linux   JRE: OpenJDK Runtime Environment
>> Hello World!
[18:00:30] Topology snapshot [ver=3, locNode=28a32ae3, servers=1, clients=0, state=ACTIVE, CPUs=12, offheap=13.0GB, heap=1.0GB]
[18:00:30]   ^-- Baseline [id=0, size=1, online=1, offline=0]
[18:01:22] 
[18:01:22]     ^--   sysMemPlc region [type=internal, persistence=false, lazyAlloc=false,
[18:01:22]       ...  initCfg=40MB, maxCfg=100MB, usedRam=0MB, freeRam=99.21%, allocRam=40MB]
[18:01:22]     ^--   default region [type=default, persistence=false, lazyAlloc=true,
[18:01:22]       ...  initCfg=256MB, maxCfg=12834MB, usedRam=8MB, freeRam=99.94%, allocRam=256MB]
[18:01:22]     ^--   TxLog region [type=internal, persistence=false, lazyAlloc=false,
[18:01:22]       ...  initCfg=40MB, maxCfg=100MB, usedRam=0MB, freeRam=100%, allocRam=40MB]
[18:01:22]     ^--   volatileDsMemPlc region [type=user, persistence=false, lazyAlloc=true,
[18:01:22]       ...  initCfg=40MB, maxCfg=100MB, usedRam=0MB, freeRam=100%, allocRam=0MB]
[18:01:22] 
[18:01:22] 
[18:01:22] Data storage metrics for local node (to disable set 'metricsLogFrequency' to 0)
[18:01:22]     ^-- Off-heap memory [used=8MB, free=99.93%, allocated=336MB]
[18:01:22]     ^-- Page memory [pages=2253]
```

## Example client output

```shell
/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/java -javaagent:/snap/intellij-idea-ultimate/357/lib/idea_rt.jar=33117:/snap/intellij-idea-ultimate/357/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/charsets.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/cldrdata.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/icedtea-sound.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/jaccess.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/java-atk-wrapper.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/nashorn.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/jce.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/jfr.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/jsse.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/management-agent.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/resources.jar:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/rt.jar:/home/bgardner/workspace/ignitetest/build/classes/kotlin/main:/home/bgardner/workspace/ignitetest/build/resources/main:/home/bgardner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.6.21/eeb4d60d75e9ea9c11200d52974e522793b14fba/kotlin-stdlib-jdk8-1.6.21.jar:/home/bgardner/.gradle/caches/modules-2/files-2.1/org.apache.ignite/ignite-core/2.13.0/7a84a211883ae5c313263aad0ed5af4103a0e1ab/ignite-core-2.13.0.jar:/home/bgardner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.6.21/568c1b78a8e17a4f35b31f0a74e2916095ed74c2/kotlin-stdlib-jdk7-1.6.21.jar:/home/bgardner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.6.21/11ef67f1900634fd951bad28c53ec957fabbe5b8/kotlin-stdlib-1.6.21.jar:/home/bgardner/.gradle/caches/modules-2/files-2.1/javax.cache/cache-api/1.0.0/2b57384801243f387f1a2e7ab8066ac79c2a91d3/cache-api-1.0.0.jar:/home/bgardner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/16.0.3/62c7299ced2a089cc541726c6d763da9417604a0/annotations-16.0.3.jar:/home/bgardner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.6.21/5e5b55c26dbc80372a920aef60eb774b714559b8/kotlin-stdlib-common-1.6.21.jar MainKt
May 21, 2022 12:00:28 PM java.util.logging.LogManager$RootLogger log
WARNING: Failed to resolve default logging config file: config/java.util.logging.properties
[12:00:28]    __________  ________________ 
[12:00:28]   /  _/ ___/ |/ /  _/_  __/ __/ 
[12:00:28]  _/ // (7 7    // /  / / / _/   
[12:00:28] /___/\___/_/|_/___/ /_/ /x___/  
[12:00:28] 
[12:00:28] ver. 2.13.0#20220420-sha1:551f6ece
[12:00:28] 2022 Copyright(C) Apache Software Foundation
[12:00:28] 
[12:00:28] Ignite documentation: https://ignite.apache.org
[12:00:28] 
[12:00:28] Quiet mode.
[12:00:28]   ^-- Logging by 'JavaLogger [quiet=true, config=null]'
[12:00:28]   ^-- To see **FULL** console log here add -DIGNITE_QUIET=false or "-v" to ignite.{sh|bat}
[12:00:28] 
[12:00:28] OS: Linux 5.15.0-27-generic amd64
[12:00:28] VM information: OpenJDK Runtime Environment 1.8.0_312-8u312-b07-0ubuntu1-b07 Private Build OpenJDK 64-Bit Server VM 25.312-b07
[12:00:28] Please set system property '-Djava.net.preferIPv4Stack=true' to avoid possible problems in mixed environments.
[12:00:28] Configured plugins:
[12:00:28]   ^-- None
[12:00:28] 
[12:00:28] Configured failure handler: [hnd=StopNodeOrHaltFailureHandler [tryStop=false, timeout=0, super=AbstractFailureHandler [ignoredFailureTypes=UnmodifiableSet [SYSTEM_WORKER_BLOCKED, SYSTEM_CRITICAL_OPERATION_TIMEOUT]]]]
[12:00:28] Message queue limit is set to 0 which may lead to potential OOMEs when running cache operations in FULL_ASYNC or PRIMARY_SYNC modes due to message queues growth on sender and receiver sides.
[12:00:28] REST protocols do not start on client node. To start the protocols on client node set '-DIGNITE_REST_START_ON_CLIENT=true' system property.
[12:00:30] Security status [authentication=off, sandbox=off, tls/ssl=off]
[12:00:30] Performance suggestions for grid  (fix if possible)
[12:00:30] To disable, set -DIGNITE_PERFORMANCE_SUGGESTIONS_DISABLED=true
[12:00:30]   ^-- Switch to the most recent 11 JVM version
[12:00:30]   ^-- Enable G1 Garbage Collector (add '-XX:+UseG1GC' to JVM options)
[12:00:30]   ^-- Specify JVM heap max size (add '-Xmx<size>[g|G|m|M|k|K]' to JVM options)
[12:00:30]   ^-- Set max direct memory size if getting 'OOME: Direct buffer memory' (add '-XX:MaxDirectMemorySize=<size>[g|G|m|M|k|K]' to JVM options)
[12:00:30]   ^-- Speed up flushing of dirty pages by OS (alter vm.dirty_expire_centisecs parameter by setting to 500)
[12:00:30]   ^-- Reduce pages swapping ratio (set vm.swappiness=10 or less)
[12:00:30] Refer to this page for more performance suggestions: https://ignite.apache.org/docs/latest/perf-and-troubleshooting/memory-tuning
[12:00:30] 
[12:00:30] To start Console Management & Monitoring run ignitevisorcmd.{sh|bat}
[12:00:30] 
[12:00:30] Ignite node started OK (id=9ae903b8)
[12:00:30] Topology snapshot [ver=2, locNode=9ae903b8, servers=1, clients=1, state=ACTIVE, CPUs=24, offheap=13.0GB, heap=15.0GB]
[12:00:30]   ^-- Baseline [id=0, size=1, online=1, offline=0]
>> Created the cache and add the values.
>> Compute task is executed, check for output on the server nodes.
[12:00:30] Ignite node stopped OK [uptime=00:00:00.252]

Process finished with exit code 0
```
