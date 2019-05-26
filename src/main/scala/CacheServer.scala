import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.directives.DebuggingDirectives
import utils.{ActorsConfig, Config}

object CacheServer extends App with Directives with Config with ActorsConfig {

  val routes = new CacheRoute().routes
  Http().bindAndHandle(
    DebuggingDirectives.logRequestResult("CacheServer", Logging.InfoLevel)(routes)
    , httpInterface
    , httpPort)

  println(s"Server started at http://$httpInterface:$httpPort/")
}
