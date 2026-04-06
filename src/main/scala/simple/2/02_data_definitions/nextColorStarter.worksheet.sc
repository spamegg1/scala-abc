// Suppose you are working on a program someone else wrote
// that simulates traffic. In the program there are traffic
// lights and cars and streets and things like that. While
// reading the program you come across this function:

// def nextColor(c: Int): Int = c match
//   case 0 => 2
//   case 1 => 0
//   case 2 => 1

// What does it do? The name is a hint, it seems to produce
// the "next color". But its hard to be sure.
// Surely if the programmer had followed the HtDF recipe
// this would be better wouldn't it? Suppose instead the
// code looked like this.

// Natural -> Natural
// produce next color of traffic light
nextColor(0) == 2
nextColor(1) == 0
nextColor(2) == 1

// def nextColor(c: Int) = 0 // stub

// template
// def nextColor(c: Int) = c match
//   case ??? => ???
//   case _ => ???

def nextColor(c: Int): Int = c match
  case 0 => 2
  case 1 => 0
  case 2 => 1

// That's a little better. At least it is now clear that
// the function does produce the next color. And the tests
// make it clear that the function is really supposed to
// produce 2 when it is called with 0. But what are the
// 0, 1 and 2 about? And what about calling the function
// with 3? The signature says that is OK, but the cond
// in the body will signal an error in that case.
// A small part of a traffic simulation.
// Data definitions:
// TLColor is one of:
//  - Red
//  - Yellow
//  - Green
// interp. Red means red, Yellow yellow, Green green
enum TlColor:
  case Red, Yellow, Green

import TlColor.* // otherwise write TlColor.Red, TlColor.Yellow, TlColor.green

// template
def fnForTlColor(color: TlColor) = color match
  case Red    => ???
  case Yellow => ???
  case Green  => ???

// Functions
// TLColor -> TLColor
// produce next color of traffic light

nextTlColor(Red)    == Green
nextTlColor(Yellow) == Red
nextTlColor(Green)  == Yellow

// def nextTlColor(c: TlColor) = Red // stub

// Template from TLColor
def nextTlColor(color: TlColor) = color match
  case Red    => Green
  case Yellow => Red
  case Green  => Yellow
