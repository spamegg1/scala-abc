import doodle.image.Image
import doodle.image.Image.Elements.Rectangle
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

val i1 = Rectangle(100, 200)
val i2 = Rectangle(200, 200)
val i3 = Rectangle(200, 100)

// i1.draw()

// Image -> String
// produce shape of image, one of "tall", "square" or "wide"
aspectRatio(i1) == "tall"
aspectRatio(i2) == "square"
aspectRatio(i3) == "wide"

def aspectRatio(i: Image): String = "" // stub

// template
// def aspectRatio(i: Image): String =
//   if ???
//   then ???
//   else if ???
//   then ???
//   else ???

def aspectRatio(i: Rectangle): String =
  if i.h < i.w
  then "wide"
  else if i.h == i.w
  then "square"
  else "tall"

//  Problem:
//  Given the following definition:
def absVal(n: Int): Int =
  if n > 0 then n
  else if n < 0 then -n
  else 0

//  Hand step the execution of:
absVal(-3)

if -3 > 0 then -3 else if -3 < 0 then -(-3) else 0
if false then -3 else if -3 < 0 then -(-3) else 0
if -3 < 0 then -(-3) else 0
if true then -(-3) else 0
// -(-3)
// 3
