def genSubsets(L: List[Int]): List[List[Int]] =
  if L.length == 0 then
    List(List[Int]())  // list of empty list
  else
    val smaller = genSubsets(L.take(L.length - 1)) // all subsets without last element
    val extra = L.last  // create a list of just last element
    var newSets = List[List[Int]]()
    for
      small <- smaller
    do
      // for all smaller solutions, add one with last element
      newSets = (extra :: small) :: newSets
    smaller ::: newSets  // combine those with last element and those without


val TEST = List(1, 2, 3, 4)
genSubsets(TEST)
