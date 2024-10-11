@main
def pset2Problem2 =
  // balance is: float
  // annualInterestRate is: float
  // returns: integer, rounded up to the closest multiple of 10

  // Calculates the minimum fixed monthly payment needed in
  // order pay off a credit card balance within 12 months.

  // Test Case 1:
  // var balance = 3329.0
  // val annualInterestRate = 0.2
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 310

  // Test Case 2:
  var balance = 4773.0
  val annualInterestRate = 0.2
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 440

  // Test Case 3:
  // var balance = 3926.0
  // val annualInterestRate = 0.2
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 360

  // Bisection search to find the minimum
  // monthly payment that pays off balance
  val monthlyInterestRate = annualInterestRate / 12.0
  var left = 0.0
  var right = balance
  var monthlyPayment = (left + right) / 2
  val epsilon = 1
  var attempt = balance

  boundary:
    while math.abs(attempt) >= epsilon do
      attempt = balance
      var month = 1
      while month <= 12 do
        val unpaidBalance = attempt - monthlyPayment
        attempt = unpaidBalance + monthlyInterestRate * unpaidBalance
        month += 1

      attempt = attempt.round(2)

      if attempt == 0 then break()
      if attempt > 0 then left = monthlyPayment
      else right = monthlyPayment

      monthlyPayment = (left + right) / 2

  // This line of code rounds the result to a multiple of 10
  monthlyPayment = math.ceil(math.ceil(monthlyPayment) / 10) * 10
  val result = monthlyPayment.toInt

  println(s"Lowest payment: ${result}") // 440 (test case 2)
