import utils.{ActorsConfig, Config}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import com.redis.RedisClient

class CacheRoute extends ActorsConfig with Config {

  val exceptionHandler = ExceptionHandler {
    case _: Exception =>
      extractUri { _ =>
        complete("unable to reach redis")
      }
  }

  val routes: Route =
    handleExceptions(exceptionHandler) {
      pathPrefix("set") {
        pathPrefix(Segment / Segment) {
          case (key, value) =>
            val client = new RedisClient(redisHost, redisPort)
            client.set(key, value)
            complete(s"Job done. key: $key has been set with value: $value")
        }
      } ~
        pathPrefix("get" / Segment) {
          key =>
            val client = new RedisClient(redisHost, redisPort)
            val value = client.get(key)
            value match {
              case Some(v) => complete(s"value: $v was found for key: $key")
              case None => complete(s"no value found for key: $key")
            }
        } ~
        pathEndOrSingleSlash {
          complete("Use /set/{key}/{value} to set a key in redis. Use get/{key} to fetch a value from redis")
        }
    }
}
