package ohnosequences-bundles.statika

import ohnosequences.cosas.typeSets._
import ohnosequences.statika._, bundles._, instructions._, aws._
import ohnosequences.awstools.regions.Region._


case object spades {

  case object ami extends amzn_ami_pv_64bit(Ireland)(1)

  case object spades extends Bundle(∅) {

    def install: Results = {

      Seq("aws", "s3", "cp", "s3://resources.ohnosequences.com/spades/SPAdes-3.1.0-Linux.tar.gz", "./"  ) -&-
      Seq("tar","-xvf", "SPAdes-3.1.0-Linux.tar.gz") -&-
      Seq("cp","./SPAdes-3.1.0-Linux/spades.py","/usr/bin/") ->-
    
      success(fullName + " is installed")
    }
  }


  case object compatibles {

    implicit def spadesCompat[E <: AmazonLinuxAMI](e: E): Compatible[E, spades.type] =
      new Compatible(e, spades, generated.metadata.spades)
  }

}
