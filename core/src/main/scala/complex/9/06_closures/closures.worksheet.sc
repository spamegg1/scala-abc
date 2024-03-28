// (listof Number) -> (listof Number)
// produce list of each number in lon cubed
cubeAll(List(1, 2, 3)) == List(1, 8, 27)

//<template as call to map>
def cubeAll(list: List[Int]): List[Int] =
  list.map(n => n * n * n)

// String (listof String) -> (listof String)
// produce list of all elements of los prefixed by p
prefixAll("accio ", List("portkey", "broom")) ==
  List("accio portkey", "accio broom")

//<template as call to map>
def prefixAll(prefix: String, words: List[String]): List[String] =
  words.map(word => prefix + word)
