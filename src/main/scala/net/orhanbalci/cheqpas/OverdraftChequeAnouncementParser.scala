package net.orhanbalci.cheqpas;
import atto._
import Atto._
import cats.implicits._
import io.circe.Encoder, io.circe.generic.semiauto._

case object OverdraftChequeAnouncementParser {
  def fixedInt(n: Int): Parser[Int] = {
    count(n, digit).map(_.mkString).flatMap { s =>
      try ok(s.toInt)
      catch { case e: NumberFormatException => err(e.toString) }
    }
  }

  def fixedString(n: Int): Parser[String] = {
    count(n, letter).map(_.mkString)
  }

  val name                 = fixedString(15).map(Name.apply)
  val middleName           = fixedString(15).map(MiddleName.apply)
  val surname              = fixedString(30).map(Surname.apply)
  val fatherName           = fixedString(15).map(FatherName.apply)
  val motherName           = fixedString(15).map(MotherName.apply)
  val nameGroup            = (name, middleName, surname, fatherName, motherName).mapN(NameGroup.apply)
  val birthPlace           = fixedString(15).map(BirthPlace.apply)
  val birthPlaceCode       = fixedString(3).map(BirthPlaceCode.apply)
  val birthDate            = fixedString(8).map(BirthDate.apply)
  val identityNumber       = fixedString(11).map(IdentityNumber.apply)
  val address              = fixedString(60).map(Address.apply)
  val addressCode          = fixedString(3).map(AddressCode.apply)
  val statusCode           = fixedString(1).map(StatusCode.apply)
  val chequeAccountNumber  = fixedString(14).map(ChequeAccountNumber.apply)
  val drawingDate          = fixedString(8).map(DrawingDate.apply)
  val submissionDate       = fixedString(8).map(SubmissionDate.apply)
  val chequeSerialNumber   = fixedString(2).map(ChequeSerialNumber.apply)
  val chequeSequenceNumber = fixedString(10).map(ChequeSequenceNumber.apply)
  val chequeAmount         = fixedString(18).map(ChequeAmount.apply)
  val paymentType          = fixedString(2).map(PaymentType.apply)
  val paymentDate          = fixedString(10).map(PaymentDate.apply)
  val bankCode             = fixedString(3).map(BankCode.apply)
  val branchCode           = fixedString(4).map(BranchCode.apply)
  val isSharedAccount      = fixedString(1).map(IsSharedAccount.apply)
  val personType           = fixedString(1).map(PersonType.apply)
  val taxNumber            = fixedString(10).map(TaxNumber.apply)
  val originalChequeAmount = fixedString(18).map(OriginalChequeAmount.apply)
  val overdraftChequeAnouncementLine: Parser[OverdraftChequeAnouncementLine] =
    (nameGroup,
     birthPlace,
     birthPlaceCode,
     birthDate,
     identityNumber,
     address,
     addressCode,
     statusCode,
     chequeAccountNumber,
     drawingDate,
     submissionDate,
     chequeSerialNumber,
     chequeSequenceNumber,
     chequeAmount,
     paymentType,
     paymentDate,
     bankCode,
     branchCode,
     isSharedAccount,
     personType,
     taxNumber,
     originalChequeAmount).mapN(OverdraftChequeAnouncementLine.apply)
  val overdraftChequeAnouncement = sepBy(overdraftChequeAnouncementLine, char('\n'))

  case class Name(value: String)
  case class MiddleName(value: String)
  case class Surname(value: String)
  case class FatherName(value: String)
  case class MotherName(value: String)
  case class NameGroup(name: Name,
                       middleName: MiddleName,
                       surname: Surname,
                       fatherName: FatherName,
                       motherName: MotherName)
  case class BirthPlace(value: String)
  case class BirthPlaceCode(value: String)
  case class BirthDate(value: String)
  case class IdentityNumber(value: String)
  case class Address(value: String)
  case class AddressCode(value: String)
  case class StatusCode(value: String)
  case class ChequeAccountNumber(value: String)
  case class DrawingDate(value: String)
  case class SubmissionDate(value: String)
  case class ChequeSerialNumber(value: String)
  case class ChequeSequenceNumber(value: String)
  case class ChequeAmount(value: String)
  case class PaymentType(value: String)
  case class PaymentDate(value: String)
  case class BankCode(value: String)
  case class BranchCode(value: String)
  case class IsSharedAccount(value: String)
  case class PersonType(value: String)
  case class TaxNumber(value: String)
  case class OriginalChequeAmount(value: String)
  case class OverdraftChequeAnouncementLine(nameGroup: NameGroup,
                                            birthPlace: BirthPlace,
                                            birthPlaceCode: BirthPlaceCode,
                                            birthDate: BirthDate,
                                            identityNumber: IdentityNumber,
                                            address: Address,
                                            addressCode: AddressCode,
                                            statusCode: StatusCode,
                                            chequeAccountNumber: ChequeAccountNumber,
                                            drawingDate: DrawingDate,
                                            submissionDate: SubmissionDate,
                                            chequeSerialNumber: ChequeSerialNumber,
                                            chequeSequenceNumber: ChequeSequenceNumber,
                                            chequeAmount: ChequeAmount,
                                            paymentType: PaymentType,
                                            paymentDate: PaymentDate,
                                            bankCode: BankCode,
                                            branchCode: BranchCode,
                                            isSharedAccount: IsSharedAccount,
                                            personType: PersonType,
                                            taxNumber: TaxNumber,
                                            originalChequeAmount: OriginalChequeAmount)
  type OverdraftChequeAnouncement = List[OverdraftChequeAnouncementLine]

  implicit val nameEncoder: Encoder[Name]                     = deriveEncoder[Name]
  implicit val middleNameEncoder: Encoder[MiddleName]         = deriveEncoder[MiddleName]
  implicit val surnameEncoder: Encoder[Surname]               = deriveEncoder[Surname]
  implicit val fatherNameEncoder: Encoder[FatherName]         = deriveEncoder[FatherName]
  implicit val motherNameEncoder: Encoder[MotherName]         = deriveEncoder[MotherName]
  implicit val nameGroupEncoder: Encoder[NameGroup]           = deriveEncoder[NameGroup]
  implicit val birthPlaceEncoder: Encoder[BirthPlace]         = deriveEncoder[BirthPlace]
  implicit val birthPlaceCodeEncoder: Encoder[BirthPlaceCode] = deriveEncoder[BirthPlaceCode]
  implicit val birthDateEncoder: Encoder[BirthDate]           = deriveEncoder[BirthDate]
  implicit val identityNumberEncoder: Encoder[IdentityNumber] = deriveEncoder[IdentityNumber]
  implicit val addressEncoder: Encoder[Address]               = deriveEncoder[Address]
  implicit val addressCodeEncoder: Encoder[AddressCode]       = deriveEncoder[AddressCode]
  implicit val statusCodeEncoder: Encoder[StatusCode]         = deriveEncoder[StatusCode]
  implicit val chequeAccountNumberEncoder: Encoder[ChequeAccountNumber] =
    deriveEncoder[ChequeAccountNumber]
  implicit val drawingDateEncoder: Encoder[DrawingDate]       = deriveEncoder[DrawingDate]
  implicit val submissionDateEncoder: Encoder[SubmissionDate] = deriveEncoder[SubmissionDate]
  implicit val chequeSerialNumberEncoder: Encoder[ChequeSerialNumber] =
    deriveEncoder[ChequeSerialNumber]
  implicit val chequeSequenceNumberEncoder: Encoder[ChequeSequenceNumber] =
    deriveEncoder[ChequeSequenceNumber]
  implicit val chequeAmountEncoder: Encoder[ChequeAmount]       = deriveEncoder[ChequeAmount]
  implicit val paymentTypeEncoder: Encoder[PaymentType]         = deriveEncoder[PaymentType]
  implicit val paymentDateEncoder: Encoder[PaymentDate]         = deriveEncoder[PaymentDate]
  implicit val bankCodeEncoder: Encoder[BankCode]               = deriveEncoder[BankCode]
  implicit val branchCodeEncoder: Encoder[BranchCode]           = deriveEncoder[BranchCode]
  implicit val isSharedAccountEncoder: Encoder[IsSharedAccount] = deriveEncoder[IsSharedAccount]
  implicit val personTypeEncoder: Encoder[PersonType]           = deriveEncoder[PersonType]
  implicit val taxNumberEncoder: Encoder[TaxNumber]             = deriveEncoder[TaxNumber]
  implicit val originalChequeAmountEncoder: Encoder[OriginalChequeAmount] =
    deriveEncoder[OriginalChequeAmount]
  implicit val overdraftChequeAnouncementLineEncoder: Encoder[OverdraftChequeAnouncementLine] =
    deriveEncoder[OverdraftChequeAnouncementLine]

}
