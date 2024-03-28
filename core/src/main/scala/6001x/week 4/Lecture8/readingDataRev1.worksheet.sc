import scala.io.StdIn.readLine
import scala.io.Source.fromResource
import collection.mutable.ArrayBuffer
import java.io.IOException

//val fileName = readLine("Provide a name of a file of data: ")
val fileName = "hello.txt"
val file = fromResource(fileName)
val data = ArrayBuffer[String]()

try
  for line <- file.getLines
  do data += line
catch case _: IOException => println(s"cannot open ${fileName}")
finally file.close() // close file even if fail

val gradesData = ArrayBuffer[(String, List[String])]()
if data.nonEmpty then
  for student <- data do
    try
      val item = (student.take(2), List(student.take(3).drop(2)))
      gradesData += item
    catch
      case _: StringIndexOutOfBoundsException =>
        val item = (student.take(2), List())
        gradesData += item
