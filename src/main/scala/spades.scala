package ohnosequencesBundles.statika

import ohnosequences.cosas.typeSets._
import ohnosequences.statika._, bundles._, instructions._, aws._
import ohnosequences.awstools.regions.Region._


case object spades {

  case object ami extends amzn_ami_pv_64bit(Ireland)(1)

  case object spades extends Bundle(∅) {


    def install: Results = {

      import ammonite.ops._
      val wd = cwd

      val getFiles =
        Seq("aws", "s3", "cp", "s3://resources.ohnosequences.com/spades/SPAdes-3.1.0-Linux.tar.gz", "./") -&- Seq("tar","-xvf", "SPAdes-3.1.0-Linux.tar.gz")

      val spadesBin = "spades.py"
      val spadesBinPath = "SPAdes-3.1.0-Linux"/"bin"/spadesBin
      val usrbin = root/"usr"/"bin"

      ln.s(wd/spadesBinPath, usrbin/spadesBin)

      //Seq("ln", "-s","./SPAdes-3.1.0-Linux/bin/spades.py","/usr/bin/")

      if ( exists(usrbin/spadesBin) )
        success(fullName + " is installed")
      else
        failure("Something went wrong with the linking :(")
    }
  }


  case object compatibles {

    implicit def spadesCompat[E <: AmazonLinuxAMI](e: E): Compatible[E, spades.type] =
      new Compatible(e, spades, generated.metadata.Spades)
  }

}
