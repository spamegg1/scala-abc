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
// Design a function that consumes a natural number n and a color c,
// and produces n concentric circles of the given color.
// So (concentric-circles 5 "black") should produce .
val RADIUS = 50.0
// Natural String -> Image
// produce n concentric circles of color c
concentricCircles(0, red) == Image.empty
concentricCircles(1, blue) == Image
  .circle(RADIUS)
  .strokeColor(blue)
  .on(Image.empty)
concentricCircles(2, green) == Image
  .circle(2 * RADIUS)
  .strokeColor(green)
  .on(
    Image
      .circle(RADIUS)
      .strokeColor(green)
      .on(Image.empty)
  )

// def concentricCircles(n: Int, color: Color): Image = Image.empty // stub
def concentricCircles(n: Int, color: Color): Image =
  if n == 0 then Image.empty
  else
    Image
      .circle(n * RADIUS)
      .strokeColor(color)
      .on(concentricCircles(n - 1, color))

// concentricCircles(5, purple).draw()
