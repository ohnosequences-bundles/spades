Nice.scalaProject

name := "spades"
organization := "ohnosequences-bundles"
description := "A bundle for spades tool"

publishBucketSuffix := "era7.com"

resolvers ++= Seq(
  "Era7 public maven releases"  at s3("releases.era7.com").toHttps(s3region.value.toString),
  "Era7 public maven snapshots" at s3("snapshots.era7.com").toHttps(s3region.value.toString)
)

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika-cli" % "0.18.0-SNAPSHOT" % Test,
  "org.scalatest" %% "scalatest" % "2.2.4" % Test,
  "com.lihaoyi"   %% "ammonite-ops" % "0.2.7"
)
