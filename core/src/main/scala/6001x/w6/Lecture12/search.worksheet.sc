def search(L: List[Int], e: Int) =
  def bSearch(L: List[Int], e: Int, low: Int, high: Int): Boolean =
    if high == low then L(low) == e
    else
      val mid = low + (high - low) / 2
      if L(mid) == e then true
      else if L(mid) > e then bSearch(L, e, low, mid)
      else bSearch(L, e, mid + 1, high)

  if L.length == 0 then false
  else bSearch(L, e, 0, L.length - 1)

val list = List(1, 2, 3, 4, 5)
search(list, 3)
search(list, 6)
