package boot

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import config.Configuration
import rest.RestServiceActor
import spray.can.Http
import scala.concurrent.duration._
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Sushmeet on 2015-03-28.
 */
object Boot extends App with Configuration {

  // create an actor system for application
  implicit val system = ActorSystem("spray-rest-service-example")

  // create and start rest service actor
  // val restService = system.actorOf(Props (classOf[RestServiceActor]), "rest-endpoint")
  val restService = system.actorOf(Props[RestServiceActor], "rest-endpoint")

  // start Http server with rest service actor as a handler
  implicit val timeout = Timeout(5.seconds)

  //  IO(Http) ! (Http.Bind(restService, interface = "localhost",  port = "servicePort"))
  IO(Http) ! Http.Bind(restService, interface = "localhost", port = 8383)


  // database connect method
  def connect() : Database  = {
    val url = "jdbc:mysql://localhost/world?allowMultiQueries=true"
    Database.forURL(url, user = dbUser, password = dbPassword, driver = "com.mysql.jdbc.Driver")
  }
}
