// PROBLEM:
// Consider the following data definition for representing
// an arbitrary number of user accounts.
case class Account(number: Int, name: String):
  require(1 <= number)

def fnForAccount(account: Account) = ??? // account.number, account.name

// Accounts is one of:
//  - empty
//  - (cons (make-account Natural String) Accounts)
// interp. a list of accounts, where each
//           num  is an account number
//           name is the person's first name
val accountList0 = List[Account]()
val accountList1 = List(
  Account(1, "abc"),
  Account(4, "dcj"),
  Account(3, "ilk"),
  Account(7, "ruf")
)

def fnForAccountList(accountList: List[Account]) = accountList match
  case head :: next => ??? // fnForAccount(head), fnForAccountList(next)
  case Nil          => ???

// blended template
def fnForAccountList2(accountList: List[Account]) = accountList match
  case head :: next => ???
  // head.number, head.name, fnForAccountList(next)
  case Nil => ???

// PROBLEM:
// Complete the design of the lookup-name function below. Note that because this
// is a search function it will sometimes 'fail'. This happens if it is called
// with an account number that does not exist in the accounts list it is
// provided. If this happens the function should produce None. The signature
// for such a function is written in a special way as shown below.

// Accounts Natural -> Option[String]
// Try to find account with given number in accounts.
// If found produce name, otherwise produce false.
lookup(accountList0, 1) == None
lookup(accountList1, 99) == None
lookup(accountList1, 3) == Some("ilk")
lookup(accountList1, 1) == Some("abc")

// def lookup(accountList: List[Account], number: Int): Option[String] = None // stub
def lookup(accountList: List[Account], number: Int): Option[String] =
  accountList match
    case head :: next =>
      if head.number == number
      then Some(head.name)
      else lookup(next, number)
    case Nil => None
