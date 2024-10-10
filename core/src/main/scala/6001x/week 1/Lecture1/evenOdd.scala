@main
def evenOdd =
  val x = readLine("Enter an integer: ").toInt

  if x % 2 == 0 then
    println("")
    println("Even")
  else
    println("")
    println("Odd")

  println("Done with conditional")
