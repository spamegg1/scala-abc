package curriculum
package mit6001x

@main
def readingData =
  val data = ArrayBuffer[String]()
  val fileName = "a.txt" // cannot be found
  // val fileName = "b.txt" // cannot be opened due to permissions
  // val fileName = "romeo.txt" // OK

  try
    val file = fromResource(fileName)
    for line <- file.getLines() do data += line
  catch case _: FileNotFoundException => println(s"$fileName cannot be found or opened.")

  data foreach println
