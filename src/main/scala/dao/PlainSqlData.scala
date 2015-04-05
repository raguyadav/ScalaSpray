package dao

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.meta.DatabaseMeta
import scala.slick.jdbc.JdbcBackend.Session

import config.Configuration

import scala.util.Try


object PlainSqlData extends Configuration {


  val driver = "com.mysql.jdbc.Driver"

  //  val url = config.getString("streaming-usage-query-service.jdbcUrl")
  //  val db = Database.forURL(dbHost, driver = driver, user = dbUser, password = dbPassword)
  //  val db = Database.forURL(url = "jdbc:mysql://%s:%d/%s".format(dbHost, dbPort, dbName),
  //   user = dbUser, password = dbPassword, driver = "com.mysql.jdbc.Driver")


  case class cityListByDistrict(cityId: Int, cityName: String, cityCountryCode: String, cityDistrict: String, cityPopulation: Int)


  // Databse Connection stuff with Slick is here
  val url = "jdbc:mysql://localhost/world?allowMultiQueries=true"
  val db = Database.forURL(url, user = dbUser, password = dbPassword, driver = "com.mysql.jdbc.Driver")

  def tupleToCityListByDistrict(x: (Int, String, String, String, Int)): cityListByDistrict = cityListByDistrict(x._1, x._2, x._3, x._4, x._5)

  val city = "Dhaka"

  def cityList = db.withSession { implicit session =>
    val cityInformationQuery = sql"""SELECT *
                                     FROM city
                                     where District = $city;""".as[(Int, String, String, String, Int)]

    cityInformationQuery.list map tupleToCityListByDistrict
  }

}





