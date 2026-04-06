import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.syntax.all.*
import doodle.reactor.syntax.all.animateWithFrame
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration

// PROBLEM:
// Design an animation of a simple countdown.
// Your program should display a simple countdown, that starts at ten, and
// decreases by one each clock tick until it reaches zero, and stays there.
// To make your countdown progress at a reasonable speed, you can use the
// rate option to on-tick. If you say, for example,
// (on-tick advance-countdown 1) then big-bang will wait 1 second between
// calls to advance-countdown.
// Remember to follow the HtDW recipe! Be sure to do a proper domain
// analysis before starting to work on the code file.
// Once you are finished the simple version of the program, you can improve
// it by reseting the countdown to ten when you press the spacebar.

// A simple countdown animation.

// Constants:
val WIDTH = 50
val HEIGHT = 50
val CTRX = WIDTH / 2
val CTRY = HEIGHT / 2
val TEXTSIZE = 24
val TEXTCOLOR = black
val TICKRATE = FiniteDuration(1, "seconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin

// Data definitions:
// Countdown is Natural[0, 10]
// interp. the current seconds remaining in the countdown
case class Countdown(n: Int):
  require(0 <= n && n <= 10)

val CD1 = Countdown(10) // countdown hasn't started
val CD2 = Countdown(5) // countdown in progress
val CD3 = Countdown(0) // countdown finished

// Template
def fnForCountdown(cd: Countdown) = cd.n match
  case _ => ???

// Template rules used:
// - atomic non-distinct: Natural[0, 10]

// Functions:
// Countdown -> Countdown
// called to run the animation, start with main(Countdown(10))
// no tests for main function
def main(countdown: Countdown): Unit =
  Reactor
    .init[Countdown](countdown)
    .withOnTick(advanceCountdown) // Countdown -> Countdown
    .withTickRate(TICKRATE)
    .withRender(renderCountdown) // Countdown -> Image
    .withOnMouseClick(handleKey) // Countdown KeyEvent -> Countdown
    .animateWithFrame(FRAME)

// Countdown -> Countdown
// if cd is zero, produce zero, otherwise subtract 1
advanceCountdown(Countdown(10)) == Countdown(9)
advanceCountdown(Countdown(1)) == Countdown(0)
advanceCountdown(Countdown(0)) == Countdown(0)

// def advanceCountdown(countdown: Countdown) = Countdown(0) //  stub
// <template from Countdown>
def advanceCountdown(countdown: Countdown) = countdown.n match
  case 0 => countdown
  case m => Countdown(m - 1)

// Countdown -> Image
// produce an appropriate image for the countdown
renderCountdown(Countdown(10)) == Text("10")
renderCountdown(Countdown(1)) == Text("1")
renderCountdown(Countdown(0)) == Text("0")

// def renderCountdown(countdown: Countdown): Image = Image.empty // stub
// <template from Countdown>
def renderCountdown(countdown: Countdown) = countdown.n match
  case m => Text(m.toString)

// Countdown KeyEvent -> Countdown
// reset the countdown to 10 when the spacebar is pressed
handleKey(Point(1, 2), Countdown(10)) == Countdown(10)
handleKey(Point(3, 4), Countdown(1)) == Countdown(10)
handleKey(Point(5, 6), Countdown(0)) == Countdown(10)

// def handleKey(point: Point, countdown: Countdown): Countdown = Countdown(0) // stub
// <template from KeyEvent>
def handleKey(point: Point, countdown: Countdown): Countdown =
  Countdown(10)

// main(Countdown(10))
