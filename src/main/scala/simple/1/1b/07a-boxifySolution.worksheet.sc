import doodle.core.*
import doodle.java2d.*
import doodle.syntax.all.*
import cats.effect.unsafe.implicits.global
import doodle.language.Basic
import doodle.image.Image
import doodle.algebra.Size


// PROBLEM:
// Use the How to Design Functions (HtDF) recipe to design a function that
// consumes an image, and appears to put a box around it. Note that you can do
// this by creating an "outline" rectangle that is bigger than the image, and
// then using overlay to put it on top of the image.
// Remember, when we say DESIGN, we mean follow the recipe.
// Leave behind commented out versions of the stub and template.

// Image -> Image
// Puts a box around given image 2 pixels wider and taller than given image.
// A solution that follows the recipe but makes the box the same width and
// height is also good. It just doesn't look quite as nice.

// Tests
// (check-expect (boxify (circle 10 "solid" "red"))
//               (overlay (rectangle 22 22 "outline" "black")
//                        (circle 10 "solid" "red")))
// (check-expect (boxify (star 40 "solid" "gray"))
//               (overlay (rectangle 67 64 "outline" "black")
//                        (star 40 "solid" "gray")))

// def boxify(i: Image): Image = Image.empty // stub

// template
// def boxify(i: Image): Image = somefun(i.w, i.h)

// solution
// def boxify(i: Image): Image = ???

val redCircle = Picture.circle(100).strokeColor(Color.red)
val twoRedCircles = redCircle.beside(redCircle)
val sixRedCircles = twoRedCircles above twoRedCircles above twoRedCircles

sixRedCircles.width
// circle[Basic](100)
