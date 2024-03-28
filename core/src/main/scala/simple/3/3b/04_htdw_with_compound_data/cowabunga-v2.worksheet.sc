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
case class Cow(x: Int, dx: Int):
  require(0 <= x && x <= WIDTH)
// Cow is (make-cow Natural[0, WIDTH] Integer)
// interp. (make-cow x dx) is a cow with x coordinate x and x velocity dx
//         the x is the center of the cow
//         x  is in screen coordinates (pixels)
//         dx is in pixels per tick
val c1 = Cow(10, 3) // at 10, moving left -> right
val c2 = Cow(20, -4) // at 20, moving left <- right

def fnForCow(cow: Cow) = ??? // ???(cow.x, cow.dx)
// Template rules used:
//  - compound: 2 fields

// Functions:
// Cow -> Cow
// called to make the cow go for a walk; start with (main (make-cow 0 3))
// no tests for main function
def main(cow: Cow): Unit =
  Reactor
    .init[Cow](cow)
    // .withOnTick(advanceCow) // Cow -> Cow
    // .withTickRate(TICKRATE)
    // .withRender(renderCow) // Cow -> Image
    // .withOnMouseClick(handleKey) // Cow KeyEvent -> Cow
    .run(FRAME)

// Cow -> Cow
// increase cow x by dx; bounce off edges
// TESTS HERE
def advanceCow(cow: Cow): Cow = cow // stub

// Cow -> Image
// place appropriate cow image on MTS at (cow-x c) and CTR-Y
// TESTS HERE
def renderCow(cow: Cow): Image = Image.empty // stub

// Cow KeyEvent-> Cow
// reverse direction of cow travel when space bar is pressed
// TESTS HERE
def handleKey(point: Point, cow: Cow): Cow = cow // stub
