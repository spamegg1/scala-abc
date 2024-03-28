import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.syntax.all.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global

// Constants
val COOKIES = Image.circle(20).fillColor(brown)

// Data Definitions
// Natural is one of:
//  - 0
//  - (add1 Natural)
// interp. a natural number
sealed trait Natural:
  def value: Int
case object Zero extends Natural:
  def value = 0
case class Succ(n: Natural) extends Natural:
  def value = n.value + 1

val n0 = Zero
val n1 = Succ(n0)
val n2 = Succ(n1)
n0.value
n1.value
n2.value

// Template
def fnForNatural(n: Natural) = n match
  case Zero    => ???
  case Succ(m) => ???

// Template rules used:
//  - one-of: two cases
//  - atomic distinct: 0
//  - compound: 2 fields
//  - self-reference: (sub1 n) is Natural

// PROBLEM 1:
// Complete the design of a function called pyramid that takes a natural
// number n and an image, and constructs an n-tall, n-wide pyramid of
// copies of that image.
// For instance, a 3-wide pyramid of cookies would look like this:

// Natural Image -> Image
// produce an n-wide pyramid of the given image
pyramid(n0, COOKIES) == Image.empty
pyramid(n1, COOKIES) == COOKIES
pyramid(n2, COOKIES) == (COOKIES above (COOKIES beside COOKIES))

// def pyramid(n: Natural, img: Image) = Image.empty // stub
def pyramid(n: Natural, img: Image): Image = n match
  case Zero       => Image.empty
  case Succ(Zero) => img
  case Succ(m)    => pyramid(m, img) above line(n.value, img)

line(0, COOKIES) == Image.empty
line(1, COOKIES) == COOKIES
line(2, COOKIES) == (COOKIES beside COOKIES)

// Natural Image -> Image
// produce n copies of image beside each other
// def helper(i: Int, img: Image): Image = Image.empty // stub
def line(i: Int, img: Image): Image = i match
  case 0 => Image.empty
  case 1 => img
  case _ => img beside line(i - 1, img)

// Problem 2:
// Consider a test tube filled with solid blobs and bubbles.  Over time the
// solids sink to the bottom of the test tube, and as a consequence the bubbles
// percolate to the top.  Let's capture this idea in BSL.
// Complete the design of a function that takes a list of blobs and sinks each
// solid blob by one. It's okay to assume that a solid blob sinks past any
// neighbor just below it.
// To assist you, we supply the relevant data definitions.
// Blob is one of:
// - "solid"
// - "bubble"
// interp.  a gelatinous blob, either a solid or a bubble
// Examples are redundant for enumerations
enum Blob:
  case Solid, Bubble

import Blob.*

// Template
def fnForBlob(blob: Blob) = blob match
  case Solid  => ???
  case Bubble => ???
// Template rules used:
// - one-of: 2 cases
// - atomic distinct: "solid"
// - atomic distinct: "bubble"

// ListOfBlob is one of:
// - empty
// - (cons Blob ListOfBlob)
// interp. a sequence of blobs in a test tube, listed from top to bottom.

def fnForListofBlob(list: List[Blob]) = list match
  case Nil            => ???
  case Bubble :: next => ??? // template blending!
  case Solid :: next  => ???

// Template rules used
// - one-of: 2 cases
// - atomic distinct: empty
// - compound: 2 fields
// - reference: (first lob) is Blob
// - self-reference: (rest lob) is ListOfBlob

// ListOfBlob -> ListOfBlob
// produce a list of blobs that sinks the given solid blobs by one
sink(Nil) == Nil
sink(List(Bubble)) == List(Bubble)
sink(List(Solid)) == List(Solid)
sink(List(Bubble, Bubble)) == List(Bubble, Bubble)
sink(List(Bubble, Solid)) == List(Bubble, Solid)
sink(List(Solid, Bubble)) == List(Bubble, Solid)
sink(List(Solid, Solid)) == List(Solid, Solid)
sink(List(Bubble, Bubble, Bubble)) == List(Bubble, Bubble, Bubble)
sink(List(Bubble, Bubble, Solid)) == List(Bubble, Bubble, Solid)
sink(List(Bubble, Solid, Bubble)) == List(Bubble, Bubble, Solid)
sink(List(Solid, Bubble, Bubble)) == List(Bubble, Solid, Bubble)
sink(List(Bubble, Solid, Solid)) == List(Bubble, Solid, Solid)
sink(List(Solid, Bubble, Solid)) == List(Bubble, Solid, Solid)
sink(List(Solid, Solid, Bubble)) == List(Bubble, Solid, Solid)
sink(List(Solid, Solid, Solid)) == List(Solid, Solid, Solid)
sink(List(Solid, Solid, Bubble, Bubble)) == List(Bubble, Solid, Solid, Bubble)

// def sink(list: List[Blob]): List[Blob] = Nil // stub
// <template from ListOfBlob>
def sink(list: List[Blob]): List[Blob] = list match
  case Nil            => Nil
  case Bubble :: rest => Bubble :: sink(rest)
  case Solid :: rest  => helper(sink(rest))

// ListOfBlob -> ListOfBlob
// helper function
// ASSUMES that lob is already "sunk" properly
helper(Nil) == List(Solid)
helper(List(Bubble)) == List(Bubble, Solid)
helper(List(Solid)) == List(Solid, Solid)
helper(List(Bubble, Bubble)) == List(Bubble, Solid, Bubble)
helper(List(Bubble, Solid)) == List(Bubble, Solid, Solid)

// def helper(sunk: List[Blob]) = Nil // stub
def helper(sunk: List[Blob]): List[Blob] = sunk match
  case Nil          => List(Solid)
  case head :: next => head :: Solid :: next
