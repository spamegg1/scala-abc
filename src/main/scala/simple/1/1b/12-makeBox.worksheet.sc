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

// PROBLEM:
// You might want to create boxes of different colours.
// Use the How to Design Functions (HtDF) recipe to design a function that
// consumes a color, and creates a solid 10x10 square of that colour.
// Follow the HtDF recipe and leave behind commented out versions of
// the stub and template.

// Color -> Image
// Create a box of the given color
makeBox(Color.red) == Image.square(10).fillColor(Color.red)
makeBox(Color.gray) == Image.square(10).fillColor(Color.gray)

// def makeBox(s: Color): Image = Image.empty // stub

// template
// def makeBox(s: Color): Image = Image.???().fillColor(s)

def makeBox(s: Color): Image = Image.square(10).fillColor(s)
