package json


/*
Json serializing and deserialising logic

 */

import spray.json
import spray.json.DefaultJsonProtocol
import dao.PlainSqlData._

object JsonProtocolForClasses extends DefaultJsonProtocol {

  implicit val cityListFormat = jsonFormat5(cityListByDistrict)
}
