package dao

import boot.Boot

import scala.slick.direct.AnnotationMapper.column
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.meta.DatabaseMeta
import scala.slick.jdbc.JdbcBackend.Session

import config.Configuration

import scala.util.Try

class Cities(tag: Tag) extends Table[(Int, String, String, String, Int)](tag, "CITY") {
  def id = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def countryCode = column[String]("COUNTRYCODE")
  def district = column[String]("DISTRICT")
  def population = column[Int]("POPULATION")

  def * = (id, name, countryCode, district, population)
}

object PlainSqlData extends Configuration {

  //  val db = Database.forURL(url = "jdbc:mysql://%s:%d/%s".format(dbHost, dbPort, dbName),
  //   user = dbUser, password = dbPassword, driver = "com.mysql.jdbc.Driver")

  case class cityListByDistrict(cityId: Int, cityName: String, cityCountryCode: String, cityDistrict: String, cityPopulation: Int)
  lazy val cities = TableQuery[Cities]
  // Database Connection stuff with Slick is here
  val db = Boot.connect()

  def tupleToCityListByDistrict(x: (Int, String, String, String, Int)): cityListByDistrict = cityListByDistrict(x._1, x._2, x._3, x._4, x._5)

  def cityListByDistrict(district: String) = db.withSession { implicit session =>
    val cityInformationQuery = sql"""SELECT *
                                     FROM city
                                     where District = $district;""".as[(Int, String, String, String, Int)]

    cityInformationQuery.list map tupleToCityListByDistrict

    /* val cityInformationQuery = sql"""SELECT *
                                      FROM city
                                      where District = $district;""".as[cityListByDistrict]

     cityInformationQuery.list
     This doesnt work but I want to know why */
  }

  def allcities = db.withSession { implicit session =>
    val cityInformationQuery =  cities.map(p => (p.id,p.name,p.countryCode,p.district,p.population))
    cityInformationQuery.list map tupleToCityListByDistrict
  }

  def getCitiesByDistrict(cityDistrict: String) = db.withSession{ implicit session =>
    val citiesByDistrictQuery = cities.filter(p => p.district === cityDistrict)
    citiesByDistrictQuery.list map tupleToCityListByDistrict
  }
}






