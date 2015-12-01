#!/bin/sh

exec scala "$0" "$@"

!#

import java.time._
import java.time.temporal._
import java.time.format._

object ttg extends App {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val now = LocalTime.now
    //println(formatter.format(now))

    val end = LocalTime.of(17,0)
    //println(formatter.format(end))

    val hoursUntil = now.until(end, ChronoUnit.HOURS)
    val minutesUntil = now.until(end, ChronoUnit.MINUTES)
    val secondsUntil = now.until(end, ChronoUnit.SECONDS)

    print("Time until end of working day: ")
    println(hoursUntil + ":" + (minutesUntil % 60) + ":" + (secondsUntil % 60))
}

ttg.main(args)
