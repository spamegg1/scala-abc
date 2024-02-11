// PROBLEM:
// Design a function that consumes an Integer, String and BST, and adds a node
// that has the given key and value to the tree. The node should be inserted in
// the proper place in the tree. The function can assume there is not already
// an entry for that number in the tree. The function should produce the new BST.
// Do not worry about keeping the tree balanced. We will come back to this later.

// Data definitions:
enum BST:
  case Leaf
  case Node(key: Int, value: String, left: BST, right: BST)
import BST.*
// A BST (Binary Search Tree) is one of:
//  - false
//  - (make-node Integer String BST BST)
// interp. false means no BST, or empty BST
//         key is the node key
//         val is the node val
//         l and r are left and right subtrees
// INVARIANT: for a given node:
//     key is > all keys in its l(eft)  child
//     key is < all keys in its r(ight) child
//     the same key never appears twice in the tree
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

// Template rules used:
//  - one of: 2 cases
//  - atomic-distinct: false
//  - compound: (make-node Integer String BST BST)
//  - self reference: (node-l t) has type BST
//  - self reference: (node-r t) has type BST

// Functions:
// Integer String BST -> BST
// produce new BST in which key, val pair have been inserted in proper place
val temp2 = Node(2, "a", Leaf, Leaf)
val temp3 = Node(3, "c", Leaf, Leaf)
val temp19 = Node(19, "z", Leaf, Leaf)
val temp14 = Node(14, "olp", Leaf, temp19)
val temp27 = Node(27, "wit", temp14, Leaf)
val temp42 = Node(42, "ily", temp27, Leaf)

insert(1, "b", Leaf) == Node(1, "b", Leaf, Leaf)
insert(2, "a", bst1) == Node(1, "abc", Leaf, temp2)
insert(3, "c", bst4) == Node(4, "dcj", temp3, bst7)
insert(19, "z", bst10) == Node(10, "why", bst3, temp42)

//<template from BST with 2 additional atomic parameters>
// def insert(insertKey: Int, insertValue: String, bst: BST): BST = bst // stub
def insert(insertKey: Int, insertValue: String, bst: BST): BST = bst match
  case Leaf => Node(insertKey, insertValue, Leaf, Leaf)
  case Node(key, value, left, right) =>
    insertKey compare key match
      case -1 => Node(key, value, insert(insertKey, insertValue, left), right)
      case 0  => bst
      case 1  => Node(key, value, left, insert(insertKey, insertValue, right))
