enum Account:
  case Leaf
  case Node(
      id: Int,
      name: String,
      balance: Double,
      left: Account,
      right: Account
  )
import Account.*
// interp. a collection of bank accounts
//   false represents an empty collection of accounts.
//   (make-node id name bal l r) is a non-empty collection of accounts such that:
//    - id is an account identification number (and BST key)
//    - name is the account holder's name
//    - bal is the account balance in dollars CAD
//    - l and r are further collections of accounts
// INVARIANT: for a given node:
//     id is > all ids in its l(eft)  child
//     id is < all ids in its r(ight) child
//     the same id never appears twice in the collection
val account0 = Leaf
val account1 = Node(1, "Mr. Rogers", 22.0, Leaf, Leaf)
val account7 = Node(7, "Mr. Natural", 13.0, Leaf, Leaf)
val account4 = Node(4, "Mrs. Doubtfire", -3.0, Leaf, account7)
val account3 = Node(3, "Miss Marple", 600.0, account1, account4)
val account14 = Node(14, "Mr. Impossible", -9.0, Leaf, Leaf)
val account27 = Node(27, "Mr. Selatcia", 40.0, account14, Leaf)
val account50 = Node(50, "Miss 604", 16.0, Leaf, Leaf)
val account42 = Node(42, "Mr. Mom", -79.0, account27, account50)
val account10 = Node(10, "Dr. No", 84.0, account3, account42)

def fnForAccount(account: Account) = account match
  case Leaf                                 => ???
  case Node(id, name, balance, left, right) => ???
  // fnForAccount(left), fnForAccount(right)

// PROBLEM 1:
// Design an abstract function (including signature, purpose, and tests)
// to simplify the remove-debtors and remove-profs functions defined below.
// Now re-define the original remove-debtors and remove-profs functions
// to use your abstract function. Remember, the signature and tests should
// not change from the original functions.
def join(act1: Account, act2: Account): Account = (act1, act2) match
  case (_, Leaf) => act1
  case (Leaf, _) => act2
  case (Node(id1, _, _, _, _), Node(id2, n2, b2, l2, r2)) =>
    id1 compare id2 match
      case -1 => Node(id2, n2, b2, join(act1, l2), r2)
      case 1  => Node(id2, n2, b2, l2, join(act1, r2))
      case 0  => act2

// (Accounts -> Boolean) Accounts -> Accounts
// remove accounts that satisfy pred predicate
val isDebtor = (a: Account) =>
  a match
    case Leaf                      => false
    case Node(_, _, balance, _, _) => balance < 0

val isProf = (a: Account) =>
  a match
    case Leaf                   => false
    case Node(_, name, _, _, _) => name.startsWith("Prof.")

removeAccounts(isDebtor)(account4) == account7
removeAccounts(isProf)(Node(0, "Prof. Longhair", 0, Leaf, Leaf)) == Leaf

// <template from Accounts>
def removeAccounts(pred: Account => Boolean)(account: Account): Account =
  account match
    case Leaf => Leaf
    case Node(id, name, balance, left, right) =>
      if pred(account)
      then join(removeAccounts(pred)(left), removeAccounts(pred)(right))
      else
        Node(
          id,
          name,
          balance,
          removeAccounts(pred)(left),
          removeAccounts(pred)(right)
        )

// Accounts -> Accounts
// remove all accounts with a negative balance
// Remove all professors' accounts.
// <template as call to abstract fold function: remove-acts>
def removeDebtors = removeAccounts(isDebtor)
def removeProfs = removeAccounts(isProf)

// PROBLEM 2:
// Using your new abstract function, design a function that removes from a given
// BST any account where the name of the account holder has an odd number of
// characters.  Call it remove-odd-characters.
// Accounts -> Accounts
// Remove accounts where the holder's name has an odd number of characters
val daisy = Node(7, "Miss Daisy", 12, Leaf, Leaf)
val robinson = Node(7, "Mrs. Robinson", 12, Leaf, Leaf)
removeOddLengths(daisy) == daisy
removeOddLengths(robinson) == Leaf

// <template as call to abstract fold function: remove-acts>
def oddLength = (a: Account) =>
  a match
    case Leaf                   => false
    case Node(_, name, _, _, _) => name.length % 2 == 1

def removeOddLengths = removeAccounts(oddLength)

// Problem 3:
// Design an abstract fold function for Accounts called fold-act.
// Use fold-act to design a function called charge-fee that decrements the
// balance of every account in a given collection by the monthly fee of 3 CAD.
// (Natural String Integer Z Z -> Z) Z Accounts -> Z
// the fold function for Accounts.
// <template from Accounts>
def foldForAccount[T](function: (Int, String, Double, T, T) => T)(base: T)(
    account: Account
): T =
  account match
    case Leaf => base
    case Node(id, name, balance, left, right) =>
      function(
        id,
        name,
        balance,
        foldForAccount(function)(base)(left),
        foldForAccount(function)(base)(right)
      )

// Accounts -> Accounts
// Deduct 3 CAD monthly fee from every account.
chargeFee(account27) == Node(
  27,
  "Mr. Selatcia",
  37.0,
  Node(
    14,
    "Mr. Impossible",
    -12.0,
    Leaf,
    Leaf
  ),
  Leaf
)

// <template as call to fold-act>
def chargeFee = foldForAccount[Account]((id, name, balance, left, right) =>
  Node(id, name, balance - 3.0, left, right)
)(Leaf)

// PROBLEM 4:
// Suppose you needed to design a function to look up an account based on its ID.
// Would it be better to design the function using fold-act, or to design the
// function using the fn-for-acts template?  Briefly justify your answer.
// It is better to design the search function using fn-for-acts template, since
// using fold-act would make it traverse the whole tree every time, even
// if it finds what it is looking for right away.
