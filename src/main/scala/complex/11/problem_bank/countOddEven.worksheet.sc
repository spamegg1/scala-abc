// PROBLEM:
// Previously we have written functions to count the number of elements in a
// list. In this problem we want a function that produces separate counts of the
// number of odd and even numbers in a list, and we only want to traverse the
// list once to produce that result. Design a tail recursive function that
// produces the Counts for a given list of numbers.
// Your function should produce Counts, as defined by the data definition below.
// There are two ways to code this function, one with 2 accumulators and one
// with a single accumulator. You should provide both solutions.

case class Counts(odds: Int, evens: Int)
// interp. describes the number of even and odd numbers in a list
val counts1 = Counts(0, 0) // Nil
val counts2 = Counts(3, 2) // List(1, 2, 3, 4, 5)

// (listof Integer) -> Counts
// Produce the count of odd and even numbers in lon
count(Nil) == Counts(0, 0)
count(List(1)) == Counts(1, 0)
count(List(2)) == Counts(0, 1)
count(List(1, 2, 3, 4, 5)) == Counts(3, 2)
count(List(-1, -2, 3, -4, 5)) == Counts(3, 2)

// <template according to (listof Number) + 2 accumulators>
def count(list: List[Int]): Counts =
  def helper(ints: List[Int], odds: Int, evens: Int): Counts = ints match
    case head :: next =>
      if head % 2 == 0
      then helper(next, odds, evens + 1)
      else helper(next, odds + 1, evens)
    case Nil => Counts(odds, evens)
  helper(list, 0, 0)

count2(Nil) == Counts(0, 0)
count2(List(1)) == Counts(1, 0)
count2(List(2)) == Counts(0, 1)
count2(List(1, 2, 3, 4, 5)) == Counts(3, 2)
count2(List(-1, -2, 3, -4, 5)) == Counts(3, 2)

// <template according to (listof Number) + accumulator>
def count2(list: List[Int]): Counts =
  def helper(ints: List[Int], counts: Counts): Counts = ints match
    case head :: next =>
      if head % 2 == 0
      then helper(next, Counts(counts.odds, counts.evens + 1))
      else helper(next, Counts(counts.odds + 1, counts.evens))
    case Nil => counts
  helper(list, Counts(0, 0))
