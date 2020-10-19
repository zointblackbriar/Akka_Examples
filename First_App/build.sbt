name := "hello-akka"

version := "1.0"

//scalaVersion := "2.11.7"
scalaVersion := "2.13.3"

val AkkaVersion = "2.6.10"
//"2.4.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion
)
