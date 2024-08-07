import doodle.core.Color
import doodle.image.Image
import doodle.image.examples.Stars
import doodle.syntax.all.AngleIntOps
import doodle.image.syntax.all.ImageOps
import doodle.java2d.{java2dRenderer, java2dFrame}
import cats.effect.unsafe.implicits.global

/*
  Write an expression to produce an image with 3 concentric stars:
    blue on the outside, yellow in the middle, blue inside.

  Don't worry about the exact size of the stars.
  Use Stars.star(sides, skip, radius)
 */
def makeStar(r: Double, c: Color) =
  Stars
    .star(5, 2, r)
    .fillColor(c)
    .strokeWidth(0)

val smallStar = makeStar(120, Color.blue)
val midStar = makeStar(240, Color.yellow)
val bigStar = makeStar(360, Color.blue)
val threeStars = smallStar on midStar on bigStar rotate -54.degrees

threeStars.draw()
