package C.w1

import scalafx.scene.paint.Color.*

object Constants:
  val Width     = 600
  val Height    = 1200
  val SleepTime = 100
  val BlockSize = 15
  val Cols      = 10
  val Rows      = 27
  val Colors    = Seq(DarkGreen, DarkBlue, DarkRed, Gold, Purple)

extension [T](seq: Seq[T]) def sample = seq(util.Random.nextInt(seq.size))
