// PROBLEM:
// Consider designing the system for controlling a New Year's Eve
// display. Design a data definition to represent the current state
// of the countdown, which falls into one of three categories:
//  - not yet started
//  - from 10 to 1 seconds before midnight
//  - complete (Happy New Year!)

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
// case class SecondsLeft(seconds: Int) extends Countdown:
//   require(1 <= seconds && seconds <= 10)
// case object Complete extends Countdown

val countdown1 = NotYetStarted
val countdown2 = SecondsLeft(10) // just started running
val countdown3 = SecondsLeft(1) // almost over
val countdown4 = Complete
// val wrong = SecondsLeft(11) // IllegalArgumentException

// Template
def fnForCountdown(countdown: Countdown) = countdown match
  case NotYetStarted        => ???
  case SecondsLeft(seconds) => ???
  case Complete             => ???

// Template rules used:
//  - one of: 3 cases
//  - atomic distinct: false
//  - atomic non-distinct: Natural[1, 10]
//  - atomic distinct: "complete"
