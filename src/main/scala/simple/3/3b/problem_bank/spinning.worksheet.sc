import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration
import doodle.syntax.all.*
import doodle.image.syntax.all.*

// PROBLEM:
// Design a world program as follows:
// The world starts off with a small square at the center of the screen. As time
// passes, the square stays fixed at the center, but increases in size and rotates
// at a constant speed.Pressing the spacebar resets the world so that the square
// is small and unrotated.
// Starting display:
// .
// After a few seconds:
// .
// After a few more seconds:
// .
// Immediately after pressing the spacebar:
// .
// NOTE 1: Remember to follow the HtDW recipe! Be sure to do a proper domain
// analysis before starting to work on the code file.
// NOTE 2: The rotate function requires an angle in degrees as its first
// argument. By that it means Number[0, 360). As time goes by the box may end up
// spinning more than once, for example, you may get to a point where it has spun
// 362 degrees, which rotate won't accept. One solution to that is to use the
// remainder function as follows:
// (rotate (remainder ... 360) (text "hello" 30 "black"))
// where ... can be an expression that produces any positive number of degrees
// and remainder will produce a number in [0, 360).
// Remember that you can lookup the documentation of rotate if you need to know
// more about it.
// NOTE 3: There is a way to do this without using compound data. But you should
// design the compound data based solution.

// a growing and rotating box
// Constants:
val WIDTH = 700
val HEIGHT = 700
val ROTATIONSPEED = 30.degrees
val GROWTHSPEED = 10
val SQUARECOLOR = red
val TICKRATE = FiniteDuration(100, "milliseconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(skyBlue)
  .withCenterAtOrigin

// Data definitions:
case class Box(angle: Angle, side: Double):
  require(0 <= side)
// Box is (make-box Natural Natural)
// interp. a box with side length box-s, rotated (remainder box-a 360) degrees
val box0 = Box(0.degrees, 10)
val box1 = Box(30.degrees, 20)
val box2 = Box(60.degrees, 40)

def fnForBox(box: Box) = ???
// ???(box.angle, box.side)

// Template Rules Used:
// - compound: 2 fields

// Functions:
// Box -> Box
// called to initiate a growing and rotating square.
// Start with (main (make-box 0 0))
// no tests for main function
def main(box: Box): Unit =
  Reactor
    .init[Box](box)
    .withOnTick(advanceBox) // grass -> grass
    .withRender(renderBox) // grass -> Image
    .withOnMouseClick(resetBox) // Point grass -> grass
    .withTickRate(TICKRATE)
    .animateWithFrame(FRAME)

// Box -> Box
// produces next box grown by GROWTH-SPEED and rotated ROTATIONAL-SPEED
advanceBox(box1) == Box(box1.angle + ROTATIONSPEED, box1.side + GROWTHSPEED)

// def advanceBox(box: Box): Box = box // stub
def advanceBox(box: Box): Box =
  Box(box.angle + ROTATIONSPEED, box.side + GROWTHSPEED)

// Box -> Image
// draws a box at the middle of the screen with side box-s
// and rotated (remainder box-a 360) degrees
renderBox(box1) == Image
  .square(box1.side)
  .rotate(box1.angle)
  .fillColor(SQUARECOLOR)
  .at(Point(0, 0))

// def renderBox(box: Box): Image = Image.empty // stub
def renderBox(box: Box): Image = Image
  .square(box.side)
  .rotate(box.angle)
  .fillColor(SQUARECOLOR)
  .at(Point(0, 0))

// Box KeyEvent -> Box
// resets the world so the box is small and unrotated when space bar is pressed
resetBox(Point(10, 20), box1) == Box(0.degrees, 0)
resetBox(Point(30, 50), box2) == Box(0.degrees, 0)
resetBox(Point(-10, -20), box0) == Box(0.degrees, 0)
// (check-expect (handle-key (make-box 40 20) " ") (make-box 0 0))
// (check-expect (handle-key (make-box 40 20) "a") (make-box 40 20))

// def resetBox(point: Point, box: Box): Box = box // stub
def resetBox(point: Point, box: Box): Box = Box(0.degrees, 0)

// main(Box(0.degrees, 0))
