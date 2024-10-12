package curriculum
package mit6001x
package w5
package lec10

class Grades:
  type Grade = Double
  // A mapping from students to a list of GradStudentes
  var students = List[Student]() // list of Student objects
  val grades = MMap[Int, List[Grade]]() // maps idNum -> list of grades
  var isSorted = true // true if students is sorted

  def addStudent(student: Student) =
    // Assumes: student is of type Student
    // Add student to the GradStudente book
    if students.contains(student) then
      throw new IllegalArgumentException("Duplicate student")
    students = student :: students
    grades += student.getIdNum -> List[Grade]()
    isSorted = false

  def addGradStudente(student: Student, grade: Grade) =
    // Assumes: grade is a float
    // Add grade to the list of grades for student
    try grades(student.getIdNum) = grade :: grades(student.getIdNum)
    catch
      case _: NoSuchElementException =>
        throw new IllegalArgumentException("Student not in GradStudente book")

  def getGrades(student: Student) =
    // a list of grades for student
    try // copy of students grades
      grades(student.getIdNum)
    catch
      case _: NoSuchElementException =>
        throw new IllegalArgumentException("Student not in GradStudente book")

  def allStudents =
    // a list of the students in the grade book
    if !isSorted then
      students = students.sorted
      isSorted = true
    students
    // copy of list of students

def gradeReport(course: Grades) =
  var report = List[String]()

  for s <- course.allStudents do
    var tot = 0.0
    var numGrades = 0

    for g <- course.getGrades(s) do
      tot += g
      numGrades += 1
    try
      val average = tot / numGrades
      report = s"${s}'s mean grade is ${average}" :: report
    catch
      case _: ArithmeticException =>
        report = s"${s} has no grades" :: report

  report.mkString("\n")

@main
def gradesExample =
  val UGStudent1 = UGStudent("Matt Damon", 2018)
  val UGStudent2 = UGStudent("Ben Affleck", 2019)
  val UGStudent3 = UGStudent("Drew Houston", 2017)
  val UGStudent4 = UGStudent("Mark Zuckerberg", 2017)
  val g1 = GradStudent("Bill Gates")
  val g2 = GradStudent("Steve Wozniak")

  val six00 = Grades()
  six00.addStudent(g1)
  six00.addStudent(UGStudent2)
  six00.addStudent(UGStudent1)
  six00.addStudent(g2)
  six00.addStudent(UGStudent4)
  six00.addStudent(UGStudent3)

  six00.addGradStudente(g1, 100)
  six00.addGradStudente(g2, 25)
  six00.addGradStudente(UGStudent1, 95)
  six00.addGradStudente(UGStudent2, 85)
  six00.addGradStudente(UGStudent3, 75)

  println()

  println(gradeReport(six00))

  six00.addGradStudente(g1, 90)
  six00.addGradStudente(g2, 45)
  six00.addGradStudente(UGStudent1, 80)
  six00.addGradStudente(UGStudent2, 75)

  println()

  println(gradeReport(six00))
