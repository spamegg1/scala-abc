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
// In this problem, we will design an animation of throwing a water balloon.
// When the program starts the water balloon should appear on the left side
// of the screen, half-way up.  Since the balloon was thrown, it should
// fly across the screen, rotating in a clockwise fashion. Pressing the
// space key should cause the program to start over with the water balloon
// back at the left side of the screen.
// NOTE: Please include your domain analysis at the top in a comment box.
// Use the following images to assist you with your domain analysis:
// Here is an image of the water balloon:
val WATERBALLOON = Image.triangle(50, 50).fillColor(lightBlue)

// NOTE: The rotate function wants an angle in degrees as its first
// argument. By that it means Number[0, 360). As time goes by your balloon
// may end up spinning more than once, for example, you may get to a point
// where it has spun 362 degrees, which rotate won't accept.
// The solution to that is to use the modulo function as follows:
// (rotate (modulo ... 360) (text "hello" 30 "black"))
// where ... should be replaced by the number of degrees to rotate.
// NOTE: It is possible to design this program with simple atomic data,
// but we would like you to use compound data.

// Constants

val WIDTH = 600
val HEIGHT = 300
val LINEARSPEED = 2
val ANGULARSPEED = 3.degrees // optional
val TICKRATE = FiniteDuration(1, "seconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin

// Data Definitions
// BalloonState is (make-bs Number Number)
// interp. The state of a tossed balloon.
//         x is the x-coordinate in pixels
//         a is the angle of rotation in degrees
case class Balloon(x: Int, angle: Angle)

val BS1 = Balloon(10, 0.degrees)
val BS2 = Balloon(30, 15.degrees)

def fnForBalloon(balloon: Balloon) = ??? // ???(balloon.x, balloon.angle)
// Template rules used:
// - compound: 2 fields

// Functions
// BalloonState -> BalloonState
// run the animation, starting with initial balloon state bs.
// Start with (main (make-bs 0 0))
// <no tests for main functions>
def main(balloon: Balloon): Unit =
  Reactor
    .init[Balloon](balloon)
    .withOnTick(advanceBalloon) // Balloon -> Balloon
    .withTickRate(TICKRATE)
    .withRender(renderBalloon) // Balloon -> Image
    .withOnMouseClick(handleKey) // Balloon KeyEvent -> Balloon
    .animateWithFrame(FRAME)

// BalloonState -> BalloonState
// advance bs by LINEAR-SPEED and ANGULAR-SPEED
advanceBalloon(BS1) == Balloon(BS1.x + LINEARSPEED, BS1.angle - ANGULARSPEED)

// def advanceBalloon(balloon: Balloon): Balloon = balloon // stub
// Template from BalloonState
def advanceBalloon(balloon: Balloon): Balloon =
  Balloon(balloon.x + LINEARSPEED, balloon.angle - ANGULARSPEED)

// BalloonState -> Image
// Produces the bs at height bs-x rotated (remainder bs-a 360) on the MTS
renderBalloon(Balloon(1, 12.degrees)) == WATERBALLOON
  .at(Point(1, 0))
  .rotate(12.degrees)
renderBalloon(Balloon(10, 361.degrees)) == WATERBALLOON
  .at(Point(10, 0))
  .rotate(361.degrees)

// def renderBalloon(balloon: Balloon) = Image.empty // stub

// Template from BalloonState
def renderBalloon(balloon: Balloon) =
  WATERBALLOON.at(Point(balloon.x, 0)).rotate(balloon.angle)

// BalloonState KeyEvent -> BalloonState
// Resets the program so the balloon is back at the top,
// unrotated, when space bar is pressed
handleKey(Point(10, 20), Balloon(1, 12.degrees)) == Balloon(0, 0.degrees)

// def handleKey(point: Point, balloon: Balloon): Balloon =
// Balloon(0, 0.degrees) // stub

def handleKey(point: Point, balloon: Balloon): Balloon =
  Balloon(0, 0.degrees)

// main(BS2)
