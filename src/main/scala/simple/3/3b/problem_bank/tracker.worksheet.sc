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
// Design a world program that displays the current (x, y) position
// of the mouse at that current position. So as the mouse moves the
// numbers in the (x, y) display changes and its position changes.

// Display the current mouse position, at the mouse position.
// Constants:
val WIDTH = 400
val HEIGHT = 400
val TEXTSIZE = 40
val TEXTCOLOR = black
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin

// Data definitions:
// Position is Point(x, y)
// interp. position of mouse in pixels
val ORIGIN = Point(0, 0) // origin
val p1 = Point(10, 20)
val p2 = Point(WIDTH / 2, HEIGHT / 2) // top right

def fnForPoint(point: Point) = ???
// ???(point.x, point.y)

// Template rules used:
//  - compound: 2 fields

// Functions:
// Position -> Position
// called to start the mouse tracker, call with (main (make-position 0 0))
// no tests for main function
def main(point: Point): Unit =
  Reactor
    .init[Point](point)
    .withRender(renderPoint) // Point -> Image
    .withOnMouseMove(movePoint) // Point Point -> Point
    .animateWithFrame(FRAME)

// Position -> Image
// render current position at the position itself
renderPoint(p1) == Text(p1.toString).at(ORIGIN)

// def renderPoint(point: Point): Image = Image.empty // stub
def renderPoint(point: Point): Image =
  Text(point.toString).at(ORIGIN)

// Position Integer Integer MouseEvent -> Position
// changes current position world state to current mouse position
movePoint(p1, ORIGIN) == p1
movePoint(ORIGIN, p1) == ORIGIN
movePoint(p2, ORIGIN) == p2
movePoint(ORIGIN, p2) == ORIGIN

def movePoint(mouseLocation: Point, position: Point): Point = mouseLocation

// main(ORIGIN)
