package curriculum
package py4e

// 8.4 Open the file romeo.txt and read it line by line.
// For each line, split the line into a words of words using the split() method.
// The program should build a words of words.
// For each word on each line check to see if the word is already in the words
// and if not append it to the words.
// When the program completes, sort
// and print the resulting words in alphabetical order.
// You can download the sample data at http://www.py4e.com/code3/romeo.txt

// Desired output:
// ['Arise', 'But', 'It', 'Juliet', 'Who', 'already', 'and', 'breaks', 'east',
// 'envious', 'fair', 'grief', 'is', 'kill', 'light', 'moon', 'pale', 'sick',
// 'soft', 'sun', 'the', 'through', 'what', 'window', 'with', 'yonder']

@main
def quiz84 =
  val fileName = "romeo.txt"
  var words = List[String]()

  Using.resource(fromResource(fileName)): file =>
    for
      line <- file.getLines
      word <- line.split(" ")
      if !words.contains(word)
    do words = word :: words

  println(words.sorted)
