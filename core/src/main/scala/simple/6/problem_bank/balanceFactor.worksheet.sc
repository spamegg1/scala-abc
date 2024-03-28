// PROBLEM:
// As discussed in lecture for optimal lookup time we want a BST to be balanced.
// The oldest approach to this is called AVL self-balancing trees and was
// invented in 1962. The remainder of this problem set is based on AVL trees.
//
// An individual node is balanced when the height of its left and right branches
// differ by no more than 1. A tree is balanced when all its nodes are balanced.
//
// a) Design the function balance-factor that consumes a node and produces its
// balance factor, which is defined as the height of its left child minus the
// height of its right child.
// b) Use your function in part a) to design the function balanced?, which
// consumes a BST and produces true if the tree is balanced.
//
// Once you have the function, use it to compare what happens with the following
// two sequences of insertions:
// (insert 4 "a"
//         (insert 5 "a"
//                 (insert 6 "a"
//                         (insert 7 "a"
//                                 (insert 8 "a" false)))))
// (insert 4 "a"
//         (insert 5 "a"
//                 (insert 8 "a"
//                         (insert 7 "a"
//                                 (insert 6 "a" false)))))
// Data definitions:
enum BST:
  case Leaf
  case Node(key: Int, value: String, left: BST, right: BST)
import BST.*

val bst0 = Leaf
val bst1 = Node(1, "abc", Leaf, Leaf)
val bst7 = Node(7, "ruf", Leaf, Leaf)
val bst4 = Node(4, "dcj", Leaf, bst7)
val bst3 = Node(3, "ilk", bst1, bst4)
val bst14 = Node(14, "olp", Leaf, Leaf)
val bst27 = Node(27, "wit", bst14, Leaf)
val bst42 = Node(42, "ily", bst27, Leaf)
val bst10 = Node(10, "why", bst3, bst42)

def fnForBst(bst: BST) = bst match
  case Leaf                          => ???
  case Node(key, value, left, right) => ???
  // key, value, fnForBst(left), fnForBst(right)

// Functions:
// BST -> Natural
// consumes a BST and produces its balance factor
balanceFactor(bst1) == 0
balanceFactor(bst3) == -1
balanceFactor(bst42) == 2
balanceFactor(bst10) == 0
balanceFactor(Node(42, "ily", Leaf, bst27)) == -2

def height(bst: BST): Int = bst match
  case Leaf => -1
  case Node(key, value, left, right) =>
    1 + math.max(height(left), height(right))

def balanceFactor(bst: BST): Int = bst match
  case Leaf                          => 0
  case Node(key, value, left, right) => height(left) - height(right)

// BST -> Boolean
// produce true if a BST is balanced
balanced(bst0)
balanced(bst1)
balanced(bst3)
!balanced(bst42)
balanced(bst27)
!balanced(Node(42, "ily", bst27, Leaf))
!balanced(bst10)

// def balanced(bst: BST): Boolean = false // stub

//<template from BST>
def balanced(bst: BST): Boolean = bst match
  case Leaf => true
  case Node(key, value, left, right) =>
    -1 <= balanceFactor(bst) &&
    balanceFactor(bst) <= 1 &&
    balanced(left) && balanced(right)
