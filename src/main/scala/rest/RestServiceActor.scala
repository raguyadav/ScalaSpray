package rest

import akka.actor.Actor
import dao.PlainSqlData
import spray.routing.HttpService
import spray.routing.Directive.pimpApply
import spray.json._
import json.JsonProtocolForClasses._


// We don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class RestServiceActor() extends Actor with RestService {

  implicit def actorRefFactory = context

  def receive = runRoute(restmethodRoute)
}


trait RestService extends HttpService {

  val restmethodRoute = path("accounts") {
    get { ctx =>

      val cityList = PlainSqlData.cityList
      ctx.complete(cityList.toJson.toString())
    }

  }
}


