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

// PROBLEM:
// Design a function that consumes a number and produces a Sierpinski
// triangle of that size. Your function should use generative recursion.
// One way to draw a Sierpinski triangle is to:
//  - start with an equilateral triangle with side length s
//  - inside that triangle are three more Sierpinski triangles
//  - and inside each of those... and so on
// So that you end up with something that looks like this:
// Note that in the 2nd picture above the inner triangles are drawn in
// black and slightly smaller just to make them clear. In the real
// Sierpinski triangle they should be in the same color and of side
// length s/2. Also note that the center upside down triangle is not
// an explicit triangle, it is simply formed from the other triangles.

// Number -> Image
// produce a Sierpinski Triangle of the given size
// Three part termination argument.
// Base case: (<= s CUTOFF)
// Reduction step: (/ s 2)
// Argument that repeated application of reduction step will eventually
// reach the base case:
// As long as the cutoff is > 0 and s starts >=0 repeated division by 2
// will eventually be less than cutoff.
def sierpinskiTriangle(depth: Int, size: Double): Image =
  if depth == 0 then Image.equilateralTriangle(size).fillColor(skyBlue)
  else
    val smaller = sierpinskiTriangle(depth - 1, size / 2.0)
    smaller above (smaller beside smaller)

// sierpinskiTriangle(4, 900.0).draw()

// PROBLEM:
// Design a function to produce a Sierpinski carpet of size s.
// Here is an example of a larger Sierpinski carpet.
// Number -> Image
// produce Sierpinski carpet of given size
// def sierpinskiCarpet(depth: Int, size: Double): Image = Image.empty // stub

// Three part termination argument.
// Base case: (<= s CUTOFF)
// Reduction step: (/ s 3)
// Argument that repeated application of reduction step will eventually
// reach the base case:
// As long as cutoff is >= 0 and s starts > 0, repeated division by 3
// will eventually reach base case.
def block(size: Double): Image =
  Image.square(size).strokeColor(darkOliveGreen).strokeWidth(2.0)

def sierpinskiCarpet(depth: Int, size: Double): Image =
  if depth <= 0
  then block(size)
  else
    val smaller = sierpinskiCarpet(depth - 1, size / 3.0)
    val emptyBlock = smaller.strokeColor(white)
    // val emptyBlock = block(size / 3.0) // causes misalignment
    val row = smaller beside smaller beside smaller
    val mid = smaller beside emptyBlock beside smaller
    row above mid above row

// sierpinskiCarpet(4, 800.0).draw()
