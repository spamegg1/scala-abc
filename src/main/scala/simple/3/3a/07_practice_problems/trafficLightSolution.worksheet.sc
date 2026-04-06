import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.syntax.all.*
import doodle.reactor.syntax.all.animateWithFrame
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration

// PROBLEM:
// Design an animation of a traffic light.
// Your program should show a traffic light that is red, then green,
// then yellow, then red etc. For this program, your changing world
// state data definition should be an enumeration.
// Here is what your program might look like if the initial world
// state was the red traffic light:
// Next:
// Next:
// Next is red, and so on.
// To make your lights change at a reasonable speed, you can use the
// rate option to on-tick. If you say, for example, (on-tick next-color 1)
// then big-bang will wait 1 second between calls to next-color.
// Remember to follow the HtDW recipe! Be sure to do a proper domain
// analysis before starting to work on the code file.
// Note: If you want to design a slightly simpler version of the program,
// you can modify it to display a single circle that changes color, rather
// than three stacked circles.

// A simple animated traffic light.
// Constants:
val WIDTH = 200
val HEIGHT = 600
val TICKRATE = FiniteDuration(1, "seconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin
val RADIUS = 200
val BACKGROUND =
  Image
    .rectangle(1 * RADIUS, 3 * RADIUS)
    .fillColor(black)

def trafficLight(filled: Boolean, color: Color): Image =
  if filled then Image.circle(RADIUS).fillColor(color)
  else Image.circle(RADIUS).strokeColor(color)

val REDON = trafficLight(true, red)
val REDOFF = trafficLight(false, red)
val YELLOWON = trafficLight(true, yellow)
val YELLOWOFF = trafficLight(false, yellow)
val GREENON = trafficLight(true, green)
val GREENOFF = trafficLight(false, green)

val redOn = (REDON above YELLOWOFF above GREENOFF) on BACKGROUND
val yellowOn = (REDOFF above YELLOWON above GREENOFF) on BACKGROUND
val greenOn = (REDOFF above YELLOWOFF above GREENON) on BACKGROUND

// Data definitions:
// Light is one of:
//  - "red"
//  - "yellow"
//  - "green"
// interp. the current color of the light
// <examples are redundant for enumerations>
enum Light:
  case Red, Yellow, Green

import Light.*

// Template
def fnForLight(light: Light) = light match
  case Red    => ???
  case Yellow => ???
  case Green  => ???

// Template rules used:
//   one of: 3 cases
//   atomic distinct: "red"
//   atomic distinct: "yellow"
//   atomic distinct: "green"

// Functions:
// Light -> Light
// called to run the animationstart with (main "red")
// no tests for main function
def main(light: Light): Unit =
  Reactor
    .init[Light](light)
    .withOnTick(advanceLight) // Light -> Light
    .withTickRate(TICKRATE)
    .withRender(renderLight) // Light -> Image
    // .withOnMouseClick(handleKey) // Light KeyEvent -> Light
    .animateWithFrame(FRAME)

// Light -> Light
// produce next color of light
advanceLight(Red) == Green
advanceLight(Yellow) == Red
advanceLight(Green) == Yellow

// def advanceLight(light: Light): Light = Red // Stub
def advanceLight(light: Light): Light = light match
  case Red    => Green
  case Yellow => Red
  case Green  => Yellow

// Light -> Image
// produce appropriate image for light color
renderLight(Red) == redOn
renderLight(Yellow) == yellowOn
renderLight(Green) == greenOn

// def renderLight(light: Light): Image = Image.empty // stub
def renderLight(light: Light): Image = light match
  case Red    => redOn
  case Yellow => yellowOn
  case Green  => greenOn

// main(Red)
