package com.example

import scala.concurrent.Await
import scala.concurrent.duration._

object Boot extends App {
  private val city = "Bangkok"
  val w = Await.result(Weather.weather(city), 10.seconds)
  println(s"Hello! The weather in $city is $w")
  Weather.http.close()
}
