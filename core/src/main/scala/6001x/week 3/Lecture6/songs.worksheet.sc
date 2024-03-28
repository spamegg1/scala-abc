import collection.mutable.Map

def lyricsToFrequencies(lyrics: List[String]) =
  val myDict = Map[String, Int]()
  for
    word <- lyrics
  do
    if myDict.contains(word) then
      myDict(word) += 1
    else
      myDict(word) = 1
  myDict


val sheLovesYou = List(
  "she", "loves", "you", "yeah", "yeah", "yeah",
  "she", "loves", "you", "yeah", "yeah", "yeah",
  "she", "loves", "you", "yeah", "yeah", "yeah",
  "you", "think", "you've", "lost", "your", "love",
  "well", "i", "saw", "her", "yesterday-yi-yay",
  "it's", "you", "she's", "thinking", "of",
  "and", "she", "told", "me", "what", "to", "say-yi-yay",
  "she", "says", "she", "loves", "you",
  "and", "you", "know", "that", "can't", "be", "bad",
  "yes", "she", "loves", "you",
  "and", "you", "know", "you", "should", "be", "glad",
  "she", "said", "you", "hurt", "her", "so",
  "she", "almost", "lost", "her", "mind",
  "and", "now", "she", "says", "she", "knows",
  "you're", "not", "the", "hurting", "kind",
  "she", "says", "she", "loves", "you",
  "and", "you", "know", "that", "can't", "be", "bad",
  "yes", "she", "loves", "you",
  "and", "you", "know", "you", "should", "be", "glad",
  "oo", "she", "loves", "you", "yeah", "yeah", "yeah",
  "she", "loves", "you", "yeah", "yeah", "yeah",
  "with", "a", "love", "like", "that",
  "you", "know", "you", "should", "be", "glad",
  "you", "know", "it's", "up", "to", "you",
  "i", "think", "it's", "only", "fair",
  "pride", "can", "hurt", "you", "too",
  "pologize", "to", "her",
  "Because", "she", "loves", "you",
  "and", "you", "know", "that", "can't", "be", "bad",
  "Yes", "she", "loves", "you",
  "and", "you", "know", "you", "should", "be", "glad",
  "oo", "she", "loves", "you", "yeah", "yeah", "yeah",
  "she", "loves", "you", "yeah", "yeah", "yeah",
  "with", "a", "love", "like", "that",
  "you", "know", "you", "should", "be", "glad",
  "with", "a", "love", "like", "that",
  "you", "know", "you", "should", "be", "glad",
  "with", "a", "love", "like", "that",
  "you", "know", "you", "should", "be", "glad",
  "yeah", "yeah", "yeah",
  "yeah", "yeah", "yeah", "yeah"
)

val beatles = lyricsToFrequencies(sheLovesYou)

def mostCommonWords(freqs: Map[String, Int]) =
  val best = freqs.values.max
  var words = List[String]()
  for
    (key, value) <- freqs
  do
    if value == best then
      words = key :: words
  (words, best)


def wordsOften(freqs: Map[String, Int], minTimes: Int) =
  var result = List[(List[String], Int)]()
  var done = false
  while
    !done
  do
    val (words, best) = mostCommonWords(freqs)
    if best >= minTimes then
      result = (words, best) :: result
      for
        word <- words
      do
        freqs.remove(word)  // remove word from dictionary
    else
      done = true
  result

wordsOften(beatles, 5)
