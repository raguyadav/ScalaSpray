name := "scala"

version := "1.0"

scalaVersion:= "2.10.5"

libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % "1.3.1",
  "io.spray" % "spray-routing" % "1.3.1",
  "io.spray" %  "spray-json_2.10" % "1.3.1",
  "io.spray" % "spray-testkit" % "1.3.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-agent" % "2.3.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.4",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "com.typesafe" % "config" % "1.2.0",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "mysql" % "mysql-connector-java" % "5.1.34",
  "joda-time" % "joda-time" % "2.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0")
