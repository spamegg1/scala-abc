import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global
import concurrent.duration.FiniteDuration
import doodle.syntax.all.*
import doodle.image.syntax.all.*

// PROBLEM:
// Design a simple interactive animation of rain falling down a screen.
// Wherever we click, a rain drop should be created and as time goes by it
// should fall. Over time the drops will reach the bottom of the screen and
// "fall off". You should filter these excess drops out of the world state -
// otherwise your program is continuing to tick and and draw them long after
// they are invisible.
// In your design pay particular attention to the helper rules. In our solution
// we use these rules to split out helpers:
//   - function composition
//   - reference
//   - knowledge domain shift

// Make it rain where we want it to.
// Constants:
val WIDTH = 300
val HEIGHT = 300
val SPEED = 10
val DROP = Image.circle(20).scale(1, 3).fillColor(darkBlue)
val TICKRATE = FiniteDuration(100, "milliseconds")
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(skyBlue)
  .withCenterAtOrigin

// Data definitions:
// (define-struct drop (x y))
// Drop is (make-drop Integer Integer)
// interp. A raindrop on the screen, with x and y coordinates.
val drop1 = Point(10, 30)
val drop2 = Point(-10, -30)

def fnForDrop(drop: Point) = ??? // drop.x, drop.y
// Template Rules used:
// - compound: 2 fields

// ListOfDrop is one of:
//  - empty
//  - (cons Drop ListOfDrop)
// interp. a list of drops
val dropList1 = List[Point]()
val dropList2 = List(drop1, drop2)

def fnForDropList(dropList: List[Point]) = dropList match
  case head :: next => ??? // head, fnForDropList(next)
  case Nil          => ???

// Template Rules used:
// - one-of: 2 cases
// - atomic distinct: empty
// - compound: (cons Drop ListOfDrop)
// - reference: (first lod) is Drop
// - self reference: (rest lod) is ListOfDrop

// Functions:
// ListOfDrop -> ListOfDrop
// start rain program by evaluating (main empty)
def main(drops: List[Point]): Unit =
  Reactor
    .init[List[Point]](drops)
    .withOnTick(advanceDrops) // droplist -> droplist
    .withRender(renderDrops) // droplist -> Image
    .withOnMouseClick(addDrop) // Point droplist -> droplist
    .withTickRate(TICKRATE)
    .animateWithFrame(FRAME)

// ListOfDrop Integer Integer MouseEvent -> ListOfDrop
// if mevt is "button-down" add a new drop at that position
def addDrop(point: Point, drops: List[Point]): List[Point] = point :: drops

// ListOfDrop -> ListOfDrop
// produce filtered and ticked list of drops
advanceDrops(dropList1) == dropList1
advanceDrops(dropList2) == dropList2.map(drop => Point(drop.x, drop.y - SPEED))
advanceDrops(List(Point(0, -HEIGHT / 2 + SPEED - 1))) == Nil

// <template as function composition>
def advanceDrops(drops: List[Point]): List[Point] = drops match
  case head :: next =>
    val headAdvance = Point(head.x, head.y - SPEED)
    if headAdvance.y <= -HEIGHT / 2
    then advanceDrops(next)
    else headAdvance :: advanceDrops(next)
  case Nil => Nil

// ListOfDrop -> Image
// Render the drops onto MTS
// <template from ListOfDrop>
def renderDrops(drops: List[Point]): Image = drops match
  case head :: next => DROP.at(head).on(renderDrops(next))
  case Nil          => Image.empty

// main(dropList1)
