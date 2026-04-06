// Data definitions:
// PROBLEM A:
// Design a data definition to help a teacher organize their next field trip.
// On the trip, lunch must be provided for all students. For each student, track
// their name, their grade (from 1 to 12), and whether or not they have allergies.
case class Student(name: String, grade: Int, hasAllergies: Boolean):
  require(1 <= grade && grade <= 12)
// Student is (make-student String Natural[1, 12] Boolean)
// interp. a student with a name, in grade 1-12, and true if they have allergies
val s1 = Student("Bob", 2, true)
val s2 = Student("Hannah", 5, false)
val s3 = Student("Joanne", 10, true)
val s4 = Student("John", 11, false)

def fnForStudent(s: Student) = ??? // ???(s.name, s.grade, s.hasAllergies)
// Template rules used:
// - compound: 3 fields

// Functions:
// PROBLEM B:
// To plan for the field trip, if students are in grade 6 or below, the teacher
// is responsible for keeping track of their allergies. If a student has allergies,
// and is in a qualifying grade, their name should be added to a special list.
// Design a function to produce true if a student name should be added to this list.

// Student -> Boolean
// produce true if the given student is at or below grade 6 and has allergies
addName(s1)
!addName(s2)
!addName(s3)
!addName(s4)

// def addName(s: Student): Boolean = true // stub
// Template from Student
def addName(s: Student): Boolean =
  s.grade <= 6 && s.hasAllergies
