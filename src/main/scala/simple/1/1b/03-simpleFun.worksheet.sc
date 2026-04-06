/**
  * DESIGN a function called yell that consumes strings like "hello"
  * and adds "!" to produce strings like "hello!".
  * Remember, when we say DESIGN, we mean follow the recipe.
  * Leave behind commented out versions of the stub and template.
  */

// tests: write these first!
yell("") == "!"
yell("hello") == "hello!"
yell("bye") == "bye!"

// Template
// def yell(s: String): String = s.???(???)

// def yell(s: String): String = "" // stub

/**
  * add exclamation mark to end of string
  * @param s the string to add exclamation to
  * @return the result of adding ! at the end of s
  */
def yell(s: String): String = s + "!"
