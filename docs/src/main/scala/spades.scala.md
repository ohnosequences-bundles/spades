
```scala
package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File

abstract class Spades(val version: String) extends Bundle() { spades =>

  val folder = s"SPAdes-${spades.version}-Linux"
  val tarball = spades.folder + ".tar.gz"
  val binary = "bin/spades.py"

  lazy val getTarball = cmd("wget")(
    s"https://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/spades/${spades.version}/${spades.tarball}"
  )

  lazy val extractTarball = cmd("tar")("-xvzf", spades.tarball)

  lazy val linkBinaries = cmd("ln")(
    "-s",
    new File(s"${spades.folder}/${spades.binary}").getCanonicalPath,
    s"/usr/${spades.binary}"
  )

  def instructions: AnyInstructions = getTarball -&- extractTarball -&- linkBinaries

}

```




[main/scala/spades.scala]: spades.scala.md