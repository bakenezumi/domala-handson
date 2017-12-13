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

  def selectAll = Action.async {
    Future { Required {
      dao.selectAll
    }} map {
      case Nil => NotFound("not found.")
      case emps => Ok(Json.toJson(emps))
    }
  }

  private def toEmp(request: Request[AnyContent]) =
    request.body.asJson.map(_.as[Emp])
      .getOrElse(throw new RuntimeException("Request body colud not parse"))

  def update(id: Int) = Action.async { request =>
    Future { Required {
      dao.update(toEmp(request).copy(id = ID(id)))
    }} map { result =>
      Ok(Json.toJson(result))
    }
  }


}
