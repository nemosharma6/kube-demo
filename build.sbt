enablePlugins(DockerPlugin)

name := "kube-demo"

organization := "nimish"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.8",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.8",
  "com.typesafe.akka" %% "akka-stream" % "2.5.22",
  "net.debasishg" %% "redisclient" % "3.9",
  "org.scala-lang" % "scala-library" % "2.12.8"
)

mainClass in Compile := Some("CacheServer")
assemblyJarName := "cache-server.jar"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _@_*) => MergeStrategy.discard
  case PathList("reference.conf") => MergeStrategy.concat
  case _ => MergeStrategy.first
}

dockerfile in docker := {

  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"

  new Dockerfile {
    from("openjdk:8-jre")
    add(artifact, artifactTargetPath)
    entryPoint("java", "-jar", artifactTargetPath)
  }
}