package MIT6001x.person

class UG(name: String, classYear: Int) extends MITPerson(name):
  def getClass = classYear

class Grad(name: String) extends MITPerson(name)

def isStudent1(obj: Object) = obj.isInstanceOf[UG] || obj.isInstanceOf[Grad]

@main
def students1 =
  val s1 = UG("Matt Damon", 2017)
  val s2 = UG("Ben Affleck", 2017)
  val s3 = UG("Arash Ferdowsi", 2018)
  val s4 = Grad("Drew Houston")
  val s5 = UG("Mark Zuckerberg", 2019)

  val studentList = List(s1, s2, s3, s5, s4)

  val p1 = MITPerson("Eric")
  val p2 = MITPerson("John Guttag")
  val p3 = MITPerson("John Smith")
  val p4 = Person("John")

  val p = Person("Spam Egg")
  println(p.getName) // Spam
  println(p.getLastName) // Egg
  p.setBirthday(6, 7, 1985)
  println(p.getAge) // 37

  val q = Person("Ham Python")
  println(p `compare` q) // true
  println(isStudent1(s1)) // true
  println(isStudent1(q)) // false
