/* Given the following function definition: */
def spam(s: String): String =
  if s.substring(0, 1) == "a" then s + "a" else s

/** Write out the step-by-step evaluation of the expression:
  * spam("abcde".substring(0, 3)) Be sure to show every intermediate evaluation
  * step.
  */

spam("abcde".substring(0, 3))
spam("abc")
if "abc".substring(0, 1) == "a" then "abc" + "a" else "abc"
if "a" == "a" then "abc" + "a" else "abc"
if true then "abc" + "a" else "abc"
// "abc" + "a"
// "abca"
