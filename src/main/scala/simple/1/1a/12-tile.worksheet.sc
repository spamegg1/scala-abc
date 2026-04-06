import doodle.core.Color
import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.{java2dRenderer, java2dFrame}
import cats.effect.unsafe.implicits.global

/*
  Use Image.square to create a 2x2 tile of yellow/blue checkered squares:
    yb
    by
 */
def colorSquare(c: Color): Image = Image.square(200).fillColor(c)

val yellowSquare = colorSquare(Color.yellow)
val blueSquare = colorSquare(Color.blue)

val yellowBlueRow = yellowSquare beside blueSquare
val blueYellowRow = blueSquare beside yellowSquare

val checkers = yellowBlueRow above blueYellowRow
checkers.draw()
