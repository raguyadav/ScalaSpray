package config

import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Try

/**
 * Holds service configuration settings
 */
trait Configuration {

  // Application config object
  val config = ConfigFactory.load()

  // Host name or address to start service on
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse()
  lazy val servicePort = Try(config.getString("service.port")).getOrElse()

  // Database set up
   val dbHost = Try(config.getString("dbhost")).getOrElse()
   val dbPort = Try(config.getInt("db.port")).getOrElse(3306)
   val dbName = Try(config.getString("db.name")).getOrElse()
   val dbUser = Try(config.getString("db.user")).toOption.orNull
   val dbPassword = Try(config.getString("db.password")).toOption.orNull

  class dbSettings(config: Config) {

    // non lazy fields , we want all exceptions at construct time
    val dbHost = Try(config.getString("dbhost")).getOrElse()
    val dbPort = Try(config.getInt("db.port")).getOrElse(3306)
    val dbName = Try(config.getString("db.name")).getOrElse()
    val dbUser = Try(config.getString("db.user")).toOption.orNull
    val dbPassword = Try(config.getString("db.password")).toOption.orNull

  }


}
