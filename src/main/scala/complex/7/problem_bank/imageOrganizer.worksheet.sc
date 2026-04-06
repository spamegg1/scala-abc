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
    case OpenPath(elements)            => 0.0
    case ClosedPath(elements)          => 0.0
    case Text(get)                     => get.length.toDouble
    case Circle(d)                     => math.Pi * d * d / 4
    case Rectangle(w, h)               => w * h
    case Triangle(w, h)                => w * h / 2
    case Beside(l, r)                  => l.area + r.area
    case Above(l, r)                   => l.area + r.area
    case On(t, b)                      => math.max(t.area, b.area)
    case At(image, _)                  => image.area
    case Transform(tx, i)              => i.area
    case StrokeWidth(image, width)     => image.area
    case StrokeColor(image, color)     => image.area
    case FillColor(image, color)       => image.area
    case FillGradient(image, gradient) => image.area
    case NoStroke(image)               => image.area
    case NoFill(image)                 => image.area
    case Font(image, font)             => image.area
    case Debug(image, color)           => image.area
    case StrokeCap(image, cap)         => image.area
    case StrokeJoin(image, cap)        => image.area
    case StrokeDash(image, cap)        => image.area
    case Size(image, width, height)    => image.area
    case Margin(_, _, _, _, _)         => 0.0
    case OriginAt(_, _)                => 0.0
    case Empty                         => 0.0

// In this exercise you will be creating an image organizer using the
// following DDs.
// Data definitions:
// Dir is (make-dir String ListOfDir ListOfImage)
// interp. An directory in the organizer, with a name, a list
//         of sub-dirs and a list of images.
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

// PART A:
// Annotate the type comments with reference arrows and label each one to say
// whether it is a reference, self-reference or mutual-reference.

// PART B:
// Write out the templates for Dir, ListOfDir and ListOfImage. Identify for each
// call to a template function which arrow from part A it corresponds to.

def fnForDirectory(directory: Directory) =
  fnForDirectoryList(directory.subdirs) // directory.name, directory.images

def fnForDirectoryList(directories: List[Directory]) = directories match
  case head :: next => ??? // fnForDirectory(head) :: fnForDirectoryList(next)
  case Nil          => ???

// Functions:
// PROBLEM B:
// Design a function to calculate the total area (width * height) of all the
// images in a directory and its sub-directories.
// Dir             -> Natural
// ListOfDir       -> Natural
// ListOfImage     -> Natural
// produce total area of all images in dir
areaDirectory(dir4) == img1.area + img2.area + img3.area + img4.area
areaDirectoryList(List(dir1, dir2)) == img1.area + img2.area + img3.area
areaImageList(List(img1, img2)) == img1.area + img2.area

//<templates taken from Dir, ListOfDir, and ListOfImage>
// def areaDirectory(directory: Directory): Double = 0.0 // stub
// def areaDirectoryList(directories: List[Directory]): Double = 0.0 // stub
// def areaImageList(images: List[Image]): Double = 0.0 // stub

def areaDirectory(directory: Directory): Double =
  areaDirectoryList(directory.subdirs) + areaImageList(directory.images)

def areaDirectoryList(directories: List[Directory]): Double = directories match
  case head :: next => areaDirectory(head) + areaDirectoryList(next)
  case Nil          => 0.0

def areaImageList(images: List[Image]): Double = images.map(_.area).sum

// PROBLEM C:
// Design a function to produce rendering of a directory with its images.
// Keep it simple and be sure to spend the first 10 minutes of your work
// with paper and pencil!
// Dir             -> Image
// ListOfDir       -> Image
// ListOfImage     -> Image
// produce VERY simple rendering of all images in dir
renderImageList(Nil) == Image.empty
renderDirectoryList(Nil) == Image.empty
renderDirectoryList(List(dir1, dir2)) ==
  (Text("dir1")
    .beside(Image.empty)
    .beside(img1 beside (img2 beside Image.empty)))
    .above(
      (Text("dir2")
        .beside(Image.empty)
        .beside(img3 beside Image.empty))
        .above(Image.empty)
    )
renderImageList(List(img1, img2)) == img1.beside(img2 beside Image.empty)
renderDirectory(dir1) ==
  Text("dir1")
    .beside(Image.empty)
    .beside(img1 beside (img2 beside Image.empty))
renderDirectory(dir2) ==
  Text("dir2").beside(Image.empty).beside(img3 beside Image.empty)
renderDirectory(dir3) ==
  Text("dir3")
    .beside(
      (Text("dir1")
        .beside(Image.empty)
        .beside(img1 beside (img2 beside Image.empty)))
        .above(
          (Text("dir2")
            .beside(Image.empty)
            .beside(img3 beside Image.empty))
            .above(Image.empty)
        )
    )
    .beside(Image.empty)
renderImageList(List(img4)) == img4.beside(Image.empty)
renderDirectory(dir4) ==
  Text("dir4")
    .beside(
      Text("dir3")
        .beside(
          (Text("dir1")
            .beside(Image.empty)
            .beside(img1 beside (img2 beside Image.empty)))
            .above(
              (Text("dir2")
                .beside(Image.empty)
                .beside(img3 beside Image.empty))
                .above(Image.empty)
            )
        )
        .beside(Image.empty)
        .above(Image.empty)
    )
    .beside(img4.beside(Image.empty))

// def renderImageList(images: List[Image]): Image = Image.empty // stub
// def renderDirectoryList(directories: List[Directory]): Image = Image.empty
// def renderDirectory(directory: Directory): Image = Image.empty // stub

def renderDirectory(directory: Directory): Image =
  Text(directory.name)
    .beside(renderDirectoryList(directory.subdirs))
    .beside(renderImageList(directory.images))

def renderDirectoryList(directories: List[Directory]): Image = directories match
  case head :: next =>
    renderDirectory(head).above(renderDirectoryList(next))
  case Nil => Image.empty

def renderImageList(images: List[Image]): Image = images match
  case head :: next => head.beside(renderImageList(next))
  case Nil          => Image.empty

// renderDirectory(dir4).draw()
