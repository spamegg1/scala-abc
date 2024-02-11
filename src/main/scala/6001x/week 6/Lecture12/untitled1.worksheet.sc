import collection.mutable.ArrayBuffer

def swapSort(L: ArrayBuffer[Int]) =
  println(s"Original L: ${L}")
  for
    i <- 0 until L.length
  do
    for
      j <- 0 until L.length
    do
      if L(i) < L(j) then
        val temp = L(i)
        L(i) = L(j)
        L(j) = temp
        println(L)
  println(s"Final L: ${L}")

swapSort(ArrayBuffer(5, 1, 4, 3, 2))
