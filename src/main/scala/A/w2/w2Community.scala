package A.w2

/* COMMUNITY PROBLEMS FOR WEEK 3 */

/* Problem 1 - 4 */
type StudentId = Int
type Grade = Int /* must be in 0 to 100 range */
case class FinalGrade(id: StudentId, grade: Option[Grade])
enum PassFail:
  case Pass, Fail

import PassFail.*

def passOrFail(finalGrade: FinalGrade): PassFail =
  finalGrade.grade match
    case Some(i) => if i >= 75 then Pass else Fail
    case None    => Fail

def hasPassed(finalGrade: FinalGrade): Boolean =
  passOrFail(finalGrade) match
    case Pass => true
    case Fail => false

def numberPassed(xs: List[FinalGrade]): Int = xs match
  case Nil => 0
  case x :: xs =>
    if hasPassed(x)
    then 1 + numberPassed(xs)
    else numberPassed(xs)

def numberMisgraded(grades: List[(PassFail, FinalGrade)]): Int =
  grades match
    case Nil => 0
    case (pf, g) :: gs =>
      if passOrFail(g) == pf
      then numberMisgraded(gs)
      else 1 + numberMisgraded(gs)

/* PROBLEM 5 - 7 */
enum Tree[+T]:
  case Leaf
  case Node[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

enum Flag:
  case LeaveMeAlone, PruneMe

import Flag.*
import Tree.*

def treeHeight[T](t: Tree[T]): Int = t match
  case Leaf          => 0
  case Node(_, l, r) => 1 + math.max(treeHeight(l), treeHeight(r))

def sumTree(t: Tree[Int]): Int = t match
  case Leaf          => 0
  case Node(v, l, r) => v + sumTree(l) + sumTree(r)

def gardener(t: Tree[Flag]): Tree[Flag] = t match
  case Leaf                     => Leaf
  case Node(PruneMe, _, _)      => Leaf
  case Node(LeaveMeAlone, l, r) => Node(LeaveMeAlone, gardener(l), gardener(r))

/* PROBLEM 8 skipped */

/* PROBLEM 9 - 16 */
enum Nat:
  case Zero
  case Succ(n: Nat)

class Negative extends Exception

import Nat.*

def isPositive(nat: Nat): Boolean = nat match
  case Zero    => false
  case Succ(_) => true

def pred(nat: Nat): Nat = nat match
  case Zero    => throw new Negative
  case Succ(m) => m

def natToInt(nat: Nat): Int = nat match
  case Zero    => 0
  case Succ(m) => 1 + natToInt(m)

def intToNat(i: Int): Nat =
  if i < 0 then throw new Negative
  else if i == 0 then Zero
  else Succ(intToNat(i - 1))

def add(nat1: Nat, nat2: Nat): Nat = (nat1, nat2) match
  case (Zero, n2)     => n2
  case (Succ(m1), n2) => add(m1, Succ(n2))

def sub(nat1: Nat, nat2: Nat): Nat = (nat1, nat2) match
  case (n1, Zero)           => n1
  case (Succ(m1), Succ(n2)) => sub(m1, n2)
  case _                    => throw new Negative

def mult(nat1: Nat, nat2: Nat): Nat = (nat1, nat2) match
  case (Zero, n2)     => Zero
  case (n1, Zero)     => Zero
  case (Succ(m1), n2) => add(n2, mult(m1, n2))

def lessThan(nat1: Nat, nat2: Nat): Boolean = (nat1, nat2) match
  case (_, Zero)          => false
  case (Zero, _)          => true
  case (Succ(m), Succ(n)) => lessThan(m, n)

/* PROBLEM 17 - 19 */
enum IntSet:
  case Elems(xs: List[Int])
  case Range(from: Int, to: Int)
  case Union(s1: IntSet, s2: IntSet)
  case Intersection(s1: IntSet, s2: IntSet)

import IntSet.*

/* Helper functions used */
def doesInclude(xs: List[Int], n: Int): Boolean = xs match
  case Nil     => false
  case x :: xs => x == n || doesInclude(xs, n)

def intersect(ls1: List[Int], ls2: List[Int]): List[Int] =
  def helper(l1: List[Int], l2: List[Int], rsf: List[Int]): List[Int] =
    (l1, l2) match
      case (Nil, _) => rsf
      case (_, Nil) => rsf
      case (x :: xs, l2) =>
        if doesInclude(l2, x)
        then helper(xs, l2, x :: rsf)
        else helper(xs, l2, rsf)
  helper(ls1, ls2, Nil)

def union(ls1: List[Int], ls2: List[Int]): List[Int] =
  def helper(l1: List[Int], l2: List[Int], rsf: List[Int]): List[Int] =
    l1 match
      case Nil => rsf
      case (x :: xs) =>
        if doesInclude(l2, x)
        then helper(xs, l2, rsf)
        else helper(xs, l2, x :: rsf)
  helper(ls1, ls2, ls2)

def range(m: Int, n: Int): List[Int] =
  if m > n then Nil
  else m :: range(m + 1, n)

def filterDuplicate(lst: List[Int]) =
  def helper(ls: List[Int], rsf: List[Int]): List[Int] =
    ls match
      case Nil => rsf
      case x :: xs =>
        if doesInclude(rsf, x)
        then helper(xs, rsf)
        else helper(xs, x :: rsf)
  helper(lst, Nil)

/* PROBLEM 18 */
def contains(mySet: IntSet, num: Int): Boolean = mySet match
  case Elems(Nil)           => false
  case Elems(xs)            => doesInclude(xs, num)
  case Range(m, n)          => (m <= num) && (num <= n)
  case Union(i1, i2)        => contains(i1, num) || contains(i2, num)
  case Intersection(i1, i2) => contains(i1, num) && contains(i2, num)

/* PROBLEM 19 */
def toList(mySet: IntSet): List[Int] = mySet match
  case Elems(xs)              => filterDuplicate(xs)
  case Range(m, n)            => range(m, n)
  case Union(is1, is2)        => union(toList(is1), toList(is2))
  case Intersection(is1, is2) => intersect(toList(is1), toList(is2))

/* PROBLEM 17 */
def isEmpty(mySet: IntSet): Boolean = mySet match
  case Elems(Nil)    => true
  case Elems(_)      => false
  case Range(m, n)   => m > n
  case Union(i1, i2) => isEmpty(i1) && isEmpty(i2)
  case Intersection(i1, i2) =>
    isEmpty(i1) || isEmpty(i2) || intersect(toList(i1), toList(i2)) == Nil
