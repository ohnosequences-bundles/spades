package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._

case object spades {

  case object spades extends Bundle {

    import ammonite.ops._

    val spadesBin = "spades.py"
    val spadesBinPath = "SPAdes-3.1.0-Linux"/"bin"/spadesBin
    val usrbin = root/"usr"/"bin"

    def install: Results = {

      Seq("aws", "s3", "cp", "s3://resources.ohnosequences.com/spades/SPAdes-3.1.0-Linux.tar.gz", "./") -&-
      Seq("tar","-xvf", "SPAdes-3.1.0-Linux.tar.gz") ->- {

        // cmds return Unit. Sad.
        val wd = cwd
        ln.s(wd/spadesBinPath, usrbin/spadesBin)

        if ( exists(usrbin/spadesBin) )
          success(bundleFullName + " is installed")
        else
          failure("Something went wrong with the linking :(")
      }
    }
  }
}
