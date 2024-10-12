package curriculum
package mit6001x

type Word = String
type Words = List[Word]
type Hand = Map[Char, Int]

val WORDLISTFILENAME = "words.txt"
val WORDGAMEFILENAME = "wordgame.txt"
val STORYFILENAME = "story.txt"
val LOWER = "abcdefghijklmnopqrstuvwxyz"
val UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val VOWELS = "aeiou"
val PUNCTUATION = raw"(\p{Punct})"
val CONSONANTS = "bcdfghjklmnpqrstvwxyz"
val CONSONANTSUPPER = "BCDFGHJKLMNPQRSTVWXYZ"
val HANDSIZE = 10
val VOWELRATIO = 3
val BONUS = 50

val SCRABBLE = Map(
  'a' -> 1,
  'b' -> 3,
  'c' -> 3,
  'd' -> 2,
  'e' -> 1,
  'f' -> 4,
  'g' -> 2,
  'h' -> 4,
  'i' -> 1,
  'j' -> 8,
  'k' -> 5,
  'l' -> 1,
  'm' -> 3,
  'n' -> 1,
  'o' -> 1,
  'p' -> 3,
  'q' -> 10,
  'r' -> 1,
  's' -> 1,
  't' -> 1,
  'u' -> 1,
  'v' -> 4,
  'w' -> 4,
  'x' -> 8,
  'y' -> 4,
  'z' -> 10,
  '*' -> 0
)
