def isIn(char: Char, aStr: String): Boolean =
  // char: a single character
  // aStr: an alphabetized string
  // returns: True if char is in aStr; False otherwise
  val mid = aStr.length / 2

  if      aStr == ""        then false
  else if aStr.length == 1  then char == aStr(0)
  else if aStr(mid) == char then true
  else if aStr(mid) < char  then isIn(char, aStr.drop(mid + 1))
  else                           isIn(char, aStr.take(mid))

println(isIn('g', "abdefghijklop"))
