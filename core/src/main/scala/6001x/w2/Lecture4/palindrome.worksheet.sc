// Returns true if given string is a palindrome
def isPalindrome(string: String) =
  def toChars(s: String) =
    val ss = s.toLowerCase()
    var ans = ""
    for char <- ss do if char.isLetter then ans += char
    ans

  def isPal(s: String): Boolean =
    s.length <= 1 || (s.head == s.last && isPal(s.tail.init))

  isPal(toChars(string))

isPalindrome("eve")
isPalindrome("Able was I, ere I saw Elba")
