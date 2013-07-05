name := "pubsub.io"

organization := "io.pubsub"

version := "1.0"

scalaVersion := "2.10.1"

resolvers ++= Seq(
    "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= Seq(
  "io.spray"            %   "spray-can"     % "1.1-M8",
  "io.spray"            %   "spray-routing" % "1.1-M8",
  "io.spray"            %   "spray-testkit" % "1.1-M8",
  "io.spray"            %   "spray-caching" % "1.1-M8",
  "org.fusesource.scalate" % "scalate-core_2.10" % "1.6.1",
  "com.typesafe.akka" %%  "akka-actor" % "2.1.2",
  "com.typesafe.akka" %%  "akka-slf4j" % "2.1.2",
  "ch.qos.logback"% "logback-classic" % "1.0.12" % "runtime",
  "org.specs2"          %%  "specs2"        % "1.14" % "test"
)

EclipseKeys.withSource := true
