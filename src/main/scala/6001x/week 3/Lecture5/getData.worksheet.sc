def getData(aTuple: List[(Int, String)]) =
  var nums = List[Int]()
  var words = List[String]()
  for
    tuple <- aTuple
  do
    nums = tuple._1 :: nums
    if !words.contains(tuple._2) then
      words = tuple._2 :: words

  (nums.min, nums.max, words.length)

// TESTING
val result = getData(List(
  (1, "mine"),
  (3, "yours"),
  (5, "ours"),
  (7, "mine")
))
println(result)
