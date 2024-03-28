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

// Growing Flowers
// Constants
val WIDTH = 800
val HEIGHT = 600
val MAXSIZE = 15
val TICKRATE = FiniteDuration(1, "seconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin
val CENTERRADIUS = 2
val CENTERCOLOR = yellow
val PETALRADIUS = 15
val PETALCOLOR = purple
val PETALANGLE = 60.degrees
val CENTER = Image.circle(CENTERRADIUS).fillColor(CENTERCOLOR)
val PETAL1 = Image.circle(PETALRADIUS).fillColor(PETALCOLOR)
val PETAL2 = PETAL1.rotate(PETALANGLE)
val PETAL3 = PETAL2.rotate(PETALANGLE)
val FLOWER = CENTER on PETAL1 on PETAL2 on PETAL3

// Data Definitions
case class Flower(x: Double, y: Double, size: Int):
  require(-WIDTH / 2 <= x && x <= WIDTH / 2)
  require(-HEIGHT / 2 <= y && y <= HEIGHT / 2)
  require(1 <= size && size <= MAXSIZE)

// interp. The position of a growing flower
//         x is the x-coordinate in pixels
//         y is the y-coordinate in pixels
//         s is the size multiplier as flower grows
val F1 = Flower(0, 0, 1)
val F2 = Flower(100, 50, 10)
val F3 = Flower(300, 150, 3)

def fnForFlower(flower: Flower) = ???
// ???(flower.x, flower.y, flower.size)

// Template rules used:
// - compound: 3 fields

// Functions
// Flower -> Flower
// run the animation, starting with initial flower f.
// Start with (main F1)
// <no tests for main functions>
def main(flower: Flower): Unit =
  Reactor
    .init[Flower](flower)
    .withOnTick(advanceFlower) // flower -> flower
    .withTickRate(TICKRATE)
    .withRender(renderFlower) // flower -> Image
    .withOnMouseClick(handleKey) // Point flower -> flower
    .run(FRAME)

// Flower -> Flower
// grow flower by increasing s by 1 (unless s is already MAXSIZE),
// enlarging the image
advanceFlower(F1) == Flower(0, 0, 2)
advanceFlower(F2) == Flower(100, 50, 11)
advanceFlower(F3) == Flower(300, 150, 4)

// def advanceFlower(flower: Flower): Flower = flower // stub
// Template from Flower
def advanceFlower(flower: Flower): Flower =
  Flower(flower.x, flower.y, math.min(flower.size + 1, MAXSIZE))

// Flower -> Image
// Produces the flower at pos fl-x and fl-y,
// with size multiplier s, on the MTS
renderFlower(F1) == FLOWER.scale(1, 1).at(Point(0, 0))
renderFlower(F2) == FLOWER.scale(10, 10).at(Point(100, 50))
renderFlower(F3) == FLOWER.scale(3, 3).at(Point(300, 150))

// def renderFlower(flower: Flower): Image = Image.empty // stub
// ; Template from Flower
def renderFlower(flower: Flower): Image = FLOWER
  .scale(flower.size, flower.size)
  .at(Point(flower.x, flower.y))

// Flower Integer Integer MouseEvent -> Flower
// produce flower with s=1 to be rendered at x,y location
// if me is button-down, otherwise produce f
handleKey(Point(0, 0), F1) == F1
handleKey(Point(-10, -10), F2) == Flower(-10, -10, 1)
handleKey(Point(30, 140), F3) == Flower(30, 140, 1)

// def handleKey(point: Point, flower: Flower): Flower = flower // stub
// <template from MouseEvent>
def handleKey(point: Point, flower: Flower): Flower =
  Flower(point.x, point.y, 1)

// main(F1)
