def multListRecur(L: List[Int]): Int =
  if L.length == 1 then L.head else L.head * multListRecur(L.tail)

multListRecur(List(1, 3, 5, 7, 9))
