package sample

import org.seasar.doma.jdbc.dialect.{Dialect, H2Dialect}
import play.api.inject.Module
import play.api.{Configuration, Environment}

class SampleModule extends Module {

  override def bindings(env: Environment, conf: Configuration) = {
    Seq(
      bind[Dialect].toInstance(getDialect(env, conf, "default")),
      bind[domala.jdbc.Config].to(classOf[AppConfig]),
      bind[JdbcExecutionContext].to(classOf[JdbcExecutionContextImpl])
    )
  }

  private def getDialect(env: Environment, conf: Configuration, dbName: String): Dialect = {
    val key = s"db.$dbName.doma.dialect"
    val dialectClassName = conf.get[String](key)
    Class
      .forName(
        dialectClassName,
        false,
        env.classLoader)
      .newInstance()
      .asInstanceOf[Dialect]
  }

}


// jdbc execution thread pool
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext

trait JdbcExecutionContext extends ExecutionContext

@Singleton
class JdbcExecutionContextImpl @Inject()(system: ActorSystem)
  extends CustomExecutionContext(system, "jdbc.executor") with JdbcExecutionContext

