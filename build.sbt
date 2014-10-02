organization := "net.arya"

name := "intern"

version := "0.1"

scalaVersion := "2.10.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.0" % "test"

publishTo := Some(Resolver.sftp("arya.net repo", "arya.net", "/home/arya/arya.net/maven2"))

