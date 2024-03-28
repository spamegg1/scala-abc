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

// In this problem you will be need to remember the following DDs
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

// Functions:
// PROBLEM A:
// Design an abstract fold function for Dir called fold-dir.
// (String Y Z -> X) (X Y -> Y) (Image Z -> Z) Y Z Dir -> X
// (check-expect (fold-dir make-dir cons cons empty empty D6) D6)
// (check-expect  (local [(define (c1 n rlod rloi) (+ rlod rloi))
//                        (define (c2 rdir rlod)   (+ 1 rdir))
//                        (define (c3 img rloi)    (+ 1 rloi))]
//                  (fold-dir c1 c2 c3 0 0 D6))
//                3)

// <template from Dir>
def foldForDir[S, T, U](dirFun: (String, T, U) => S)(dirListFun: (S, T) => T)(
    imgListFun: (Image, U) => U
)(baseT: T)(baseU: U)(directory: Directory): S =
  def fnForDir(dir: Directory): S =
    dirFun(dir.name, fnForDirList(dir.subdirs), fnForImgList(dir.images))
  def fnForDirList(dirList: List[Directory]): T = dirList match
    case head :: next => dirListFun(fnForDir(head), fnForDirList(next))
    case Nil          => baseT
  def fnForImgList(imgList: List[Image]): U = imgList match
    case head :: next => imgListFun(head, fnForImgList(next))
    case Nil          => baseU
  fnForDir(directory)

// PROBLEM B:
// Design a function that consumes a Dir and produces the number of
// images in the directory and its sub-directories.
// Use the fold-dir abstract function.
// Dir -> Natural
// count total number of Images in dir and all its subdirs
countImages(dir1) == 2
countImages(dir3) == 3
countImages(dir4) == 4

// <template as call to fold-dir>
def countImages =
  foldForDir[Int, Int, Int]((_, n, m) => n + m)(_ + _)((_, n) => n + 1)(0)(0)

// PROBLEM C:
// Design a function that consumes a Dir and a String. The function looks in
// dir and all its sub-directories for a directory with the given name. If it
// finds such a directory it should produce true, if not it should produce false.
// Use the fold-dir abstract function.
// String Dir -> Boolean
// look for a dir named name, if found produce true, otherwise produce false
find("dir1", dir1)
!find("dir1", dir2)
find("dir1", dir3)
!find("dir5", dir4)

// <template as call to fold-dir>
def find(searchName: String, directory: Directory): Boolean =
  foldForDir[Boolean, Boolean, Boolean]((name, bool1, bool2) =>
    name == searchName || bool1 || bool2
  )(_ || _)((img, _) => false)(false)(false)(directory)

// PROBLEM D:
// Is fold-dir really the best way to code the function from part C? Why or
// why not?
// No. Consider the case where the directory we are looking for is at the very
// root of the tree. As we have written the function in part C with fold-dir,
// even though find will produce true for the root node it will search the whole
// tree anyways.
// When we use fold-dir it isn't possible to implement find so that it stops
// searching as soon as it finds a directory with the right name. Instead it
// will always search the whole tree.
