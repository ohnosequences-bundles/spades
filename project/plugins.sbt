resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"
resolvers += "Era7 maven snapshots" at "http://snapshots.era7.com.s3.amazonaws.com"

addSbtPlugin("ohnosequences" % "sbt-statika" % "2.0.0-SNAPSHOT")

resolvers += "Github-API" at "http://repo.jenkins-ci.org/public/"