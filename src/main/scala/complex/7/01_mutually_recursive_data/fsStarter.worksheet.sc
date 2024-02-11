// Data definitions:
case class Element(name: String, data: Int, subdirs: List[Element])
// Element is (make-elt String Integer ListOfElement)
// interp. An element in the file system, with name, and EITHER data or subs.
//         If data is 0, then subs is considered to be list of sub elements.
//         If data is not 0, then subs is ignored.

// This makes more sense:
enum Data:
  case File(name: String, data: Int)
  case Directory(name: String, subdirs: List[Data])
import Data.*

val file1 = File("file1", 1)
val file2 = File("file2", 2)
val file3 = File("file3", 3)
val dir1 = Directory("dir1", List(file1, file2))
val dir2 = Directory("dir2", List(file3))
val dir3 = Directory("dir3", List(dir1, dir2))
