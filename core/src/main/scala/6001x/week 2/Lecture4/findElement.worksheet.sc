// assume L is ordered in increasing order
def findElemRecur(e: Int, L: List[Int]): Boolean =
  if L.isEmpty then false
  else if L.length == 1 then L.head == e
  else
    val half = L.length / 2
    if L(half) > e then findElemRecur(e, L.take(half))
    else findElemRecur(e, L.drop(half))

findElemRecur(6, List(1, 2, 3, 5, 7, 8, 9, 15))
