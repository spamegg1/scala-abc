import collection.mutable.Buffer

def mergeSort[T](L: List[T], compare: (T, T) => Boolean): List[T] =
  val len = L.length
  if len < 2 then L
  else
    val middle = len / 2
    val left = mergeSort(L.take(middle), compare)
    val right = mergeSort(L.drop(middle), compare)
    merge(left, right, compare)

def merge[T](left: List[T], right: List[T], compare: (T, T) => Boolean): List[T] =
  var result = Buffer[T]()
  var (i, j) = (0, 0)

  while i < left.length && j < right.length do
    if compare(left(i), right(j)) then
      result += left(i)
      i += 1
    else
      result += right(j)
      j += 1
  while i < left.length do
    result += left(i)
    i += 1
  while j < right.length do
    result += right(j)
    j += 1

  result.toList

mergeSort[Int](List(5, 3, 4, 2, 1), _ < _)
mergeSort[Char](List('e', 'a', 'f', 'd', 'c', 'b'), _ > _)
