package MIT6001x.person

class Student(name: String) extends MITPerson(name)

class UGStudent(name: String, classYear: Int) extends Student(name):
  def getClass = classYear

class GradStudent(name: String) extends Student(name)

class TransferStudent(name: String) extends Student(name)

def isStudent(obj: Object) = obj.isInstanceOf[Student]

@main
def students =
  val s1 = UGStudent("Matt Damon", 2017)
  val s2 = UGStudent("Ben Affleck", 2017)
  val s3 = UGStudent("Arash Ferdowsi", 2018)
  val s4 = GradStudent("Drew Houston")
  val s5 = UGStudent("Mark Zuckerberg", 2019)
  val p1 = MITPerson("Eric Grimson")
  val p2 = MITPerson("John Guttag")
  val p3 = MITPerson("Ana Bell")
  val q1 = Person("Bill Gates")
  val q2 = Person("Travis Kalanick")

  val studentList = List(s1, s2, s3, s5, s4)

  val allList = studentList ::: List(p1, p2, p3) ::: List(q1, q2)
