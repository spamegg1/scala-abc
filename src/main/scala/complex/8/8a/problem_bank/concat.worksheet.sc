// Problem:
// Given the data definition below, design a function called concat that
// consumes two ListOfString and produces a single list with all the elements
// of lsta preceding lstb.
// (concat (list "a" "b" ...) (list "x" "y" ...)) should produce:
// (list "a" "b" ... "x" "y" ...)
// You are basically going to design the function append using a cross product
// of type comments table. Be sure to simplify your design as much as possible.
// Hint: Think carefully about the values of both lists. You might see a way to
// change a cell's content so that 2 cells have the same value.

// Functions:
// ListOfString ListOfString -> ListOfString
// append the two lists
// CROSS PRODUCT OF TYPE COMMENTS TABLE
//
//                                lstb
//                       empty         (cons String LOS)
//
// l empty               lsta          lstb
// s
// t (cons String LOS)   lsta          (cons (first a)
// a                                         (concat (rest a) b)) *
//
//                                     *an altered natural recursion

concat(Nil, Nil) == Nil
concat(Nil, List("hello")) == List("hello")
concat(List("hello"), Nil) == List("hello")
concat(List("hello", "world"), List("spam", "ham", "eggs")) ==
  List("hello", "world", "spam", "ham", "eggs")

def concat(list1: List[String], list2: List[String]): List[String] =
  (list1, list2) match
    case (Nil, _) => list2
    case (_, Nil) => list1
    case (head1 :: next1, head2 :: next2) =>
      head1 :: concat(next1, list2)
