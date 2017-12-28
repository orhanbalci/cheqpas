import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "net.orhanbalci",
      scalaVersion := "2.12.4",
      version := "0.1.0"
    )),
  name := "cheqpas",
  libraryDependencies += scalaTest              % Test,
  libraryDependencies += "org.rogach"           %% "scallop" % "3.1.1",
  libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.4.0"
)
