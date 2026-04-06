import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM:
// You are asked to contribute to the design for a very simple New Year's
// Eve countdown display. You already have the data definition given below.
// You need to design a function that consumes Countdown and produces an
// image showing the current status of the countdown.

// Data definitions:
// Countdown is one of:
//  - false
//  - Natural[1, 10]
//  - "complete"
// interp.
//    false           means countdown has not yet started
//    Natural[1, 10]  means countdown is running and how many seconds left
//    "complete"      means countdown is over
enum Countdown:
  case NotYetStarted
  case SecondsLeft(seconds: Int) // we can't add require(???) this way.
  case Complete
import Countdown.*
// sealed trait Countdown
// case object NotYetStarted extends Countdown
// case object Complete      extends Countdown
// case class SecondsLeft(seconds: Int) extends Countdown:
//   require(1 <= seconds && seconds <= 10)

val cd1 = NotYetStarted
val cd2 = SecondsLeft(10)
val cd3 = SecondsLeft(10)
val cd4 = Complete

// Template
def fnForCountdown(cd: Countdown) = cd match
  case NotYetStarted        => ???
  case SecondsLeft(seconds) => ??? // seconds
  case Complete             => ???

// Template rules used:
//  - one of: 3 cases
//  - atomic distinct: false
//  - atomic non-distinct: Natural[1, 10]
//  - atomic distinct: "complete"

// Functions:
// Countdown -> Image
// produce nice image of current state of countdown
countdownToImage(NotYetStarted) == Image.empty
countdownToImage(SecondsLeft(5)) == Image.text("5")
countdownToImage(Complete) == Image.text("Happy New Year!")

// def countdownToImage(cd: Countdown): Image = Image.empty // stub

// <use template from Countdown>
def countdownToImage(cd: Countdown) = cd match
  case NotYetStarted        => Image.empty
  case SecondsLeft(seconds) => Image.text(s"${seconds}")
  case Complete             => Image.text("Happy New Year!")

// countdownToImage(Complete).draw()
