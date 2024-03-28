// import doodle.core.*
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
// In this problem imagine you have a bunch of pictures that you would like to
// store as data and present in different ways. We'll do a simple version of
// that here, and set the stage for a more elaborate version later.
// (A) Design a data definition to represent an arbitrary number of images.
// (B) Design a function called arrange-images that consumes an arbitrary number
//     of images and lays them out left-to-right in increasing order of size.
extension (image: Image)
  def size: Double = image match
    case OpenPath(elements)            => 0.0
    case ClosedPath(elements)          => 0.0
    case Text(get)                     => get.size.toDouble
    case Circle(d)                     => math.Pi * d * d / 4
    case Rectangle(w, h)               => w * h
    case Triangle(w, h)                => w * h / 2
    case Beside(l, r)                  => l.size + r.size
    case Above(l, r)                   => l.size + r.size
    case On(t, b)                      => math.max(t.size, b.size)
    case At(image, _)                  => image.size
    case Transform(tx, i)              => i.size
    case StrokeWidth(image, width)     => image.size
    case StrokeColor(image, color)     => image.size
    case FillColor(image, color)       => image.size
    case FillGradient(image, gradient) => image.size
    case NoStroke(image)               => image.size
    case NoFill(image)                 => image.size
    case Font(image, font)             => image.size
    case Debug(image, color)           => image.size
    case Margin(_, _, _, _, _)         => 0.0
    case OriginAt(_, _)                => 0.0
    case StrokeCap(_, _)               => 0.0
    case StrokeJoin(_, _)              => 0.0
    case StrokeDash(_, _)              => 0.0
    case Empty                         => 0.0

// Constants for testing:
val img1 = Image.rectangle(100, 200).fillColor(red)
val img2 = Image.rectangle(200, 300).fillColor(green)
val img3 = Image.rectangle(300, 400).fillColor(blue)

// Data definitions:
// ListOfImage is one of:
//  - empty
//  - (cons Image ListOfImage)
// interp. An arbitrary number of images
val imgList1 = List[Image]()
val imgList2 = List(img3, img1, img2)
val sorted = List(img1, img2, img3)

def fnForImgList(imgList: List[Image]): AnyVal = imgList match
  case head :: next => ??? // head, fnForImgList(next)
  case Nil          => ???

// Functions:
// ListOfImage -> Image
// lay out images left to right in increasing order of size
// sort images in increasing order of size and then lay them out left-to-right
// squares first, rectangles next, circles next, other images last.
arrangeImages(imgList1) == Image.empty
arrangeImages(imgList2) == (img1 beside img2 beside img3 beside Image.empty)

// def arrangeImages(imgList: List[Image]): Image = Image.empty // stub
def arrangeImages: List[Image] => Image = layoutImages compose sortImages

// ListOfImage -> Image
// place images beside each other in order of list
layoutImages(imgList1) == Image.empty
layoutImages(imgList2) == (img1 beside img2 beside img3 beside Image.empty)

// def layoutImages(imgList: List[Image]): Image = Image.empty // stub
def layoutImages(imgList: List[Image]): Image = imgList match
  case head :: next => head beside layoutImages(next)
  case Nil          => Image.empty

// ListOfImage -> ListOfImage
// sort images in increasing order of size (area)
sortImages(imgList1) == imgList1
sortImages(imgList2) == sorted

// def sortImages(imgList: List[Image]): List[Image] = imgList // stub
def sortImages(imgList: List[Image]): List[Image] = imgList match
  case head :: next => insert(head, sortImages(next))
  case Nil          => Nil

// Image ListOfImage -> ListOfImage
// insert img in proper place in loi (in increasing order of size)
// ASSUME: loi is already sorted
insert(img1, imgList1) == List(img1)
insert(img1, List(img2, img3)) == sorted
insert(img2, List(img1, img3)) == sorted
insert(img3, List(img1, img2)) == sorted

// def insert(img: Image, imgList: List[Image]): List[Image] = Nil // stub
def insert(img: Image, imgList: List[Image]): List[Image] = imgList match
  case head :: next =>
    if img.size >= head.size
    then head :: insert(img, next)
    else img :: head :: next
  case Nil => List(img)

// arrangeImages(imgList2).draw()
