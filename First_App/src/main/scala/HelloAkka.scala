import akka.actor.{ ActorRef, ActorSystem, Props, Actor }
import scala.concurrent.duration._


// Define Actor Messages
case class WhoToGreet(who: String)

// Define Greeter Actor
class Greeter extends Actor {
  def receive = {
    case WhoToGreet(who) => println(s"Hello $who")
  }
}

case class whoToReceiveSalutation(who: String)

class Saluter extends Actor {
  def receiveSalutation: PartialFunction[Any, Unit] = {
    case whoToReceiveSalutation(who) => println(s"Second System said hello: $who")
  }
}

object HelloAkkaScala extends App {

  // Create the 'hello akka' actor system
  val system = ActorSystem("Hello-Akka")
  val secondSystem = ActorSystem("Hello-Akka-World")

  // Create the 'greeter' actor
  val greeter = system.actorOf(Props[Greeter], "greeter")
  val saluter = secondSystem.actorOf(Props[Saluter],name = "saluter")

  // Send WhoToGreet Message to actor
  greeter ! WhoToGreet("Akka")
  saluter ! whoToReceiveSalutation("Akko World")

}
