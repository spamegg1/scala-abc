// 7.2 Write a program that prompts for a file name,
// then opens that file and reads through the file,
// looking for lines of the form:

// X-DSPAM-Confidence:    0.8475

// Count these lines and extract the floating point values from each of the lines
// and compute the average of those values
// and produce an output as shown below.
// Do not use the sum() function or a variable named sum in your solution.

// You can download the sample data at http://www.py4e.com/code3/mbox-short.txt
// when you are testing below enter mbox-short.txt as the file name.

// Use the file name mbox-short.txt as the file name

// Desired output:
// Average spam confidence: 0.7507185185185187
import scala.io.Source.fromResource

val file = fromResource("mbox-short.txt")

//val resourcePath = os.pwd/"src"/"main"/"resources"
//val fileName = resourcePath/"mbox-short.txt"

var total = 0.0
var count = 0

for
  // line <- os.read.lines(fileName)
  line <- file.getLines
  if line.startsWith("X-DSPAM-Confidence:")
do
  total += line.drop(line.length - 6).toDouble
  count += 1

println(f"Average spam confidence: ${total / count}")
file.close() // TODO: replace this with scala.util.Using(???)
