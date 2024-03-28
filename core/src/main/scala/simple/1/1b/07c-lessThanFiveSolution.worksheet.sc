// PROBLEM:
// DESIGN function that consumes a string and determines whether its length is
// less than 5.  Follow the HtDF recipe and leave behind commented out versions
// of the stub and template.

// String -> Boolean
// produce true if length of s is less than 5

// Tests
lessThanFive("")
lessThanFive("five")
!lessThanFive("12345")
!lessThanFive("eighty")

// def lessThanFive(string: String): Boolean = true // stub

// def lessThanFive(string: String): Boolean = string.??? // template

def lessThanFive(string: String): Boolean = string.length < 5
