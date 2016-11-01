name := """chat-room-svc"""

version := "1.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  filters,
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.5"
)


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := false