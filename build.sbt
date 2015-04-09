SbtStatikaPlugin.projectSettings

name := "spades"
organization := "ohnosequences-bundles"
description := "A bundle for spades tool"

publishBucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika-cli" % "0.18.0-SNAPSHOT" % Test,
  "org.scalatest" %% "scalatest" % "2.2.4" % Test
)
