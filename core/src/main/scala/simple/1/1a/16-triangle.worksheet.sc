import doodle.core.Color
import doodle.image.Image
import doodle.image.Image.Elements.Triangle
import doodle.syntax.all.AngleIntOps
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

/*
  Write an expression to produce an image of two triangles, one green,
  one yellow; one upside down, on top of each other, partially overlaid,
  like this:

    ggggggg     y     gggyggg
     ggggg  +  yyy  =  gyyyg
      ggg     yyyyy    yyyyy
       g     yyyyyyy  yyyyyyy

  Don't worry about the exact size of the triangles.
  Use Triangle(w, h), rotate, and on.
 */
def makeTriangle(c: Color) = Triangle(400, 400).fillColor(c)

val greenTri = makeTriangle(Color.green)
val yellowTri = makeTriangle(Color.yellow)
val twoTriangles = yellowTri on (greenTri rotate 180.degrees)

twoTriangles.draw()
