package org.eigengo.akkacsug

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import scodec.bits.BitVector

import scala.io.StdIn

object AccelerometerImporterMain extends App {
  import akka.pattern.ask
  import org.eigengo.akkacsug.AccelerometerImporter._

import scala.concurrent.duration._

  implicit val timeout = Timeout(5.seconds)
  implicit val system = ActorSystem()
  import org.eigengo.akkacsug.AccelerometerImporterMain.system.dispatcher
  val importer = system.actorOf(Props[AccelerometerImporter])

  val is = getClass.getResourceAsStream("/training/chest1.dat")
  val bits = BitVector.fromInputStream(is)
  (importer ? bits).mapTo[List[AccelerometerData]].onSuccess {
    case ads => ads.foreach(avs => avs.values.foreach(av => println(s"${av.x},${av.y},${av.z}")))
  }

  StdIn.readLine()
  system.shutdown()
}
