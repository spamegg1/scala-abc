// PROBLEM:
// Design the function height, that consumes a BST and produces its height. Note that
// the height of a BST is one plus the height of its highest child. You will want to
// use the BSL max function in your solution. The height of a false tree is 0. The
// height of (make-node 1 "a" false false) is 1.
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
//  produce max height of BST
height(bst0) == 0
height(bst1) == 1
height(bst3) == 3
height(bst42) == 3
height(bst10) == 4

// <template from BST>
def height(bst: BST): Int = bst match
  case Leaf => 0
  case Node(key, value, left, right) =>
    1 + math.max(height(left), height(right))
