def bisectSearch1(L: List[Int], e: Int): Boolean =
  if L.isEmpty then
    false
  else if L.length == 1 then
    L(0) == e
  else
    val half = L.length / 2
    if L(half) > e then
      bisectSearch1(L.take(half), e)
    else
      bisectSearch1(L.drop(half), e)


def bisectSearch2(L: List[Int], e: Int): Boolean =
  def bisectSearchHelper(L: List[Int], e: Int, low: Int, high: Int): Boolean =
    if high == low then
      L(low) == e
    else
      val mid = (low + high) / 2
      if L(mid) == e then
        true
      else if L(mid) > e then
        if low == mid then  // nothing left to search
          false
        else
          bisectSearchHelper(L, e, low, mid - 1)
      else
        bisectSearchHelper(L, e, mid + 1, high)

  if L.length == 0 then
    false
  else
    bisectSearchHelper(L, e, 0, L.length - 1)


val testList = List(1, 2, 3, 5, 7, 9, 18, 27)
bisectSearch1(testList, 7)
bisectSearch1(testList, 8)
bisectSearch2(testList, 7)
bisectSearch2(testList, 8)
