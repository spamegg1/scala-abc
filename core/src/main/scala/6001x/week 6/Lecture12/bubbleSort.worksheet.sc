import collection.mutable.ArrayBuffer

def bubbleSort(L: ArrayBuffer[Int]) =
  var swap = false
  while
    !swap
  do
    swap = true
    println(L)
    for
      j <- 1 until L.length
    do
      if L(j - 1) > L(j) then
        swap = false
        val temp = L(j)
        L(j) = L(j - 1)
        L(j - 1) = temp


val test = ArrayBuffer(1, 5, 3, 8, 4, 2, 9, 6)
bubbleSort(test)
