package net.orhanbalci.cheqpas
import atto._
import Atto._

object Main extends Greeting with App {
  //println(greeting)
  println(OverdraftChequeAnouncementParser.overdraftChequeAnouncement parseOnly "aaaaa\n)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
