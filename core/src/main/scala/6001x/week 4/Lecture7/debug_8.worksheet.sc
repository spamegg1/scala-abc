import scala.io.StdIn.readLine

def isPalindrome[T](x: List[T]): Boolean =
  x == x.reverse

def silly(n: Int) =
  var result = List[String]()
  for _ <- 0 until n
  do
    val elem = readLine("Enter element: ")
    result = elem :: result

  println(if isPalindrome(result) then "Yes" else "No")
