import doodle.core.Color
import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global
import math.{pow, sqrt}

// PROBLEM:
// In interactive games it is often useful to be able to determine the distance
// between two points on the screen. We can describe those points using
// Cartesian coordinates as four numbers: x1, y1 and x2, y2.
// The formula for the distance between those points is:
// Use the How to Design Functions (HtDF) recipe to design a function called
// distance that consumes four numbers representing two points and produces the
// distance between the two points.
// Use (distance 3 0 0 4), which should produce 5 as your first example/test.
// Once your function works with that test, try (distance 1 0 0 1) which should
// produce (sqrt 2).
// Remember, when we say DESIGN, we mean follow the recipe.
// Leave behind commented out versions of the stub and template.
// NOTE:
// The signature for such a function is:
// Number Number Number Number -> Number
// The template for such a function is:
// (define (distance x1 y1 x2 y2)
//   (... x1 y1 x2 y2))


// Number Number Number Number -> Number
// produce cartesian distance between (x1, y1) and (x2, y2)
distance(3, 0, 0, 4) == 5.0
distance(0, 1, 1, 0) == sqrt(2)

// def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double = 0.0 // stub

// template
// def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double =
//   ???(x1, y1, x2, y2)

def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double =
  sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2))
