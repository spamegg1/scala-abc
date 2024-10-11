def printMove(fr: String, to: String) = println(s"move from $fr to $to")

def towers(n: Int, fr: String, to: String, spare: String): Unit =
  if n == 1 then printMove(fr, to)
  else
    towers(n - 1, fr, spare, to)
    towers(1, fr, to, spare)
    towers(n - 1, spare, to, fr)

towers(4, "P1", "P2", "P3")
// move from P1 to P3
// move from P1 to P2
// move from P3 to P2
// move from P1 to P3
// move from P2 to P1
// move from P2 to P3
// move from P1 to P3
// move from P1 to P2
// move from P3 to P2
// move from P3 to P1
// move from P2 to P1
// move from P3 to P2
// move from P1 to P3
// move from P1 to P2
// move from P3 to P2
