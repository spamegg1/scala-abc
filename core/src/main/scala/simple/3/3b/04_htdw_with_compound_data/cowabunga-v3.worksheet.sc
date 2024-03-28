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
val WIDTH = 600
val HEIGHT = 300
val LCOW = Image.circle(200).fillColor(red)
val RCOW = Image.circle(200).fillColor(green)
val TICKRATE = FiniteDuration(1, "seconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin

// Data definitions:
case class Cow(x: Int, dx: Int):
  require(-WIDTH / 2 <= x && x <= WIDTH / 2)
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
    .withOnTick(advanceCow) // Cow -> Cow
    .withTickRate(TICKRATE)
    .withRender(renderCow) // Cow -> Image
    .withOnMouseClick(handleKey) // Cow KeyEvent -> Cow
    .run(FRAME)

// Cow -> Cow
// increase cow x by dx; bounce off edges
// when gets to edge, change dir and move off by 1
// away from edges
advanceCow(Cow(20, 3)) == Cow(23, 3)
advanceCow(Cow(20, -3)) == Cow(17, -3)
// reaches edge
advanceCow(Cow(WIDTH / 2 - 3, 3)) == Cow(WIDTH / 2, 3)
advanceCow(Cow(3, -3)) == Cow(0, -3)
// tries to pass edge, reverses
advanceCow(Cow(WIDTH / 2 - 2, 3)) == Cow(WIDTH / 2, -3)
advanceCow(Cow(-WIDTH / 2 + 2, -3)) == Cow(-WIDTH / 2, 3)

// def advanceCow(cow: Cow): Cow = cow // stub
def advanceCow(cow: Cow): Cow =
  if cow.x + cow.dx > WIDTH / 2
  then Cow(WIDTH / 2, -cow.dx)
  else if cow.x + cow.dx < -WIDTH / 2
  then Cow(-WIDTH / 2, -cow.dx)
  else Cow(cow.x + cow.dx, cow.dx)

// Cow -> Image
// place appropriate cow image on MTS at (cow-x c) and 0
// TESTS HERE
// def renderCow(cow: Cow): Image = Image.empty // stub
def renderCow(cow: Cow): Image =
  if cow.dx < 0
  then LCOW.at(Point(cow.x, 0))
  else RCOW.at(Point(cow.x, 0))

// Cow KeyEvent-> Cow
// reverse direction of cow travel when mouse is clicked
// TESTS HERE
def handleKey(point: Point, cow: Cow): Cow = Cow(cow.x, -cow.dx)

// main(Cow(0, 30))
