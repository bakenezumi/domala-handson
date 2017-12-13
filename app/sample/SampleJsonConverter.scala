package sample

import domala.jdbc.Result

import play.api.libs.json._
import play.api.libs.functional.syntax._

object EmpConverter {

  implicit def writesID[T] = Writes[ID[T]] { case ID(value) => JsNumber(value) }
  implicit def readsID[T] = Reads[ID[T]] { json => json.validate[Int] map (value => ID[T](value)) }

  implicit def writesName[T] = Writes[Name] { case Name(value) => JsString(value) }
  implicit def readsName[T] = Reads[Name] { json => json.validate[String] map (value => Name(value)) }

  implicit def writesAge[T] = Writes[Age] { case Age(value) => JsNumber(value) }
  implicit def readsAge[T] = Reads[Age] { json => json.validate[Int] map (value => Age(value)) }

  implicit def writesSex[T] = Writes[Sex] {
    case Sex.Male => JsString("Male")
    case Sex.Female => JsString("Female")
    case Sex.Other => JsString("Other")
  }
  implicit def readsSex[T] = Reads[Sex] { json => json.validate[String] map {
    case "Male" => Sex.Male
    case "Female" => Sex.Female
    case _ => Sex.Other
  }}

  implicit def writesEmp = Json.writes[Emp]
  implicit def readsEmp = Json.reads[Emp]

  implicit def writesResult = Json.writes[Result[Emp]]

}
