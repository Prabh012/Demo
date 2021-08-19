name := """Demo"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  "mysql" % "mysql-connector-java" % "8.0.11"
)
