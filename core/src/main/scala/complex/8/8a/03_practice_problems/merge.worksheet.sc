// Problem:
// Design the function merge. It consumes two lists of numbers, which it assumes
// are each sorted in ascending order. It produces a single list of all the
// numbers, also sorted in ascending order.
// Your solution should explicitly show the cross product of type comments
// table, filled in with the values in each case. Your final function should
// have a cond with 3 cases. You can do this simplification using the cross
// product table by recognizing that there are subtly equal answers.
// Hint: Think carefully about the values of both lists. You might see a way to
// change a cell content so that 2 cells have the same value.

// ListOfNumber ListOfNumber -> ListOfNumber
// produce sorted list of numbers by merging two sorted lists of numbers

// CROSS PRODUCT OF TYPE COMMENTS TABLE
//
//       l2                     empty        (cons Number ListOfNumber)
//   l1
// empty                        l2           l2
//
// (cons Number ListOfNumber)   l1           (if (<= (first l1) (first l2))
//                                               (cons (first l1)
//                                                     (merge (rest l1) l2))
//                                               (cons (first l2)
//                                                     (merge l1 (rest l2))))
//
// NOTE: The l2 in the upper left corner of the table was originally
//       empty. But then the table had no simplifications. Thinking
//       about the cases carefully leads to the conclusion that the
//       empty could be replaced by l2, since l2 is in fact empty
//       in that case. Now the table supports simplification.
merge(Nil, Nil) == Nil
merge(Nil, List(1, 3, 5)) == List(1, 3, 5)
merge(List(0, 2, 4), Nil) == List(0, 2, 4)
merge(List(0, 2, 4, 6), List(1, 3, 5)) == List(0, 1, 2, 3, 4, 5, 6)

// def merge(list1: List[Int], list2: List[Int]): List[Int] = Nil // stub
// template taken from cross product table
def merge(list1: List[Int], list2: List[Int]): List[Int] =
  (list1, list2) match
    // case (Nil, Nil) => Nil // redundant case!
    case (Nil, _) => list2
    case (_, Nil) => list1
    case (head1 :: next1, head2 :: next2) =>
      if head1 <= head2
      then head1 :: merge(next1, list2)
      else head2 :: merge(list1, next2)
