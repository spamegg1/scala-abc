package curriculum
package mit6001x

@main
def readDataRev1 =
  val fileName = "hello.txt"
  val file = fromResource(fileName)
  val data = ArrayBuffer[String]()

  try
    for line <- file.getLines()
    do data += line
  catch
    case _: AccessDeniedException => println(s"cannot open ${fileName}")
    case _: IOException           => println(s"cannot open ${fileName}")
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

  gradesData foreach println
