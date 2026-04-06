import doodle.core.Color
import doodle.image.Image
import doodle.image.Image.Elements.Rectangle
import doodle.image.syntax.all.ImageOps
import doodle.java2d.{java2dRenderer, java2dFrame}
import cats.effect.unsafe.implicits.global

/*
  The background for the Canadian Flag (without the maple leaf) is made of
  three "bands": red, white, and red.
  Write an expression to produce that background.

  If you want to get the details right, officially the overall flag has
  proportions 1:2, and the band widths are in the ratio 1:2:1. So:
    rwwr
    rwwr
  You can use Rectangle(width, height) with appropriate width and height values.
  You could also use Image.square, then stack up multiple squares the right way.
  To get rid of black separating edges, use .strokeWidth(0)
 */

/*  Solution 1: using 3 rectangles */
def rect(w: Double, h: Double, c: Color): Image =
  Rectangle(w, h).fillColor(c).strokeWidth(0)

val redBand = rect(200, 400, Color.red)
val whiteBand = rect(400, 400, Color.white)
val canadaFlag1 = redBand beside whiteBand beside redBand

/*  Solution 2: using 8 squares */
def sqr(s: Double, c: Color): Image =
  Image.square(s).fillColor(c).strokeWidth(0)

val redSq = sqr(200, Color.red)
val whiteSq = sqr(200, Color.white)
val row = redSq beside whiteSq beside whiteSq beside redSq
val canadaFlag2 = row above row

canadaFlag1.draw()
canadaFlag2.draw()
