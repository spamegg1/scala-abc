// PROBLEM:
// Use the How to Design Functions (HtDF) recipe to design a function that
// consumes a string, and adds "?" to the end unless the string already ends
// in "?". For this question, assume the string has length > 0. Follow the HtDF
// recipe and leave behind commented out versions of the stub and template.

// String -> String
// add ? to end of str unless it already ends in ?
ensureQuestion("Hello") == "Hello?"
ensureQuestion("OK?") == "OK?"

// def ensureQuestion(s: String) = "" // stub

// template
// def ensureQuestion(s: String) =
//   if ??? then
//     s
//   else
//     s + ???

def ensureQuestion(s: String) =
  if s.endsWith("?") then
    s
  else
    s + "?"
