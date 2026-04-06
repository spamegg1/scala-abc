// Assume s is a string of lower case characters.
// Write a program that printlns the longest substring of s in which the letters
// occur in alphabetical order. For example, if s = "azcbobobegghakl", then your
// program should println
// Longest substring in alphabetical order is: beggh
// In the case of ties, println the first substring. For example, if s = "abcbcd",
// then your program should println
// Longest substring in alphabetical order is: abc
// Note: This problem may be challenging. We encourage you to work smart.
// If you"ve spent more than a few hours on this problem,
// we suggest that you move on to a different part of the course.
// If you have time, come back to this problem after you"ve had a break and
// cleared your head.

def longestSubstring(s: String): String =
  // Returns the longest substring in string
  var substring = s(0).toString
  var longest = substring

  for i <- 1 until s.length do
    if s(i - 1) <= s(i) then
      substring += s(i)
      if substring.length > longest.length then longest = substring
    else substring = s(i).toString

  println(s"Longest substring in alphabetical order is: $longest")
  longest

// TESTING
val WORDS = List(
  "dbxuofuzgon",
  "fsagwlltxifaqg",
  "bogvxlzntxzurfdxqiz",
  "twameqnktryeugaufxic",
  "kapyrljsstow",
  "kdfopywicsstu",
  "upqnbtxfywnuvisrpg",
  "abcdefghijklmnopqrstuvwxyz",
  "lsbjatmsvwwrdxw",
  "wjhprvhwneixskmgpyj",
  "hjfnqvttoqrp",
  "zyxwvutsrqponmlkjihgfedcba",
  "rvdaorualoonojnzt",
  "grqdjfzworbuiededct",
  "zqfreprxpbdfxkcjaj",
  "jccuqkkb",
  "iodrczqeih",
  "iaysxzeidqgwdc",
  "aiikeudqfyikcooed",
  "ligwdpsszgbzjw",
  "azcbobobegghakl",
  "abcbcd",
  "abczhefghijtatuvwxy",
  "abczhefghijtatuvwxyz",
  "aaaaaaaaaaaabaaaaaaaaaazaaaaaaaaaafaaaaaaaaayaaaaaaaa"
)
val ANSWERS = List(
  "fuz",
  "lltx",
  "ntxz",
  "tw",
  "jsst",
  "dfopy",
  "btx",
  "abcdefghijklmnopqrstuvwxyz",
  "msvww",
  "hprv",
  "fnqv",
  "z",
  "aoru",
  "gr",
  "eprx",
  "ccu",
  "io",
  "sxz",
  "aiik",
  "dpssz",
  "beggh",
  "abc",
  "efghijt",
  "atuvwxyz",
  "aaaaaaaaaaaab"
)

assert(WORDS.map(longestSubstring) == ANSWERS)
println("All tests passed!")
