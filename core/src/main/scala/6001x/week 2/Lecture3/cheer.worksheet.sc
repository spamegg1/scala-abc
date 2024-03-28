import scala.io.StdIn.readLine

val letters = "aefhilmnorsxAEFHILMNORSX"

// val word = readLine("I will cheer for you! Enter a word: ")
val word = "scala"
// val times = readLine("Enthusiasm level (1-10): ").toInt
val times = 5

var i = 0
while i < word.length
do
  val char = word(i)
  if letters.contains(char) then println(s"Give me an ${char}! ${char}")
  else println(s"Give me a ${char}! ${char}")
  i += 1

println("What does that spell?")

for _ <- 0 until times
do println(word + "!!!")
