package com.toidiu.timer

import java.util.{Timer, TimerTask}

import scala.concurrent.duration._

object Timer {

  def main(args: Array[String]): Unit = {
    start(5 second, HelloExecute)

    def start(duration: FiniteDuration, e: TimerExecute): Timer = {
      val t = new Timer(false)
      t.schedule(new TimerTask {
        override def run(): Unit = e.execute
      }, 0, duration.toMillis)
      t
    }
  }

}

trait TimerExecute {
  def execute: Unit
}

object HelloExecute extends TimerExecute {
  def execute: Unit = println("hello")
}
