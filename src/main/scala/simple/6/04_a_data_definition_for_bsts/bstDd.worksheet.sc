// import scalax.collection.Graph
// import scalax.collection.GraphPredef.*
// import scalax.collection.GraphEdge.*

// PROBLEM:
// Design a data definition to represent binary search trees.
// a BST (Binary Search Tree) is one of:
//  - false
//  - (make-node Integer String BST BST)
// interp. false means no BST, or empty BST
//         key is the node key
//         val is the node val
//         l and r are left and right subtrees
enum BST:
  case Leaf
  case Node(key: Int, value: String, left: BST, right: BST)
import BST.*
// INVARIANT: for a given node:
//     key is > all keys in its l(eft)  child
//     key is < all keys in its r(ight) child
//     the same key never appears twice in the tree
val bst0 = Leaf
val bst1 = Node(1, "abc", Leaf, Leaf)
val bst7 = Node(7, "ruf", Leaf, Leaf)
val bst4 = Node(4, "dcj", bst1, bst7)

def fnForBst(bst: BST) = bst match
  case Leaf                          => ???
  case Node(key, value, left, right) => ???
  // key, value, fnForBst(left), fnForBst(right)

// Template rules used:
//  - one of: 2 cases
//  - atomic-distinct: false
//  - compound: (make-node Integer String BST BST)
//  - self reference: (node-l t) has type BST
//  - self reference: (node-r t) has type BST
