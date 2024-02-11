package A.w3

/*
1.  Write a function
      composeOpt: (S => Option[T]) => (T => Option[U]) => T => Option[U]
    that composes two functions with "optional" values.
    If either function returns None, then the result is None.
 */
def composeOpt[S, T, U](f: T => Option[U])(g: S => Option[T])(s: S): Option[U] =
  g(s) match
    case None    => None
    case Some(t) => f(t)

/*
2.  Write a function
      doUntil: (T => T) => (T => Boolean) => T => T
      doUntil(f)(p)(x)
    will apply f to x and f again to that result and so on until p x is false.
    Example:
      doUntil(x => x / 2)(x => x % 2 != 1)
    will evaluate to a function of type Int => Int that divides its argument by
    2 until it reaches an odd number.
    In effect, it will remove all factors of 2 from its argument.
 */
def doUntil[T](f: T => T)(pred: T => Boolean)(x: T): T =
  if pred(x)
  then doUntil(f)(pred)(f(x))
  else x

/* 3.
    Use doUntil to implement factorial.
 */
def factorial(n: Int): Int =
  // def nonzero(pair: (Int, Int)): Boolean = pair._1 != 0
  // def multiply(pair: (Int, Int)): (Int, Int) = (pair._1 - 1, pair._1 * pair._2)
  // doUntil(multiply)(nonzero)((n, 1))._2
  val nonzero = (x: Int, _: Int) => x != 0
  val multiply = (x: Int, y: Int) => (x - 1, x * y)
  doUntil(multiply.tupled)(nonzero.tupled)(n, 1)._2

/*
4.  Use doUntil to write a function
      fixedPoInt: (T => T) => T => T
    that given a function f and an zeroial value x,
    applies f to x until f(x) = x.
 */
def fixedPoint[T](f: T => T)(zero: T): T =
  doUntil(f)(x => f(x) != x)(zero)

/*
5.  Write a function
      map2 : (T => S) => (T, T) => (S, S)
    that given a function that takes T values to S values
    and a pair of T values
    returns the corresponding pair of S values.
 */
def map2[T, S](f: T => S)(x: T, y: T): (S, S) = (f(x), f(y))

/*
6.  Write a function
      appAll: (T => List[U]) => (S => List[T]) => S => List[U]
    so that:
      appAll(f)(g)(x) will apply f to every element of the list g(x)
    and concatenate the results Into a single list.
    For example, for val f = n: Int => List(n, 2 * n, 3 * n), we have
      appAll(f)(f)(1) = List(1, 2, 3, 2, 4, 6, 3, 6, 9)
 */
def appAll[S, T, U](f: T => List[U])(g: S => List[T])(s: S): List[U] =
  def helper[T, U](f1: T => List[U])(ts: List[T]): List[U] = ts match
    case Nil      => Nil
    case t :: ts1 => f1(t) ::: helper(f1)(ts1)
  helper(f)(g(s))

/*
7.  Implement myFoldRight
    Its type is myFoldRight[B](zero: B)(op: (A, B) => B)(lst: List[A]): B
      foldr(zero)(op)(List(x1, x2, ..., xn)) returns
        op(x1, op(x2, ..., op(xn, zero)...))
    or zero if the list is empty.
 */
def myFoldRight[A, B](zero: B)(op: (A, B) => B)(lst: List[A]): B =
  lst match
    case Nil     => zero
    case x :: xs => op(x, (myFoldRight(zero)(op)(xs)))

/*
8.  Write a function
      partition : (T => Boolean) => List[T] => (List[T], List[T])
    where the first part of the result contains the second argument elements for
    which the first element evaluates to true and the second part of the result
    contains the other second argument elements.
    Traverse the second argument only once.
 */
def partition[T](f: T => Boolean)(lst: List[T]): (List[T], List[T]) =
  lst match
    case Nil => (Nil, Nil)
    case x :: xs =>
      val (first, second) = partition(f)(xs)
      if f(x)
      then (x :: first, second)
      else (first, x :: second)

/*
9.  Write a function
      unfold : (T => Option[(S, T)]) => T => List[S]
    that produces a list of S values, given:
      a "seed" of type T, and
      a function that given a seed,
        produces Some of a pair of a S value and a new seed, or
        None if it is done seeding.
    For example, here is an elaborate way to count down from 5:
    unfold(n => if n == 0 then None else Some(n, n-1))(5) = List(5, 4, 3, 2, 1)
 */
def unfold[T, S](f: T => Option[(S, T)])(seed: T): List[S] =
  f(seed) match
    case None             => Nil
    case Some(s, newseed) => s :: unfold(f)(newseed)

/*
10. Use unfold and myFoldRight to implement factorial.
 */
def factorial2(n: Int): Int =
  val countdown = (n: Int) => if n == 0 then None else Some(n, n - 1)
  val mult = (x: Int, y: Int) => x * y
  myFoldRight(1)(mult)(unfold(countdown)(n))

/*
11. Implement map using myFoldRight.
 */
def mymap[T, S](f: T => S)(lst: List[T]): List[S] =
  myFoldRight(Nil)((t: T, ss: List[S]) => f(t) :: ss)(lst)

/*
12. Implement filter using myFoldRight.
 */
def myfilter[T](pred: T => Boolean)(lst: List[T]): List[T] =
  val binOp = (t: T, ts: List[T]) => if pred(t) then t :: ts else ts
  myFoldRight(Nil)(binOp)(lst)

/*
13. Implement myFoldLeft using myFoldRight on functions. (This is challenging.)
      myFoldLeft(zero)(f)(List(x1, x2, ..., xn))
    returns
      f(xn,...,f(x2, f(x1, zero))...)
    or zero if the list is empty.
 */
def myFoldLeft[T, S](zero: S)(f: (T, S) => S)(lst: List[T]): S =
  myFoldRight(zero)(f)(lst.reverse)

def myFoldLeft2[T, S](zero: S)(f: (T, S) => S)(lst: List[T]): S =
  lst match
    case Nil     => zero
    case x :: xs => myFoldLeft2(f(x, zero))(f)(xs)

/*
14. Define a (polymorphic) type for binary trees
    where data is at Internal Nodes but not at leaves.
    Define map and fold functions over such trees.
    You can define filter as well where we interpret a "false" as meaning:
    the entire subtree rooted at the Node with data that produced false
    should be replaced with a Leaf.
 */
enum Tree[+T]:
  case Leaf
  case Node[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

import Tree.*

/* Apply function f to the Internal Nodes only, and recurse */
def treeMap[T](f: T => T)(tree: Tree[T]): Tree[T] = tree match
  case Leaf          => Leaf
  case Node(v, l, r) => Node(f(v), treeMap(f)(l), treeMap(f)(r))

def treeFilter[T](f: T => Boolean)(tree: Tree[T]): Tree[T] =
  tree match
    case Leaf => Leaf
    case Node(v, l, r) =>
      if f(v) then Node(v, treeFilter(f)(l), treeFilter(f)(r))
      else Leaf

def treeFold[T](f: (T, T, T) => T)(acc: T)(tree: Tree[T]): T =
  tree match
    case Leaf          => acc
    case Node(v, l, r) => f(treeFold(f)(acc)(l), v, treeFold(f)(acc)(r))
