// PROBLEM:
// Rewite each of the following function definitions using lambda.
// Number (listof Number) -> (listof Number)
// produce only elements of lon > threshold
onlyBigger(2, List()) == List()
onlyBigger(3, List(2, 4, 5)) == List(4, 5)

// def onlyBigger(threshold: Int, list: List[Int]): List[Int] =
//   def pred(n: Int): Boolean = n > threshold
//   list filter pred
def onlyBigger(threshold: Int, list: List[Int]): List[Int] =
  list.filter(n => n > threshold)

// (listof Number)  ->  (listof Number)
// produce list of numbers sorted in ASCENDING order
// ASSUMPTION: lon contains no duplicates
qSort(Nil).isEmpty
qSort(List(4, 3, 2, 1)) == List(1, 2, 3, 4)
qSort(List(6, 8, 1, 9, 3, 7, 2)) == List(1, 2, 3, 6, 7, 8, 9)

def qSort(list: List[Int]): List[Int] = list match
  case head :: next =>
    qSort(list.filter(_ < head)) :::
      List(head) :::
      qSort(list.filter(_ > head))
  case Nil => Nil
