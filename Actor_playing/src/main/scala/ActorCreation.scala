package com.packt.akka

import akka.actor.{ ActorRef, ActorSystem, Props, Actor }
import scala.concurrent.duration._

object FirstObject {
  case object Start
  case object Stop

  def props = Props[FirstActor]
}

class FirstActor extends Actor {
  import FirstObject._

  def receive = {
    case Start =>
      println("Action Started .............")
    case Stop =>
      println("Action Stopped .............")
  }

}

object SecondObject {
  case object startAction
  case object stopAction
}

class SecondActor extends Actor {
  import SecondObject._

  def receive = {
    case startAction =>
      val firstObject = context.actorOf(FirstObject.props)
      firstObject ! FirstObject.Start
    case stopAction => println("Action was stopped")

  }
}

object Creation extends App {

  // Create the 'creation' actor system
  val system = ActorSystem("creation")

  // Create the 'Zeus' actor
  val secondObject = system.actorOf(Props[SecondActor], "SecondActor")

  //send StartMusic Message to actor
  secondObject ! SecondObject.startAction

  // Send StopMusic Message to actor
  secondObject ! SecondObject.stopAction

  //shutdown system
  system.shutdown()

}