package ohnosequences.statika.tests

import ohnosequences.statika._, aws._, bundles._
import ohnosequences-bundles.statika.spades._

import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import com.amazonaws.auth.profile._
import ohnosequences.awstools.regions.Region._


class ApplicationTest extends org.scalatest.FunSuite {

  val ec2 = EC2.create(new ProfileCredentialsProvider("intercrossing"))

  def testBundle[A <: AnyAMI, B <: AnyBundle](ami: A, bundle: B)(implicit comp: A => Compatible[A, B]) = {
    test("Apply "+bundle.name+" bundle to an instance"){
      val specs = InstanceSpecs(
        instanceType = InstanceType.m1_small,
        amiId = ami.id,
        keyName = "statika-test",
        userData = ami.userScript(bundle),
        instanceProfile = Some("god")
      )

      println(specs.userData)

      val result = ec2.applyAndWait(bundle.name, specs, 1) match {
        case List(inst) => inst.getTagValue("statika-status") == Some("success")
        case _ => false
      }
      assert(result)
    }
  }


  import compatibles._

  testBundle(ami, spades)
}
