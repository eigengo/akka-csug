package org.eigengo.akkacsug.importer

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

import scala.io.StdIn

object AccelerometerImporterMain extends App {
  import akka.pattern.ask
  import scala.concurrent.duration._

  implicit val timeout = Timeout(5.seconds)
  implicit val system = ActorSystem()
  import system.dispatcher
  val importer = system.actorOf(Props[AccelerometerImporter])

  val is = getClass.getResourceAsStream("/training/chest1.dat")
  val bits = BitVector.fromInputStream(is)
  (importer ? bits).mapTo[List[AccelerometerData]].onSuccess {
    case ads => ads.foreach(avs => avs.values.foreach(av => println(s"${av.x},${av.y},${av.z}")))
  }

  StdIn.readLine()
  system.shutdown()
}
