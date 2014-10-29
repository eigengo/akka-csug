package org.eigengo.akkacsug.importer

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import org.eigengo.akkacsug.AccelerometerData
import scodec.bits.BitVector

import scala.io.StdIn

object AccelerometerImporterMain extends App {
  import akka.pattern.ask
  import scala.concurrent.duration._

  // construct the ActorSystem
  // construct the AccelerometerImporter

  // load the classpath resource ``/training/*.dat``
  // turn it into BitVector (use the companion object functions)

  // import akka.pattern.ask
  // ask the importer for the result of sending it the bits

  // print the received values as CSV data on the console
  // optional: use R to plot the time series data
}
