import org.scalatest.FunSuite
import net.orhanbalci.cheqpas.OverdraftChequeAnouncementParser._
import atto._
import Atto._

class OverdraftChequeAnouncementParserSpec extends FunSuite {
  test("NameParser should parse strings of length 15") {
    assert(
      "aaaaaaaaaaaaaaa" == name.parse("aaaaaaaaaaaaaaa").option.map(_.value).getOrElse("")
    )
  }

  test("NameParser should fail on strings length less than 15") {
    assert("" == name.parse("aaa").option.map(_.value).getOrElse(""))
  }

}
