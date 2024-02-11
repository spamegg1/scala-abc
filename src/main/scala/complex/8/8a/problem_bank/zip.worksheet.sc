// Problem:
// Given the data definition below, design a function called zip that consumes
// two lists of numbers and produces a list of Entry, formed from the
// corresponding elements of the two lists.
// (zip (list 1 2 ...) (list 11 12 ...)) should produce:
// (list (make-entry 1 11) (make-entry 2 12) ...)
// Your design should assume that the two lists have the same length.

// Functions:
// ListOfNumber ListOfNumber -> ListOfEntry
// given (list a0...) (list b0...) produce (list (make-entry a0 b0) ...)
// ASSUMPTION: lsta and lstb have the same length
zip(Nil, Nil) == Nil
zip(Nil, List(1)) == Nil
zip(List(1), Nil) == Nil
zip(List(1, 2), List(11, 12)) == List((1, 11), (2, 12))

// CROSS PRODUCT OF TYPE COMMENTS TABLE
//
//                                    lstb
//                               empty         (cons Number ListOfNumber)
//
// l empty                       empty         IMPOSSIBLE
// s
// t (cons Number ListOfNumber)  IMPOSSIBLE    (cons (make-entry <firsts>)
// a                                                 (zip <rests>))*
//
//                                             *the natural recursion

def zip(list1: List[Int], list2: List[Int]): List[(Int, Int)] =
  (list1, list2) match
    case (Nil, _)                         => Nil
    case (_, Nil)                         => Nil
    case (head1 :: next1, head2 :: next2) => (head1, head2) :: zip(next1, next2)
