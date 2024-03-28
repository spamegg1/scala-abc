// PROBLEM:
// Design the function count-nodes, which consumes BST and produces a
// natural number which is the total number of nodes in the BST. An
// empty tree (false) has 0 nodes.
//  Data definitions:
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

//  Functions:
//  BST -> Natural
//  count number of nodes in a BST
countNodes(bst0) == 0
countNodes(bst1) == 1
countNodes(bst42) == 3
countNodes(bst10) == 8

// <template from BST>
def countNodes(bst: BST): Int = bst match
  case Leaf                          => 0
  case Node(key, value, left, right) => 1 + countNodes(left) + countNodes(right)
