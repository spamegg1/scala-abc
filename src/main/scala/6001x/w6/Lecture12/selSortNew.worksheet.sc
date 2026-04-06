import collection.mutable.ArrayBuffer

def selectionSort(L: ArrayBuffer[Int]) =
  var suffixStart = 0
  while suffixStart != L.length do
    println(L)
    for i <- suffixStart until L.length do
      if L(i) < L(suffixStart) then
        val temp = L(i)
        L(i) = L(suffixStart)
        L(suffixStart) = temp
    suffixStart += 1

val test = ArrayBuffer(1, 5, 3, 8, 4, 9, 6, 2)
selectionSort(test)
