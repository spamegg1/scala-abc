def lyricsToFrequencies(lyrics: List[String]) =
  val myDict = MMap[String, Int]()
  for word <- lyrics do
    if myDict.contains(word) then myDict(word) += 1
    else myDict(word) = 1
  myDict

def mostCommonWords(freqs: MMap[String, Int]) =
  val best = freqs.values.max
  var words = List[String]()
  for (key, value) <- freqs do if value == best then words = key :: words
  (words, best)

def wordsOften(freqs: MMap[String, Int], minTimes: Int) =
  var result = List[(List[String], Int)]()
  var done = false
  while !done do
    val (words, best) = mostCommonWords(freqs)
    if best >= minTimes then
      result = (words, best) :: result
      for word <- words do freqs.remove(word) // remove word from dictionary
    else done = true
  result

@main
def songs =
  val sheLovesYou = fromResource("sheLovesYou.txt").getLines.toList
  val beatles = lyricsToFrequencies(sheLovesYou)
  wordsOften(beatles, 5).foreach(println)
// (List(love),5)
// (List(glad, should, that),7)
// (List(and),8)
// (List(be),10)
// (List(know),11)
// (List(loves),13)
// (List(she),20)
// (List(yeah),28)
// (List(you),36)
