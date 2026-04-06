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
// In this problem you will design another world program. In this program the
// changing information will be more complex - your type definitions will
// involve arbitrary sized data as well as the reference rule and compound data.
// But by doing your design in two phases you will be able to manage this
// complexity. As a whole, this problem will represent an excellent summary of
// the material covered so far in the course, and world programs in particular.
// This world is about spinning bears. The world will start with an empty
// screen. Clicking anywhere on the screen will cause a bear to appear at that
// spot. The bear starts out upright, but then rotates counterclockwise at a
// constant speed. Each time the mouse is clicked on the
// screen, a new upright bear appears and starts spinning.
// So each bear has its own x and y position, as well as its angle of rotation.
// And there are an arbitrary amount of bears.
// To start, design a world that has only one spinning bear. Initially, the
// world will start with one bear spinning in the center at the screen. Clicking
// the mouse at a spot on the world will replace the old bear with a new bear at
// the new spot. You can do this part with only material up through compound.

// Once this is working you should expand the program to include an arbitrary
// number of bears.
// Here is an image of a bear for you to use: .

// Spinning Bears
// Constants:
val WIDTH = 600
val HEIGHT = 700
val SPEED = 30.degrees
val TICKRATE = FiniteDuration(100, "milliseconds")
val BEAR = Image.triangle(80, 40).fillColor(brown)
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(skyBlue)
  .withCenterAtOrigin

// Data definitions:
case class Bear(x: Double, y: Double, angle: Angle):
  require(-WIDTH / 2 <= x && x <= WIDTH / 2)
  require(-HEIGHT / 2 <= y && y <= HEIGHT / 2)
// Bear is (make-bear Number[0,WIDTH] Number[0,HEIGHT] Integer)
// interp.  (make-bear x y r) is the state of a bear, where
//  x is the x coordinate in pixels,
//  y is the y coordinate in pixels, and
//  r is the angle of rotation in degrees
val bear1 = Bear(0, 0, 0.degrees) // at the origin
val bear2 = Bear(WIDTH / 2, HEIGHT / 2, 90.degrees)

def fnForBear(bear: Bear) = ??? // bear.x, bear.y, bear.angle
// Template Rules Used:
// - compound: 3 fields

// ListOfBear is one of:
// - empty
// - (cons Bear ListOfBear)
val bears1 = List[Bear]()
val bears2 = List(bear1)
val bears3 = List(bear1, bear2)

def fnForBearList(bears: List[Bear]) = bears match
  case head :: next => ??? // head.x, head.y, head.angle, fnForBearList(next)
  case Nil          => ???
// Template Rules Used:
// - one of: 2 cases
// - atomic distinct: empty
// - compound: 2 fields
// - reference: (first lob) is Bear
// - self-reference: (rest lob) is ListOfBear

// Functions:
// ListOfBear -> ListOfBear
// start the world with (main empty)
def main(bears: List[Bear]): Unit =
  Reactor
    .init[List[Bear]](bears)
    .withOnTick(spinBears) // List[Bear] -> List[Bear]
    .withRender(renderBears) // List[Bear] -> Image
    .withOnMouseClick(addBear) // Point List[Bear] -> List[Bear]
    .withTickRate(TICKRATE)
    .animateWithFrame(FRAME)

// ListOfBear -> ListOfBear
// spin all of the bears forward by SPEED degrees
spinBears(bears1) == bears1
spinBears(bears2) == List(Bear(bear1.x, bear1.y, bear1.angle + SPEED))
spinBears(bears3) == List(
  Bear(bear1.x, bear1.y, bear1.angle + SPEED),
  Bear(bear2.x, bear2.y, bear2.angle + SPEED)
)

// def spinBears(bears: List[Bear]): List[Bear] = Nil // stub
def spinBears(bears: List[Bear]): List[Bear] = bears match
  case head :: next => // head.x, head.y, head.angle, fnForBearList(next)
    spinBear(head) :: spinBears(next)
  case Nil => Nil

// Bear -> Bear
// spin a bear forward by SPEED degrees
spinBear(bear1) == Bear(bear1.x, bear1.y, bear1.angle + SPEED)
spinBear(bear2) == Bear(bear2.x, bear2.y, bear2.angle + SPEED)

// def spinBear(bear: Bear): Bear = bear // stub
// Took template from Bear
def spinBear(bear: Bear): Bear =
  Bear(bear.x, bear.y, bear.angle + SPEED)

// ListOfBear -> Image
// render the bears onto the empty scene
renderBears(bears1) == Image.empty
renderBears(bears2) == BEAR
  .rotate(bear1.angle)
  .at(Point(bear1.x, bear1.y))
  .on(Image.empty)

renderBears(bears3) ==
  BEAR
    .rotate(bear1.angle)
    .at(Point(bear1.x, bear1.y))
    .on(
      BEAR
        .rotate(bear2.angle)
        .at(Point(bear2.x, bear2.y))
        .on(Image.empty)
    )

// def renderBears(bears: List[Bear]): Image = Image.empty // stub
// Took Template from ListOfBear
def renderBears(bears: List[Bear]): Image = bears match
  case head :: next =>
    renderBear(head) on renderBears(next)
  case Nil => Image.empty

// Bear Image -> Image
// render an image of the bear on the given image
renderBear(bear1) == BEAR.rotate(bear1.angle).at(Point(bear1.x, bear1.y))
renderBear(bear2) == BEAR.rotate(bear2.angle).at(Point(bear2.x, bear2.y))

// def renderBear(bear: Bear): Image = Image.empty // stub
// Took Template from Bear w/ added atomic parameter
def renderBear(bear: Bear): Image =
  BEAR.rotate(bear.angle).at(Point(bear.x, bear.y))

// ListOfBear Integer Integer MouseEvent -> ListOfBear
// On mouse-click, adds a bear with 0 rotation to the list at the x, y location
addBear(Point(50, 100), bears1) == List(Bear(50, 100, 0.degrees))
addBear(Point(-50, -100), bears2) == List(
  Bear(-50, -100, 0.degrees),
  bear1,
  bear2
)

// def addBear(point: Point, bears: List[Bear]): List[Bear] = Nil // stub
// Templated according to MouseEvent large enumeration.
def addBear(point: Point, bears: List[Bear]): List[Bear] =
  Bear(point.x, point.y, 0.degrees) :: bears

// main(bears1)
