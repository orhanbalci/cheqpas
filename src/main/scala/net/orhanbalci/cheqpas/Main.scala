package net.orhanbalci.cheqpas
import atto._
import Atto._
import org.rogach.scallop._
import better.files.{File => ScalaFile, _}
import better.files.Dsl._
import OverdraftChequeAnouncementParser._
import OverdraftChequeAnouncementParser.overdraftChequeAnouncementLineEncoder
import io.circe.syntax._ 
import io.circe.generic.auto._
import io.circe.Printer

class Conf(settings: Seq[String]) extends ScallopConf(settings) {
  version("cheqpas 0.1.0 (c) 2017 Orhan Balci")
  banner("""Usage: java -jar cheqpas.jar [OPTION]
           |Overdraft Cheque Anouncemen File Parser
           |Options:
           |""".stripMargin)
  val overdraftAnnouncementFile = opt[String](descr = "Overdraft Cheque Announcement File")
  verify()
}

object Main extends App {
  val conf = new Conf(args);
  val overdraftFileContent =
    ScalaFile(conf.overdraftAnnouncementFile()).contentAsString(charset = "Windows-1254");
  
  val overdraftAnnouncement = overdraftChequeAnouncement
    .parseOnly(overdraftFileContent)
    .option
    
  val printer = Printer(
    preserveOrder = true,
    dropNullValues = false,
    indent = " ",
    lbraceRight = " ",
    rbraceLeft = " ",
    lbracketRight = "\n",
    rbracketLeft = "\n",
    lrbracketsEmpty = "\n",
    arrayCommaRight = "\n",
    objectCommaRight = " ",
    colonLeft = " ",
    colonRight = " "
  )

  overdraftAnnouncement match {
    case Some(i) => println(i.asJson.pretty(printer))
    case None    => println("That didn't work.")
  }
}
