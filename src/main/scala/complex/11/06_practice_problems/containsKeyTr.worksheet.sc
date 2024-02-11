// Problem:
// Starting with the following data definition for a binary tree (not a binary
// search tree) design a tail-recursive function called contains? that consumes
// a key and a binary tree and produces true if the tree contains the key.

enum BinaryTree:
  case Leaf
  case Node(key: Int, value: String, left: BinaryTree, right: BinaryTree)
import BinaryTree.*
// Interp. A binary tree, each node has a key, value and 2 children

val leaf = Leaf
val node4 = Node(4, "d", leaf, leaf)
val node6 = Node(6, "f", leaf, leaf)
val node7 = Node(7, "g", leaf, leaf)
val node1 = Node(1, "a", node6, node7)

// Integer BT -> Boolean
// Produce true if the tree contains the given key
!containsNoTr(1, leaf)
containsNoTr(1, node1)
!containsNoTr(3, node1)
containsNoTr(7, node1)

// This is the non-tail-recursive version. The lack of tail-recursion is coming
// from the backtracking to visit unvisited right branches of the tree. An
// accumulator can be used to keep a list of those branches to visit.
def containsNoTr(searchKey: Int, bt: BinaryTree): Boolean = bt match
  case Leaf => false
  case Node(key, value, left, right) =>
    searchKey == key ||
    containsNoTr(searchKey, left) ||
    containsNoTr(searchKey, right)

!containsTr(1, leaf)
containsTr(1, node1)
!containsTr(3, node1)
containsTr(7, node1)
// Here we have added the accumulator except for the problem of what to do when
// bt is false.
// def containsAcc1(searchKey: Int, bt: BinaryTree): Boolean =
//   def helper(bin: BinaryTree, toDo: List[BinaryTree]): Boolean = bt match
//     case Leaf => false
//     case Node(key, value, left, right) =>
//       searchKey == key || helper(left, right :: toDo)
//   helper(bt, Nil)

// Since todo is a list, and so has arbitrary size, we need to introduce a new
// helper function. In doing so we rename the original inner function.
def containsTr(searchKey: Int, bt: BinaryTree): Boolean =
  def helperOne(bin: BinaryTree, toDo: List[BinaryTree]): Boolean = bin match
    case Leaf => helperList(toDo)
    case Node(key, value, left, right) =>
      searchKey == key || helperOne(left, right :: toDo)

  def helperList(toDo: List[BinaryTree]): Boolean = toDo match
    case head :: next => helperOne(head, next)
    case Nil          => false

  helperOne(bt, Nil)

// Interesting solution: using binary tree as accumulator
!containsTr2(1, leaf)
containsTr2(1, node1)
!containsTr2(3, node1)
containsTr2(7, node1)

def containsTr2(searchKey: Int, bt: BinaryTree): Boolean =
  def helper(bin: BinaryTree, toDo: BinaryTree): Boolean = bin match
    case Leaf =>
      toDo match
        case Leaf => false
        case _    => helper(toDo, Leaf)
    case Node(key, value, left, right) =>
      searchKey == key match
        case true => true
        case false =>
          (left, right) match
            case (Leaf, _) => helper(right, toDo)
            case (_, Leaf) => helper(left, toDo)
            case _         => helper(left, Node(0, "", right, toDo))

  helper(bt, Leaf)
