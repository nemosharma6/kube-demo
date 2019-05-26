package utils

import com.typesafe.config.ConfigFactory

trait Config {
  val config: com.typesafe.config.Config = ConfigFactory.load("reference.conf")

  private val httpConfig = config.getConfig("http")
  val httpInterface: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")

  private val redisConfig = config.getConfig("redis")
  val redisHost: String = redisConfig.getString("host")
  val redisPort: Int = redisConfig.getInt("port")
}