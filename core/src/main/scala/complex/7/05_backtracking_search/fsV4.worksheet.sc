// Data definitions:
// case class Element(name: String, data: Int, subdirs: List[Element])
// Element is (make-elt String Integer ListOfElement)
// interp. An element in the file system, with name, and EITHER data or subs.
//         If data is 0, then subs is considered to be list of sub elements.
//         If data is not 0, then subs is ignored.

// This makes more sense:
enum Element:
  case File(name: String, data: Int)
  case Directory(name: String, subdirs: List[Element])
import Element.*

val file1 = File("file1", 1)
val file2 = File("file2", 2)
val file3 = File("file3", 3)
val dir1 = Directory("dir1", List(file1, file2))
val dir2 = Directory("dir2", List(file3))
val dir3 = Directory("dir3", List(dir1, dir2))

def fnForElement(element: Element) = element match
  case File(name, data)         => ??? // name, data
  case Directory(name, subdirs) => ??? // name, fnForElementList(subdirs)

def fnForElementList(elementList: List[Element]) = elementList match
  case head :: next => ??? // fnForElement(head), fnForElementList(next)
  case Nil          => ???

// PROBLEM
// Design a function that consumes Element and produces
// the sum of all the file data in the tree.
// Element -> Integer
// ListOfElement -> Integer
// produce the sum of all the data in element (and its subs)
sumElement(file1) == 1
sumElement(file2) == 2
sumElement(file3) == 3
sumElement(dir1) == 3
sumElement(dir2) == 3
sumElement(dir3) == 6

// def sumElement(element: Element): Int = 0 // stub
// def sumElementList(elementList: List[Element]): Int = 0 // stub

def sumElement(element: Element): Int = element match
  case File(name, data)         => data
  case Directory(name, subdirs) => sumElementList(subdirs)

def sumElementList(elementList: List[Element]): Int = elementList match
  case head :: next => sumElement(head) + sumElementList(next)
  case Nil          => 0

// Another approach
// with foldLeft, we do not need sumElementList
// def sumElement(element: Element): Int = element match
//   case File(name, data) => data
//   case Directory(name, subdirs) =>
//     subdirs.foldLeft(0)((total, element) => total + sumElement(element))

// PROBLEM
// Design a function that consumes Element and produces a list of
// the names of all the elements in the tree.
// Element       -> ListOfString
// ListOfElement -> ListOfString
// produce list of the names of all the elements in the tree.
// This includes the names of the directories, as well as the names of the files
// Notice that the order matters:
// dir3
// |
// |__dir1
// |   |
// |   __file1
// |   __file2
// |
// |__dir2
//     |
//     __file3
namesElement(file1) == List("file1")
namesElement(file2) == List("file2")
namesElement(file3) == List("file3")
namesElement(dir1) == List("dir1", "file1", "file2")
namesElement(dir2) == List("dir2", "file3")
namesElement(dir3) == List("dir3", "dir1", "file1", "file2", "dir2", "file3")

// def namesElement(element: Element): List[String] = Nil // stub
// def namesElementList(elementList: List[Element]): List[String] = Nil // stub

// def namesElement(element: Element): List[String] = element match
//   case File(name, data)         => List(name)
//   case Directory(name, subdirs) => name :: namesElementList(subdirs)

// def namesElementList(elementList: List[Element]): List[String] =
//   elementList match
//     case head :: next => namesElement(head) ::: namesElementList(next)
//     case Nil          => Nil

// Another approach: using foldLeft again
def namesElement(element: Element): List[String] = element match
  case File(name, data) => List(name)
  case Directory(name, subdirs) =>
    subdirs.foldLeft(List(name))((list, element) =>
      list ::: namesElement(element)
    )

// PROBLEM
// Design a function that consumes String and Element and looks for a data
// element with the given name.
// If it finds that element it produces the data, otherwise it produces false.
// String Element -> Integer or false
// String ListOfElement -> Integer or false???
// search the given tree for an element with the given name,
// produce data if found false otherwise
findElement("file3", file1) == None
findElement("file3", file3) == Some(3)
findElement("dir1", dir1) == None
findElement("file2", dir1) == Some(2)
findElement("dir3", dir3) == None
findElement("file3", dir3) == Some(3)
findElement("file1", dir3) == Some(1)
findElement("file2", dir3) == Some(2)
findElement("file3", dir3) == Some(3)
findElementList("file3", Nil) == None
findElementList("file2", List(file1, file2)) == Some(2)
findElementList("file3", List(file1, file2)) == None

// def findElement(searchName: String, element: Element): Option[Int] =
//   None // stub
// def findElementList(
//     searchName: String,
//     elementList: List[Element]
// ): Option[Int] =
//   None // stub
def findElement(searchName: String, element: Element): Option[Int] =
  element match
    case File(name, data) =>
      if searchName == name then Some(data) else None
    case Directory(name, subdirs) => findElementList(searchName, subdirs)

def findElementList(
    searchName: String,
    elementList: List[Element]
): Option[Int] = elementList match
  case head :: next =>
    findElement(searchName, head) match
      case Some(data) => Some(data)
      case None       => findElementList(searchName, next)
  case Nil => None

// Alternate approach:
def findElement2(searchName: String, element: Element): Option[Int] =
  element match
    case File(name, data) =>
      if searchName == name then Some(data) else None
    case Directory(name, subdirs) =>
      subdirs.foldLeft[Option[Int]](None)((opt, elt) =>
        if opt.isDefined then opt
        else findElement2(searchName, elt)
      )
