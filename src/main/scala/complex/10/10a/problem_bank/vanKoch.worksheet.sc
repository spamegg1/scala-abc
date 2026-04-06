import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
// import doodle.image.Image.Elements.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import scala.util.Random

// PROBLEM:
// First review the discussion of the Van Koch Line fractal at:
// http://pages.infinit.net/garrick/fractals/.
// Now design a function to draw a SIMPLIFIED version of the fractal.
// For this problem you will draw a simplified version as follows:
// Notice that the difference here is that the vertical parts of the
// curve, or segments BC and DE in this figure .
// are just ordinary lines they are not themselves recursive Koch curves.
// That ends up making things much simpler in terms of the math required
// to draw this curve.
// We want you to make the function consume positions using
// DrRacket's posn structure. A reasonable data definition for these
// is included below.
// Add a simplified Koch fractal to image of length ln, going from p1 to p2
// Include a termination argument for your function.
// We've also given you some constants and two other functions
// below that should be useful.

def kochElements(
    depth: Int,
    start: Point,
    angle: Angle,
    length: Double
): Seq[PathElement] =
  if depth == 0 then Seq(PathElement.lineTo(start + Vec.polar(length, angle)))
  else
    val lAngle = angle + 90.degrees
    val rAngle = angle - 90.degrees

    val third = length / 3.0
    val edge = Vec.polar(third, angle)

    val mid1 = start + edge
    val mid2 = mid1 + edge.rotate(90.degrees)
    val mid3 = mid2 + edge
    val mid4 = mid3 + edge.rotate(-90.degrees)

    kochElements(depth - 1, start, angle, third) ++
      kochElements(0, mid1, lAngle, third) ++
      kochElements(depth - 1, mid2, angle, third) ++
      kochElements(0, mid3, rAngle, third) ++
      kochElements(depth - 1, mid4, angle, third)

def koch(depth: Int, length: Double): Image =
  val origin = Point.cartesian(0, length / 6)
  val element = PathElement.moveTo(origin)
  val elements = element +: kochElements(depth, origin, 0.degrees, length)
  Image.path(OpenPath(elements.toList))

// koch(3, 1400).draw()
