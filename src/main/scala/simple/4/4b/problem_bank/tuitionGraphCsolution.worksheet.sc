case class School(name: String, tuition: Double)

def cheapest(schools: List[School]): School = schools match
  case head :: Nil => head
  case head :: next =>
    val rest = cheapest(next)
    if cheaper(head, rest)
    then head
    else rest
  case Nil => School("", 0)

def cheaper(school1: School, school2: School): Boolean =
  school1.tuition < school2.tuition

def getNames(schools: List[School]): List[String] = schools match
  case head :: next => head.name :: getNames(next)
  case Nil          => Nil
