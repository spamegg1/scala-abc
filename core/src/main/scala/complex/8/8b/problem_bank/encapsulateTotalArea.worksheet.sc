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
import scala.util.Random

extension (image: Image)
  def area: Double = image match
    case OpenPath(elements)                  => 0.0
    case ClosedPath(elements)                => 0.0
    case Text(get)                           => get.length.toDouble
    case Circle(d)                           => math.Pi * d * d / 4
    case Rectangle(w, h)                     => w * h
    case Triangle(w, h)                      => w * h / 2
    case Beside(l, r)                        => l.area + r.area
    case Above(l, r)                         => l.area + r.area
    case On(t, b)                            => math.max(t.area, b.area)
    case At(image, _)                        => image.area
    case Transform(tx, i)                    => i.area
    case StrokeWidth(image, width)           => image.area
    case StrokeColor(image, color)           => image.area
    case FillColor(image, color)             => image.area
    case FillGradient(image, gradient)       => image.area
    case NoStroke(image)                     => image.area
    case NoFill(image)                       => image.area
    case Font(image, font)                   => image.area
    case Debug(image, color)                 => image.area
    case StrokeCap(image, cap)               => image.area
    case StrokeJoin(image, cap)              => image.area
    case StrokeDash(image, cap)              => image.area
    case Size(image, width, height)          => image.area
    case Margin(i, top, right, bottom, left) => 0.0
    case OriginAt(image, landmark)           => 0.0
    case Empty                               => 0.0

// In this exercise you will be need to remember the following DDs
// for an image organizer.
// Data definitions:
case class Directory(
    name: String,
    subdirs: List[Directory],
    images: List[Image]
)
val img1 = Image.square(40).fillColor(red)
val img2 = Image.square(60).fillColor(green)
val img3 = Image.rectangle(70, 80).fillColor(blue)
val img4 = Image.circle(60).fillColor(yellow)
val dir1 = Directory("dir1", Nil, List(img1, img2))
val dir2 = Directory("dir2", Nil, List(img3))
val dir3 = Directory("dir3", List(dir1, dir2), Nil)
val dir4 = Directory("dir4", List(dir3), List(img4))

// PROBLEM:
// Remember the functions we wrote last week to calculate the total area
// of all images in an image organizer.
// Use local to encapsulate the functions so that total-area--dir,
// total-area--lod, total-area--loi and image-area are private to a new function
// called total-area. Be sure to revise the signature, purpose, tests etc.
// Dir -> Natural
// produce total area of all images in dir
totalArea(dir4) == img1.area + img2.area + img3.area + img4.area

// <template from Dir, ListOfDir and ListOfImage, encapsulated with local>
def totalArea(directory: Directory): Double =
  def totalDir(dir: Directory): Double =
    totalDirList(dir.subdirs) + totalImgList(dir.images)
  def totalDirList(dirList: List[Directory]): Double = dirList.map(totalDir).sum
  def totalImgList(imgList: List[Image]): Double = imgList.map(_.area).sum

  totalDir(directory)
