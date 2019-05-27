package utils

import com.typesafe.config.ConfigFactory

trait Config {
  val config: com.typesafe.config.Config = ConfigFactory.load("reference.conf")

  private val httpConfig = config.getConfig("http")
  val httpInterface: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")

  private val redisConfig = config.getConfig("redis")
  val redisHost: String = System.getenv("REDIS_MASTER_SERVICE_HOST") match {
    case null => redisConfig.getString("host")
    case host => host
  }
  val redisPort: Int = System.getenv("REDIS_MASTER_SERVICE_PORT") match {
    case null => redisConfig.getInt("port")
    case port => port.toInt
  }
}