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
  def size: Double = image match
    case OpenPath(elements)                  => 0.0
    case ClosedPath(elements)                => 0.0
    case Text(get)                           => get.size.toDouble
    case Circle(d)                           => math.Pi * d * d / 4
    case Rectangle(w, h)                     => w * h
    case Triangle(w, h)                      => w * h / 2
    case Beside(l, r)                        => l.size + r.size
    case Above(l, r)                         => l.size + r.size
    case On(t, b)                            => math.max(t.size, b.size)
    case At(image, _)                        => image.size
    case Transform(tx, i)                    => i.size
    case StrokeWidth(image, width)           => image.size
    case StrokeColor(image, color)           => image.size
    case FillColor(image, color)             => image.size
    case FillGradient(image, gradient)       => image.size
    case NoStroke(image)                     => image.size
    case NoFill(image)                       => image.size
    case Font(image, font)                   => image.size
    case Debug(image, color)                 => image.size
    case StrokeCap(image, cap)               => image.size
    case StrokeJoin(image, cap)              => image.size
    case StrokeDash(image, cap)              => image.size
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
totalArea(dir4) == img1.size + img2.size + img3.size + img4.size

// <template from Dir, ListOfDir and ListOfImage, encapsulated with local>
def totalArea(directory: Directory): Double =
  def totalDir(dir: Directory): Double =
    totalDirList(dir.subdirs) + totalImgList(dir.images)
  def totalDirList(dirList: List[Directory]): Double = dirList.map(totalDir).sum
  def totalImgList(imgList: List[Image]): Double = imgList.map(_.size).sum

  totalDir(directory)
