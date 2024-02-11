def oddTuples(aTuple: Tuple) =
  // aTuple: a tuple
  // returns: tuple, every other element of aTuple.
  val array = aTuple.toArray
  val result =
    for
      index <- 0 until array.length by 2
    yield
      array(index)
  Tuple.fromArray(result.toArray)


print(oddTuples(("I", "am", "a", "test", "tuple")))
