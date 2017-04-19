package com.toidiu.timer

import java.util.{Timer, TimerTask}

import scala.concurrent.duration._

object Timer {

  private val t = new Timer(false)

  def start(duration: FiniteDuration, e: TimerExecute): Timer = {
    t.schedule(new TimerTask {
      override def run(): Unit = e.execute
    }, 0, duration.toMillis)
    t
  }

  def shutdown = t.cancel()

}

trait TimerExecute {
  def execute: Unit
}

