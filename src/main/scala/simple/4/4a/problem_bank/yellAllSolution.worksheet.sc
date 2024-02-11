def fnForStringList(stringList: List[String]): List[String] = stringList match
  case head :: next => ??? // head, fnForStringList(next)
  case Nil          => ???

// Template rules used:
// - one of: 2 cases
// - atomic distinct: empty
// - compound: (cons String ListOfString)
// - self-reference: (rest los) is ListOfString

// Functions:
// PROBLEM:
// Design a function that consumes a list of strings and "yells" each word by
// adding "!" to the end of each string.
// ListOfString -> ListOfString
// produces a list of strings with "!" added to the end of each word
yellAll(Nil) == Nil
yellAll(List("c", "b", "a")) == List("c!", "b!", "a!")
yellAll(List("hi", "wait", "what")) == List("hi!", "wait!", "what!")

// def yellAll(stringList: List[String]): List[String] = Nil // stub
// <template from ListOfString>

def yellAll(stringList: List[String]): List[String] = stringList match
  case head :: next => (head + "!") :: yellAll(next)
  case Nil          => Nil
