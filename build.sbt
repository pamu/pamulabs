name := """pamulabs"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3-M1",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars" % "requirejs" % "2.1.11-1"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)


fork in run := true
