// Consider the following data definition for a binary search tree:
// Data definitions:
// A BST (Binary Search Tree) is one of:
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
// PROBLEM:
// Complete the design of the lookup-key function below. Note that because this
// is a search function it will sometimes 'fail'. This happens if it is called
// with an key that does not exist in the BST it is provided. If this happens
// the function should produce false. The signature for such a function is
// written in a special way as shown below.
// BST Natural -> String or false
// Try to find node with given key, if found produce value
// if not found produce false.
lookupKey(bst0, 99) == None
lookupKey(bst1, 1) == Some("abc")
lookupKey(bst1, 0) == None
lookupKey(bst1, 99) == None
lookupKey(bst4, 1) == None
lookupKey(bst4, 4) == Some("dcj")
lookupKey(bst4, 7) == Some("ruf")

// def lookupKey(bst: BST, searchKey: Int): Option[String] = None // stub
def lookupKey(bst: BST, searchKey: Int): Option[String] = bst match
  case Leaf => None
  case Node(key, value, left, right) =>
    if searchKey == key
    then Some(value)
    else if searchKey < key
    then lookupKey(left, searchKey)
    else lookupKey(right, searchKey)
