package com.example

import gigahorse.support.okhttp.Gigahorse
import scala.concurrent._, duration._
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Weather {
  lazy val http = Gigahorse.http(Gigahorse.config)

  def weather(city: String = "New York"): Future[String] = {
    val baseUrl = "https://www.metaweather.com/api/location"
    val locUrl = baseUrl + "/search"
    val weatherUrl = baseUrl + "/%s/"
    val rLoc = Gigahorse.url(locUrl).get
      .addQueryString("query" -> city)

    for {
      loc <- http.run(rLoc, parse)
      woeid = (loc \ 0 \ "woeid").get
      rWeather = Gigahorse.url(weatherUrl.format(woeid)).get
      weather <- http.run(rWeather, parse)
    } yield (weather \\ "weather_state_name").head.as[String].toLowerCase()
  }

  private def parse = Gigahorse.asString andThen Json.parse
}
