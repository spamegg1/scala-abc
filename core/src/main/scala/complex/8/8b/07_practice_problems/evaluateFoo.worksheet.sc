// PROBLEM:
// Hand step the evaluation of (foo 3) given the definition of foo below.
// We know that you can use the stepper to check your work - please go
// ahead and do that AFTER you try hand stepping it yourself.
def spam(n: Int): Int =
  val x = 3 * n
  if x % 2 == 0
  then n
  else n + spam(n - 1)

//  EVALUATE: (foo 3)
{
  val x = 3 * 3
  if x % 2 == 0
  then 3
  else 3 + spam(3 - 1)
}

{
  val x = 9
  if x % 2 == 0
  then 3
  else 3 + spam(3 - 1)
}

if 9 % 2 == 0
then 3
else 3 + spam(3 - 1)

if 1 == 0
then 3
else 3 + spam(3 - 1)

if false
then 3
else 3 + spam(3 - 1)

3 + spam(3 - 1)

3 + spam(2)

3 + {
  val x = 3 * 2
  if x % 2 == 0
  then 2
  else 2 + spam(2 - 1)
}

3 + {
  val x = 6
  if x % 2 == 0
  then 2
  else 2 + spam(2 - 1)
}

3 + {
  if 6 % 2 == 0
  then 2
  else 2 + spam(2 - 1)
}

3 + {
  if 0 == 0
  then 2
  else 2 + spam(2 - 1)
}

3 + {
  if true
  then 2
  else 2 + spam(2 - 1)
}

// 3 + 2

// 5
