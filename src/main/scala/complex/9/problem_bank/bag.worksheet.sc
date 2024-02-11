// Given the following partial data definitions:
case class Bag(length: Double, width: Double, height: Double)
// interp. a bag with a length, width and height in centimeters
val bag1 = Bag(19.5, 10.0, 6.5)
val bag2 = Bag(23.0, 11.5, 7.0)
val bag3 = Bag(18.0, 9.5, 5.5)

// PROBLEM:
// The linear length of a bag is defined to be its length plus
// width plus height. Design the function linear-length-lob that consumes
// a list of bags and produces a list of the linear lengths of each of
// the bags in the list.
// Use at least one built-in abstract function and encapsulate any helper
// functions in a local expression.
// ListOfBag -> ListOfNumber
// produces list of linear lengths of all the bags in lob
linearLengthBagList(Nil).isEmpty
linearLengthBagList(List(bag1, bag2, bag3)) == List(36.0, 41.5, 33.0)

// def linearLengthBagList(bags: List[Bag]): List[Double] = Nil // stub
// <template as call to map>
def linearLengthBagList(bags: List[Bag]): List[Double] =
  val bagLength = (bag: Bag) => bag.width + bag.length + bag.height
  bags map bagLength
