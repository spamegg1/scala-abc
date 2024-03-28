/*  Given the following function definition: */
def bobble(s: String): String =
  if s.length <= 6 then s + "ible" else s
/*
  Write out the step-by-step evaluation of the expression:
    bobble("fungus".substring(0, 4))
  Be sure to show every intermediate evaluation step (including the original
  expression and the final result, our answer has 7 steps).
 */

bobble("fungus".substring(0, 4))
bobble("fung")
if "fung".length <= 6 then "fung" + "ible" else "fung"
if 4 <= 6 then "fung" + "ible" else "fung"
if true then "fung" + "ible" else "fung"
// "fung" + "ible"
// "fungible"
