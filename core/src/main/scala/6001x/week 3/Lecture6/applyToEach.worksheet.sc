def applyToEach[T <: AnyVal](array: Array[T], f: T => T) =
  // assumes array is an Array, f a function
  // mutates array by replacing each element,
  // e, of array by f(e)
  for i <- 0 until array.size do array(i) = f(array(i))

def applyFuns[T, S](funs: Array[T => S], t: T) = for f <- funs do println(f(t))

val array = Array(1, 2, 3)
applyToEach[Int](array, x => x * x)
array foreach println

applyFuns[Int, String](
  Array((x: Int) => s"hello, ${x}", (y: Int) => s"${y * y}"),
  3
)
