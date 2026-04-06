// Now that we understand how to form data definitions for abitrary-sized data
// we can look at how to design functions that "count down" on natural numbers.
// Here's the key inight - ask yourself how many natural numbers are there?
// A lot, many... an arbitrary number:
// 0                      0
// (add1 0)               1
// (add1 (add1 0))        2
// (add1 (add1 (add1 0))) 3
// and so on
// What that is saying is that (add1 <some natural>) produces a natural,
// similarly (sub1 <some natural other than 0>) produces a natural.
// So add1 is kind of like cons, it takes a natural and makes a bigger one
// (cons makes a longer list). And sub1 is kind of like rest it takes a natural
// (other than 0) and gives the next smallest one (rest gives shorter list).
// Consider this type comment:
// Natural is one of:
//  - 0
//  - (add1 Natural)
// interp. a natural number
enum Natural:
  case Zero
  case Successor(n: Natural)
import Natural.*
// sealed trait Natural
// case object Zero extends Natural
// case class Successor(n: Natural) extends Natural

val nat0 = Zero // 1
val nat1 = Successor(nat0) // 1
val nat2 = Successor(nat1) // 2
val nat3 = Successor(nat2) // 3

def fnForNatural(natural: Natural) = natural match
  case Zero         => ???
  case Successor(n) => ??? // n

// Template rules used:
//  - one-of: two cases
//  - atomic distinct: 0
//  - compound: (add1 Natural)
//  - self-reference: (sub1 n) is Natural

// PROBLEMs:
// (A) Design a function that consumes Natural number n and produces the sum of
// all the naturals in [0, n].
// Natural -> Natural
// produce total of Natural[0, n]
total(nat0) == Zero
total(nat1) == Successor(Zero)
total(nat2) == Successor(nat2)
total(nat3) == Successor(Successor(Successor(nat3)))

// def total(natural: Natural): Natural = natural // stub
// <template from Natural>
def total(natural: Natural): Natural = natural match
  case Zero         => Zero
  case Successor(n) => add(total(n), natural)

add(Zero, Zero) == Zero
add(nat1, Zero) == nat1
add(Zero, nat1) == nat1
add(nat1, nat1) == nat2
add(nat1, nat2) == nat3
add(nat2, nat1) == nat3

def add(succ1: Natural, succ2: Natural): Natural = succ2 match
  case Zero         => succ1
  case Successor(n) => add(Successor(succ1), n)

// (B) Design a function that consumes Natural number n and produces a list of
// all the naturals of the form (cons n (cons n-1 ... empty)) not including 0.
// Natural -> ListOfNatural
// produce (cons n (cons n-1 ... empty)), not including 0
// <template from Natural>
toList(nat0) == Nil
toList(nat1) == List(nat1)
toList(nat2) == List(nat2, nat1)
toList(nat3) == List(nat3, nat2, nat1)

def toList(natural: Natural): List[Natural] = natural match
  case Zero         => Nil
  case Successor(n) => Successor(n) :: toList(n)
