import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.syntax.all.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration

// A cow, meandering back and forth across the screen.
// Constants:
val WIDTH = 400
val HEIGHT = 200
val CTRY = HEIGHT / 2
val LCOW = Image.empty
val RCOW = Image.empty
val TICKRATE = FiniteDuration(1, "seconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin

// Data definitions:

// Functions:
