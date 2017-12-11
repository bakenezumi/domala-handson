package sample

import javax.inject._
import scala.concurrent.Future

import play.api.mvc._
import play.api.libs.json._

import domala.Required
import domala.jdbc.{Config, DaoProvider}

import EmpConverter._

@Singleton
class SampleController @Inject()
(val controllerComponents: ControllerComponents)
(implicit config: Config, ec: JdbcExecutionContext) extends BaseController {

  lazy val dao: EmpDao = DaoProvider.get[EmpDao](config)

  def selectById(id: Int) = Action.async {
    Future { Required {
      dao.selectById(ID(id))
    }}.map {
      case Some(emp) => Ok(Json.toJson(emp))
      case None => NotFound("not found.")
    }
  }

  def selectAll = Action.async {
    Future { Required {
      dao.selectAll
    }} map {
      case Nil => NotFound("not found.")
      case emps => Ok(Json.toJson(emps))
    }
  }

  import scala.language.implicitConversions
  import scala.language.reflectiveCalls
  implicit def as(request: Request[AnyContent]) = new {
    def asEmp = request.body.asJson.map(_.as[Emp])
      .getOrElse(throw new RuntimeException("Request body colud not parse"))
  }

  def insert = Action.async { request =>
    Future { Required {
      dao.insert(request.asEmp)
    }} map { result => 
      Ok(Json.toJson(result))
    }
  }

  def update = Action.async { request =>
    Future { Required {
      dao.update(request.asEmp)
    }} map { result =>
      Ok(Json.toJson(result))
    }
  }

  def delete(id: Int) = Action.async {
    Future { Required {
      dao.selectById(ID(id)).map(dao.delete)
    }} map { result =>
      Ok(Json.toJson(result))
    }
  }

}
