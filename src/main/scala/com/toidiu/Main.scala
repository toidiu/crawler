package com.toidiu

import com.toidiu.scrape._
import com.toidiu.timer._

import scala.concurrent.duration._
import scala.language.postfixOps

object Main extends App {


  Timer.start(5 seconds, ScrapeExecute)

  sys.addShutdownHook{
    Timer.shutdown
    ScrapeExecute.shutdown
  }

}
