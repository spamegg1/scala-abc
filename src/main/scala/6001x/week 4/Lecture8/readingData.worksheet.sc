import scala.io.StdIn.readLine
import scala.io.Source.fromResource
import collection.mutable.ArrayBuffer
import java.io.FileNotFoundException
import java.io.IOException

val data = ArrayBuffer[String]()
// val fileName = readLine("Provide a name of a file of data: ")
val fileName = "a.txt"

try
  val file = fromResource(fileName)
  for line <- file.getLines do data += line
catch
  case _: FileNotFoundException => println(s"file ${fileName} cannot be found.")
  case _: IOException           => println(s"cannot open ${fileName}.")
// finally file.close() // close file even if fail
