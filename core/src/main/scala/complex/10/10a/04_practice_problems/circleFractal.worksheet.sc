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

// PROBLEM :
// Design a function that will create the following fractal:
// Each circle is surrounded by circles that are two-fifths smaller.
// You can build these images using the convenient beside and above functions
// if you make your actual recursive function be one that just produces the
// top leaf shape. You can then rotate that to produce the other three shapes.
// You don't have to use this structure if you are prepared to use more
// complex place-image functions and do some arithmetic. But the approach
// where you use the helper is simpler.
// Include a termination argument for your design.

// Functions:
// Number -> Image
// draws a fractal with leaves coming out of all four directions
// (define (circle-fractal n)
def circleFractal(depth: Int, size: Double): Image =
  val topLeaf = drawLeaf(depth - 1, size / 2.0)
  val center = Image.circle(size).fillColor(blueViolet)
  val mid =
    topLeaf.rotate(90.degrees) beside center beside topLeaf.rotate(-90.degrees)
  topLeaf above mid above topLeaf.rotate(180.degrees)

// Number -> Image
// draws the top leaf
// Termination Argument
//     trivial case: size is less than TRIVIAL-SIZE
//     reduction step: reduce size by STEP and recurse on that to build leaves
//     argument: reduction step reduces size of leaves so eventually it will be
//               less than TRIVIAL-SIZE

// <template as gen-rec>
def drawLeaf(depth: Int, size: Double): Image =
  val center = Image.circle(size).fillColor(blueViolet)
  if depth <= 0
  then center
  else
    val leaf = drawLeaf(depth - 1, size / 2)
    leaf above (
      leaf.rotate(90.degrees) beside center beside leaf.rotate(-90.degrees)
    )

// circleFractal(3, 200).draw()
