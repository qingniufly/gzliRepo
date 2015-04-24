name := "spark-helloworld"

organization := "com.simon.spark"

version := "0.0.1-SNAPSHOT"

scalaVersion :="2.11.6"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

// classpathTypes += "orbit"

libraryDependencies ++= Seq(
    "org.json4s" %% "json4s-native" % "3.2.10",
    "org.json4s" %% "json4s-jackson" % "3.2.10"
)

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "1.3.1"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

resolvers ++= Seq(
      // HTTPS is unavailable for Maven Central  
      "Maven Repository"     at "http://repo.maven.apache.org/maven2",  
      "Apache Repository"    at "https://repository.apache.org/content/repositories/releases",  
      "JBoss Repository"     at "https://repository.jboss.org/nexus/content/repositories/releases/",  
      "MQTT Repository"      at "https://repo.eclipse.org/content/repositories/paho-releases/",  
      "Cloudera Repository"  at "http://repository.cloudera.com/artifactory/cloudera-repos/",  
	  // "Local Maven Repository" at ""+Path.userHome.asFile.toURI.toURL+"/.m2/repository",
      // For Sonatype publishing  
      // "sonatype-snapshots"   at "https://oss.sonatype.org/content/repositories/snapshots",  
      // "sonatype-staging"     at "https://oss.sonatype.org/service/local/staging/deploy/maven2/",  
      // also check the local Maven repository ~/.m2  
      Resolver.mavenLocal
)