val isPositive = (n: Int) => n > 0
val isNegative = (n: Int) => n < 0

// ListOfString -> Boolean
// produce true if los includes "UBC"
!containsUBC(Nil)
!containsUBC(List("McGill"))
containsUBC(List("UBC"))
containsUBC(List("McGill", "UBC"))

// def containsUBC(words: List[String]): Boolean = false // stub
def containsUBC(words: List[String]): Boolean = contains("UBC", words)

// ListOfString -> Boolean
// produce true if los includes "McGill"
!containsMcGill(Nil)
!containsMcGill(List("UBC"))
containsMcGill(List("McGill"))
containsMcGill(List("McGill", "UBC"))

// def containsMcGill(words: List[String]): Boolean = false // stub
def containsMcGill(words: List[String]): Boolean = contains("McGill", words)

// produce true if los includes s
!contains("UBC", Nil)
!contains("UBC", List("McGill"))
contains("UBC", List("UBC", "McGill"))
!contains("Toronto", List("UBC", "McGill"))

def contains(word: String, words: List[String]): Boolean =
  words.exists(_.contains(word))

// ListOfNumber -> ListOfNumber
// produce list of sqr of every number in lon
squares(Nil).isEmpty
squares(List(3, 4)) == List(9, 16)
// def squares(list: List[Int]): List[Int] = Nil // stub
def squares(list: List[Int]): List[Int] = list map (x => x * x)

// ListOfNumber -> ListOfNumber
// produce list of sqrt of every number in lon
squareRoots(Nil).isEmpty
squareRoots(List(9, 16)) == List(3, 4)
// def squareRoots(list: List[Int]): List[Int] = Nil // stub
def squareRoots(list: List[Double]): List[Double] = list map Math.sqrt

// ListOfNumber -> ListOfNumber
// produce list with only positive? elements of lon
positiveOnly(Nil).isEmpty
positiveOnly(List(1, -2, 3, -4)) == List(1, 3)

// def positiveOnly(list: List[Int]): List[Int] = Nil // stub
def positiveOnly = filter2(isPositive)

// ListOfNumber -> ListOfNumber
// produce list with only negative? elements of lon
negativeOnly(Nil).isEmpty
negativeOnly(List(1, -2, 3, -4)) == List(-2, -4)

// def negativeOnly(list: List[Int]): List[Int] = Nil // stub
def negativeOnly = filter2(isNegative)

def filter2(pred: Int => Boolean)(list: List[Int]): List[Int] = list match
  case head :: next =>
    if pred(head) then head :: filter2(pred)(next)
    else filter2(pred)(next)
  case Nil => Nil
