import doodle.core.Color
import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM:
// Design a function that consumes a number and produces a blue solid triangle
// of that size. You should use The How to Design Functions (HtDF) recipe, and
// your complete design should include signature, purpose, commented out stub,
// examples/tests, commented out template and the completed function.

// Natural -> Image
// Given a number, produce a blue solid triangle of that size.

// tests
blueTriangle(7) == Image.triangle(7, 7).fillColor(Color.blue)
blueTriangle(50) == Image.triangle(50, 50).fillColor(Color.blue)
blueTriangle(100) == Image.triangle(100, 100).fillColor(Color.blue)

// def blueTriangle(size: Int): Image = Image.empty // stub

// template
// def blueTriangle(size: Int): Image = Image.???(size).???(???)

def blueTriangle(size: Int): Image =
  Image
    .triangle(size, size)
    .fillColor(Color.blue)
