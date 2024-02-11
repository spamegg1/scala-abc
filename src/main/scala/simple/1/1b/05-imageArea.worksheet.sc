import doodle.core.{Color, BoundingBox}
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import doodle.java2d.*
import doodle.language.Basic
import doodle.algebra.{Picture, Text, Size}
import cats.implicits.*
import cats.effect.unsafe.implicits.global

/** DESIGN a function called image-area that consumes an image and produces the
  * area of that image. For the area it is sufficient to just multiple the
  * image's width by its height. Follow the HtDF recipe and leave behind
  * commented out versions of the stub and template.
  */

// tests
imageArea(Rectangle(3, 4)) == 12

// def imageArea(i: Image): Double = ??? // stub

// Template
// def imageArea(i: Image): Double = i.w ??? i.h

/** produce the area of the consumed image (width * height)
  *
  * @param i
  *   the image (for now, a Rectangle)
  * @return
  *   area of image (approximate)
  */
def imageArea(i: Rectangle): Double = i.w * i.h

val aCircle = circle[Algebra](100) // Circle with diameter 100
// aCircle.draw()
aCircle.width

val img = Rectangle(100, 200) beside Triangle(200, 100)
// img.toPicture[Image](img)
