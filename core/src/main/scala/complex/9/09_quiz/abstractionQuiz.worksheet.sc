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
import scala.util.Random

//  PROBLEM 1:
//  Design an abstract function called arrange-all to simplify the
//  above-all and beside-all functions defined below. Rewrite above-all and
//  beside-all using your abstract function.
// (listof Image) -> Image
// combines a list of images into a single image, each image above the next one
def aboveAllOld(images: List[Image]): Image = images match
  case head :: next => head above aboveAll(next)
  case Nil          => Image.empty

// we want this instead:
def aboveAll = arrangeAll((img1, img2) => img1 above img2)(Image.empty)

// (listof Image) -> Image
// combines a list of images into a single image, each image beside the next one
def besideAllOld(images: List[Image]): Image = images match
  case head :: next => head beside aboveAll(next)
  case Nil          => Image.empty

// we want this instead:
def besideAll = arrangeAll((img1, img2) => img1 beside img2)(Image.empty)

// (Image Image -> Image) Image ListOfImage -> Image
def arrangeAll(function: (Image, Image) => Image)(base: Image)(
    images: List[Image]
): Image = images match
  case head :: next => function(head, arrangeAll(function)(base)(next))
  case Nil          => base

// Using fold instead:
def aboveAllFold(images: List[Image]): Image =
  images.foldLeft(Image.empty)((img1, img2) => img1 above img2)

def besideAllFold(images: List[Image]): Image =
  images.foldLeft(Image.empty)((img1, img2) => img1 beside img2)

// PROBLEM 2:
// Finish the design of the following functions, using built-in abstract
// functions.
// Function 1
// (listof String) -> (listof Natural)
// produces a list of the lengths of each string in los
lengths(Nil).isEmpty
lengths(List("apple", "banana", "pear")) == List(5, 6, 4)

def lengths(words: List[String]): List[Int] = words.map(_.length)

// Function 2
// (listof Natural) -> (listof Natural)
// produces a list of just the odd elements of lon
oddOnly(Nil).isEmpty
oddOnly(List(1, 2, 3, 4, 5)) == List(1, 3, 5)

def oddOnly(ints: List[Int]): List[Int] = ints.filter(_ % 2 == 1)

// Function 3
// (listof Natural -> Boolean
// produce true if all elements of the list are odd
allOdd(Nil)
!allOdd(List(1, 2, 3, 4, 5))
allOdd(List(5, 5, 79, 13))

def allOdd(ints: List[Int]): Boolean = ints.forall(int => int % 2 == 1)

// Function 4
// (listof Natural) -> (listof Natural)
// subtracts n from each element of the list
minusN(Nil, 5).isEmpty
minusN(List(4, 5, 6), 1) == List(3, 4, 5)
minusN(List(10, 5, 7), 4) == List(6, 1, 3)

def minusN(ints: List[Int], n: Int): List[Int] = ints.map(_ - n)

//  PROBLEM 3
//  Consider the data definition below for Region. Design an abstract fold
//  function for region, and then use it do design a function that produces a
//  list of all the names of all the regions in that region.
//
//  For consistency when answering the multiple choice questions, please order
//  the arguments in your fold function with combination functions first, then
//  and combination functions in order of where they appear in the function.
//  bases, then region. Please number the bases
//  So (all-regions CANADA) would produce
//  (list "Canada" "British Columbia" "Vancouver"
//        "Victoria" "Alberta" "Calgary" "Edmonton")

enum RegionType:
  case Continent, Country, Province, State, City
import RegionType.*

case class Region(
    name: String,
    regionType: RegionType,
    subregions: List[Region]
)
val VANCOUVER = Region("Vancouver", City, Nil)
val VICTORIA = Region("Victoria", City, Nil)
val BC = Region("British Columbia", Province, List(VANCOUVER, VICTORIA))
val CALGARY = Region("Calgary", City, Nil)
val EDMONTON = Region("Edmonton", City, Nil)
val ALBERTA = Region("Alberta", Province, List(CALGARY, EDMONTON))
val CANADA = Region("Canada", Country, List(BC, ALBERTA))

// (String X Z -> Y) (Y Z -> Z) X X X X X Z Region -> Y
// (check-expect (fold-region
//                make-region cons
//                "Continent" "Country" "Province" "State" "City" empty CANADA)
//               CANADA)
def foldRegion[S, T, U](regionOp: (String, S, U) => T)(
    regionListOp: (T, U) => U
)(s1: S, s2: S, s3: S, s4: S, s5: S)(u: U)(region: Region): T =
  def fnForRegion(reg: Region): T =
    regionOp(
      reg.name,
      fnForType(reg.regionType),
      fnForRegionList(reg.subregions)
    )
  def fnForType(regType: RegionType): S = regType match
    case Continent => s1
    case Country   => s2
    case Province  => s3
    case State     => s4
    case City      => s5
  def fnForRegionList(regs: List[Region]): U = regs match
    case head :: next => regionListOp(fnForRegion(head), fnForRegionList(next))
    case Nil          => u
  fnForRegion(region)

def allRegions(region: Region): List[String] =
  foldRegion[List[Region], List[String], List[String]]((name, _, nameList) =>
    name :: nameList
  )(_ ::: _)(Nil, Nil, Nil, Nil, Nil)(Nil)(region)

allRegions(CANADA)
