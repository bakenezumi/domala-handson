package sample

import domala.jdbc.Result

import play.api.libs.json._
import play.api.libs.functional.syntax._

object EmpConverter {

  implicit def writesID[T] = Json.writes[ID[T]]
  implicit def readsID[T] = Json.reads[ID[T]]

  implicit def writesName = Json.writes[Name]
  implicit def readsName = Json.reads[Name]

  implicit def writesAge = Json.writes[Age]
  implicit def readsAge = Json.reads[Age]

  implicit def writesEmp = Json.writes[Emp]
  implicit def readsEmp = Json.reads[Emp]

  implicit def writesResult = Json.writes[Result[Emp]]

}

