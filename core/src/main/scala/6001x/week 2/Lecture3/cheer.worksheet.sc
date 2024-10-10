val word = "scala"
val times = 5

val letters = "aefhilmnorsxAEFHILMNORSX"

var i = 0
while i < word.length do
  val char = word(i)
  if letters.contains(char) then println(s"Give me an ${char}! ${char}")
  else println(s"Give me a ${char}! ${char}")
  i += 1

println("What does that spell?")
for _ <- 0 until times do println(s"$word!!!")
