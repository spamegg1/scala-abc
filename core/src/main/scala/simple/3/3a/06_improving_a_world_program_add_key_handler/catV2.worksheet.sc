import doodle.core.*
import doodle.core.Color.*
import doodle.image.Image
import doodle.image.Image.Elements.*
import doodle.syntax.all.*
import doodle.java2d.*
import doodle.reactor.Reactor
import cats.effect.unsafe.implicits.global

// (define octo-head
//   (ellipse 160 110 "solid" "black"))
val octoHead = Circle(160).fillColor(black)
// (define octo-face-bg
//   (ellipse 128 80 "solid" "tan"))
val octoFaceBg = Circle(128).fillColor(tan)
// (define octo-ear
//   (ellipse 40 64 "solid" "black"))
val octoEar = Circle(40).fillColor(black)
// (define octo-left-ear
//   (rotate 30 octo-ear))
val octoLeftEar = octoEar.rotate(30.degrees)
// (define octo-right-ear
//   (rotate 330 octo-ear))
val octoRightEar = octoEar.rotate(-30.degrees)
// (define octo-ears
//   (beside
//    octo-left-ear
//    (rectangle 50 0 "solid" "white")
//    octo-right-ear))
val octoEars = octoLeftEar beside Rectangle(50, 0) beside octoRightEar
// (define octo-eye
//   (overlay
//    (ellipse 20 30 "solid" "lightbrown")
//    (ellipse 28 40 "solid" "white")))
val octoEye =
  val x = Circle(30).fillColor(brown)
  val y = Circle(40).fillColor(white)
  x on y
// (define octo-eyes
//   (beside
//    octo-eye
//    (rectangle 30 1 "solid" "tan")
//    octo-eye))
val octoEyes = octoEye beside Rectangle(30, 0).fillColor(tan) beside octoEye
// (define octo-nose
//   (circle 4 "solid" "lightbrown"))
val octoNose = Circle(4).fillColor(brown)
// (define octo-mouth
//   (overlay/align "middle" "top"
//    (rotate 195 (wedge 8 150 "solid" "tan"))
//    (rotate 195 (wedge 12 150 "solid" "lightbrown"))))
val octoMouth = octoNose
// (define octo-face
//   (overlay
//    (above
//     octo-eyes
//     octo-nose
//     octo-mouth)
//    octo-face-bg))
val eyesNoseMouth = octoEyes above octoNose above octoMouth
val octoFace = eyesNoseMouth on octoFaceBg
// (define octo-face-head
//   (overlay/align
//    "middle" "bottom"
//    (above octo-face (rectangle 1 8 "solid" "black"))
//    octo-head))
val octoFaceHead = octoFace above octoHead
// (define octocat
//   (overlay/offset
//    octo-face-head
//    0 -40
//    octo-ears))
val octoCat = octoEars above octoFaceHead

// A cat that walks from left to right across the screen.
val ear = Triangle(40, 40).fillColor(brown)
val ears = ear beside ear
val eye = Circle(20).fillColor(lightBlue)
val eyes = eye beside eye
val nose = Triangle(10, 20).fillColor(black)
val mouth = Rectangle(30, 10).fillColor(white)
val face = eyes above nose above mouth
val head = Circle(100).fillColor(brown)
val CAT = ears above (face on head)

// Constants:
val WIDTH = 800
val HEIGHT = 600
val HORIZONTAL = 0
val SPEED = 10 // number of pixels per second
val FRAME = Frame.default
  .withSize(WIDTH, HEIGHT)
  .withBackground(white)
  .withCenterAtOrigin
// Picture.read("octocat.png")

// Data definitions:
// Cat is Point(x, y)
// interp. x,y position of the cat in screen coordinates
// for this exercise we keep the y coordinate fixed at HORIZONTAL
val cat1 = Point(30, HORIZONTAL)

// Template
def fnForCat(cat: Point) = ???
// cat.x, cat.y

// Template rules used:
//  - atomic non-distinct: Number

// Functions:
// Cat -> Unit
// start the world with main(initCat)
def main(cat: Point): Unit =
  Reactor
    .init[Point](cat)
    .withOnTick(advanceCat)
    .withRender(renderCat)
    .withStop(stopCat)
    .run(FRAME)

// Cat
// the initial Point where the cat animation starts
val initCat = Point(-WIDTH / 2, HORIZONTAL)

// Cat -> Cat
// produce the next cat, by advancing it 1 pixel to right
advanceCat(Point(3.0, HORIZONTAL)) == Point(4.0, HORIZONTAL)
// def advanceCat(cat: Point): Point = cat // stub
// <use template from Cat>
def advanceCat(cat: Point): Point = Point(cat.x + SPEED, cat.y)

// Cat -> Image
// render the cat image at appropriate place on FRAME
renderCat(initCat) == CAT.at(initCat)
// def renderCat(cat: Point): Image = Image.empty // stub
// <use template from Cat>
def renderCat(cat: Point) =
  // octoCat.at(cat)
  CAT.at(cat)

def stopCat(cat: Point): Boolean = cat.x >= WIDTH / 2

// main(initCat)
