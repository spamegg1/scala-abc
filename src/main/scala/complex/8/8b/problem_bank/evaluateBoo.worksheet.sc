// Given the following function definition:
def boo(x: Int, list: List[Int]): List[Int] =
  def addx(n: Int) = x + n
  if x == 0 then Nil
  else addx(list.head) :: boo(x - 1, list.tail)

// PROBLEM A:
// What is the value of the following expression:
boo(2, List(10, 20))
// NOTE: We are not asking you to show the full step-by-step evaluation for
// this problem, but you may want to sketch it out to help you get these
// questions right.
List(12, 21)

// PROBLEM B:
// How many function definitions are lifted during the evaluation of the
// expression in part A.
// 3

// PROBLEM C:
// Write out the lifted function definition(s). Just the actual lifted function
// definitions.
def addx_0(n: Int) = n + 2
def addx_1(n: Int) = n + 1
def addx_2(n: Int) = n + 0
