package sample

import domala.jdbc.Result

import play.api.libs.json._
import play.api.libs.functional.syntax._

object EmpConverter {

  implicit def idFormat[T] = Json.format[ID[T]]

  implicit val nameFormat = Json.format[Name]

  implicit val ageFormat = Json.format[Age]

  implicit val empFormat = Json.format[Emp]

  implicit val writesResult = Json.writes[Result[Emp]]

}
