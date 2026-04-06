import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.java2d.*
import doodle.reactor.Reactor
import doodle.reactor.syntax.all.animateWithFrame
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration
import doodle.syntax.all.*
import doodle.image.syntax.all.*

// PROBLEM:
// Design a world program as follows:
// The world starts off with a piece of grass waiting to grow. As time passes,
// the grass grows upwards. Pressing any key cuts the current strand of
// grass to 0, allowing a new piece to grow to the right of it.
// Starting display: e stands for empty, -> stands for "after 1 second"
// ee  ee  ee  .e
// ee->ee->.e->.e
// ee  .e  .e  .e
// Immediately after pressing any key, clears screen.
// (notice leftmost column is now empty,
// instead it grows on the second column.
// If that gets cut too, grow on third column.)
// ee  ee  ee  e.
// ee->ee->e.->e.
// ee  e.  e.  e.
// NOTE 1: Remember to follow the HtDW recipe! Be sure to do a proper domain
// analysis before starting to work on the code file.

// Growing and replanting grass
// Constants:
val WIDTH = 600
val HEIGHT = 400
val GRASSCOLOR = green
val GRASSWIDTH = 10
val GRASSY = -HEIGHT / 2
val SPACING = 10
val SUNCENTER = Image.circle(50).fillColor(yellow)
val SUNSTAR = Image.star(20, 30, 45).fillColor(orange)
val SUN = SUNCENTER on SUNSTAR at Point(-WIDTH / 2 + 50, HEIGHT / 2 - 50)
val TICKRATE = FiniteDuration(100, "milliseconds")
val GROWTHSPEED = 50
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(skyBlue)
  .withCenterAtOrigin

// Data definitions:
// Grass is (make-grass Number[0,WIDTH] Number)
// interp. a grass at some x-position that is grass-h tall
case class Grass(position: Double, height: Double):
  require(-WIDTH / 2 <= position && position <= WIDTH / 2)
  require(0 <= height && height <= HEIGHT)

val grass0 = Grass(-WIDTH / 2 + SPACING, 0)
val grass1 = Grass(0, 0)
val grass2 = Grass(-WIDTH / 2, HEIGHT) // max height grass, cannot grow

def fnForGrass(grass: Grass) = ???
// ???(grass.position, grass.height)

// Template Rules
// - compound: 2 fields

// Functions:
// Grass -> Grass
// starts the world call (main (make-grass 10 0))
// <examples not needed>
def main(grass: Grass): Unit =
  Reactor
    .init[Grass](grass)
    .withOnTick(growGrass) // grass -> grass
    .withRender(renderGrass) // grass -> Image
    .withOnMouseClick(cutGrass) // Point grass -> grass
    .withTickRate(TICKRATE)
    .animateWithFrame(FRAME)

// Grass -> Grass
// increase g's height by GROW-SPEED
growGrass(grass1) == Grass(grass1.position, grass1.height + GROWTHSPEED)
growGrass(grass2) == grass2

// def growGrass(grass: Grass): Grass = grass // stub
// Template from Grass
def growGrass(grass: Grass): Grass = Grass(
  grass.position,
  math.min(HEIGHT, grass.height + GROWTHSPEED)
)

// Grass -> Image
// place the grass at grass-x, GRASS-Y with height grass-h
renderGrass(grass1) == Image
  .rectangle(GRASSWIDTH, 0)
  .fillColor(GRASSCOLOR)
  .at(Point(0, GRASSY))
  .on(SUN)
renderGrass(grass2) == Image
  .rectangle(GRASSWIDTH, grass2.height)
  .fillColor(GRASSCOLOR)
  .at(Point(-WIDTH / 2, GRASSY))
  .on(SUN)

// def renderGrass(grass: Grass): Image = Image.empty // stub

// Template from Grass
def renderGrass(grass: Grass): Image = Image
  .rectangle(GRASSWIDTH, grass.height)
  .fillColor(GRASSCOLOR)
  .at(Point(grass.position, GRASSY))
  .on(SUN)

// Grass KeyEvent -> Grass
// replant new grass at (grass-x + GRASS-SPACING)
cutGrass(Point(0, 0), grass1) == Grass(grass1.position + SPACING, 0)
cutGrass(Point(-10, 20), grass2) == Grass(grass2.position + SPACING, 0)

// def cutGrass(point: Point, grass: Grass): Grass = grass // stub

// Template from Grass
def cutGrass(point: Point, grass: Grass): Grass =
  Grass(grass.position + SPACING, 0)

// main(grass0)
