package net.orhanbalci.cheqpas
import atto._
import Atto._
import org.rogach.scallop._
import better.files.{File => ScalaFile, _}
import better.files.Dsl._
import OverdraftChequeAnouncementParser._

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
    val overdraftFileContent = ScalaFile(conf.overdraftAnnouncementFile()).contentAsString;
    overdraftChequeAnouncement.parse(overdraftFileContent)
}
