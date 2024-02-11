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
import scala.util.Random

// Constants:
val BLANK = Image.square(0.0)
// Functions:
def ellipse(size: Double): Image = Image.circle(size * 5).scale(1, 2)
// PROBLEM A:
// Use build-list to write an expression (an expression, not a function) to
// produce a list of 20 ellipses ranging in width from 0 to 19.
// NOTE: Assuming n refers to a number, the expression
// (ellipse n (* 2 n) "solid" "blue") will produce an ellipse twice as tall
// as it is wide.
val ellipses = for size <- 0 until 20 yield ellipse(size)

// PROBLEM B:
// Write an expression using one of the other built-in abstract functions
// to put the ellipses beside each other in a single image like this:
// HINT: If you are unsure how to proceed, first do part A, and then design a
// traditional function operating on lists to do the job. Then think about
// which abstract list function to use based on that.
// ellipses.foldRight(BLANK)((img1, img2) => img1 beside img2).draw()

// PROBLEM C:
// By just using a different built in list function write an expression
// to put the ellipses beside each other in a single image like this:
// ellipses.foldLeft(BLANK)((img1, img2) => img1 beside img2).draw()
