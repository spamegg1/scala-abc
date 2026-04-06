package curriculum
package mit6001x

// Consider the following function definition:
def badFact(n: Int): Int = // n >= 0
  if n == 0 then 1 // changed this from n to 1
  else n * badFact(n - 1)

// When we call f(3) we expect the result 6, but we get 0.
// When we call f(1) we expect the result 1, but we get 0.
// When we call f(0) we expect the result 1, but we get 0.

// Using this information, choose what line of code should
// be changed from the following choices:

// def f(n: Int): Int =
//   if n == 0 then
//     1 // THIS IS THE LINE I CHANGED
//   else
//     n * f(n - 1)

@main
def factorialBug: Unit =
  assert(badFact(3) == 6)
  assert(badFact(1) == 1)
  assert(badFact(0) == 1)
  println("Tests passed!")
