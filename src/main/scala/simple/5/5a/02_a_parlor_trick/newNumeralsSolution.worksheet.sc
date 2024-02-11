// PROBLEM:
// Your friend has just given you a new pad, and it runs a prototype version
// of Racket. This is great, you can make it do anything. There's just one
// problem, this version of racket doesn't include numbers as primitive data.
// There just are no numbers in it!
// But you need natural numbers to write your next program.
// No problem you say, because you remember the well-formed self-referential
// data definition for Natural, as well as the idea that add1 is kind of like
// cons, and sub1 is kind of like rest. Your idea is to make add1 actually be
// cons, and sub1 actually be rest...
// NATURAL is one of:
//  - empty
//  - (cons "!" NATURAL)
// interp. a natural number, the number of "!" in the list is the number
enum Nat:
  case Zero
  case Succ(n: Nat)

import Nat.*

val nat0 = Zero // 0
val nat1 = Succ(nat0) // 1
val nat2 = Succ(nat1) // 2
val nat3 = Succ(nat2) // 3
val nat4 = Succ(nat3) // 4
val nat5 = Succ(nat4) // 5
val nat6 = Succ(nat5) // 6

// Template
def fnForNat[T](nat: Nat): T = nat match
  case Zero    => ???
  case Succ(n) => fnForNat[T](n)

// These are the primitives that operate NATURAL:
def isZero(nat: Nat): Boolean = nat match
  case Zero    => true
  case Succ(n) => false
def add1(nat: Nat): Nat = Succ(nat)
def sub1(nat: Nat): Nat = nat match
  case Zero    => Zero
  case Succ(n) => n

// template for functions that take a pair of naturals
def fnForNatPair[T](nat1: Nat, nat2: Nat): T = (nat1, nat2) match
  case (Zero, Zero)       => ???
  case (Zero, Succ(n))    => ???
  case (Succ(n), Zero)    => ???
  case (Succ(n), Succ(m)) => ???

// NATURAL NATURAL -> NATURAL
// produce a + b
add(nat2, nat0) == nat2
add(nat0, nat3) == nat3
add(nat3, nat2) == nat5
add(nat2, nat3) == nat5

// def add(nat1: Nat, nat2: Nat): Nat = Zero // stub
def add(nat1: Nat, nat2: Nat): Nat = (nat1, nat2) match
  case (Zero, Zero)       => Zero
  case (Zero, Succ(n))    => Succ(n)
  case (Succ(n), Zero)    => Succ(n)
  case (Succ(n), Succ(m)) => Succ(Succ(add(n, m)))

// NATURAL NATURAL -> NATURAL
// produce a - b
// if b is >= a then produce Zero
sub(nat2, nat0) == nat2
sub(nat0, nat2) == nat0
sub(nat5, nat3) == nat2
sub(nat3, nat5) == nat0

// def sub(nat1: Nat, nat2: Nat): Nat = Zero // stub
def sub(nat1: Nat, nat2: Nat): Nat = (nat1, nat2) match
  case (Zero, Zero)       => Zero
  case (Zero, Succ(n))    => Zero
  case (Succ(n), Zero)    => Succ(n)
  case (Succ(n), Succ(m)) => sub(n, m)

// Natural -> Natural
// produce n*n-1...1
fact(nat0) == nat1
fact(nat1) == nat1
fact(nat2) == nat2
fact(nat3) == nat6

// def fact(nat: Nat): Nat = Zero // stub
def fact(nat: Nat): Nat = nat match
  case Zero    => Succ(Zero)
  case Succ(n) => mult(Succ(n), fact(n))

// NATURAL NATURAL -> NATURAL
// produce a * b
mult(nat0, nat2) == nat0
mult(nat3, nat0) == nat0
mult(nat1, nat2) == nat2
mult(nat3, nat2) == nat6

// def mult(nat1: Nat, nat2: Nat): Nat = Zero // stub
def mult(nat1: Nat, nat2: Nat): Nat = (nat1, nat2) match
  case (Zero, Zero)    => Zero
  case (Zero, Succ(n)) => Zero
  case (Succ(n), Zero) => Zero
  case (Succ(n), Succ(m)) =>
    val product = mult(n, m)
    val addN = add(product, n)
    val addM = add(addN, m)
    Succ(addM)
