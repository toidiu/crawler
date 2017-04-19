package com.toidiu.scrape

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.toidiu.timer.TimerExecute
import play.api.libs.ws._
import play.api.libs.ws.ahc._

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

object ScrapeExecute extends TimerExecute {

  // Create Akka system for thread and streaming management
  private implicit val system = ActorSystem()
  system.registerOnTermination {
    System.exit(0)
  }
  private implicit val materializer = ActorMaterializer()
  private val wsClient = StandaloneAhcWSClient()


  def execute: Unit = {
    call(wsClient)
  }

  private def call(wsClient: StandaloneWSClient): Future[Unit] = {
    wsClient.url("http://www.google.com").get().map { response ⇒
      val statusText: String = response.statusText
      println(s"Got a response $statusText")
    }
  }

  def shutdown = {
    Future(wsClient.close())
      .andThen { case _ => system.terminate() }
  }

}
