var calls = 0

def fibCounted(n: Long): Long =
  calls += 1
  if n == 1 then 1
  else if n == 2 then 2
  else fibCounted(n - 1) + fibCounted(n - 2)

def fibEffCounted(n: Long, d: MMap[Long, Long]): Long =
  calls += 1
  if d.contains(n) then d(n)
  else
    val ans = fibEffCounted(n - 1, d) + fibEffCounted(n - 2, d)
    d(n) = ans
    ans

@main
def fibCountTest =
  val ten = fibCounted(10L)
  println(s"fibCounted function calls ${calls}") // 109

  calls = 0

  val d = MMap(1L -> 1L, 2L -> 2L)
  val eff = fibEffCounted(10L, d)
  println(s"fibEffCounted function calls ${calls}") // 17
