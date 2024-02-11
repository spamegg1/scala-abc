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
// Design a function called decreasing-image that consumes a natural n
// and produces an image of all the numbers from n to 0 side by side.
// So (decreasing-image 3) should produce .
val SPACING = Text(" ")

// Natural -> Image
// produce an image of n, n-1, ... 0 side by side
decreasingImage(0) == Text("0")
decreasingImage(1) == (Text("1") beside SPACING beside Text("0"))

// def decreasingImage(n: Int): Image = Image.empty // stub
def decreasingImage(n: Int): Image =
  require(n >= 0)
  if n == 0 then Text("0")
  else Text(n.toString) beside SPACING beside decreasingImage(n - 1)

// decreasingImage(5).draw()
