package net.orhanbalci.cheqpas
import atto._
import Atto._

object Main extends Greeting with App {
  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
