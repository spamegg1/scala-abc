def creditBalance(
    balance: Double,
    annualInterestRate: Double,
    monthlyPayment: Double
) =
  // balance is: float
  // annualInterestRate is: float
  // monthlyPayment is: float
  // returns: float, rounded to 2 decimal places
  // Returns the credit card balance after one year if a person only pays the
  // minimum monthly payment required by the credit card company each month.

  // this variable will iterate through 12 months of the year
  var month = 0
  var newBalance = balance

  while month < 12 do
    val unpaidBalance = newBalance - monthlyPayment
    // The bank charges interest each month on unpaid balance
    newBalance = unpaidBalance + (annualInterestRate / 12.0) * unpaidBalance
    month += 1

  // Don't forget to round answer to two decimal places
  newBalance.round(2)
end creditBalance

@main
def testCreditBalance =
  // Test Case 1:
  // val balance = 3329.0
  // val annualInterestRate = 0.2
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 310

  // Test Case 2:
  val balance = 4773.0
  val annualInterestRate = 0.2
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 440

  // Test Case 3:
  // val  balance = 3926.0
  // val annualInterestRate = 0.2
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 360

  // Test Case 4:
  // val balance = 794.0
  // val annualInterestRate = 0.25
  // Result Your Code Should Generate:
  // -------------------
  // Lowest Payment: 80

  // Bisection search to find the minimum
  // monthly payment that pays off balance
  var left = 0.0
  var right = balance
  var result = (left + right) / 2
  val epsilon = 10
  var attempt = balance

  boundary:
    while math.abs(attempt) >= epsilon do
      attempt = creditBalance(balance, annualInterestRate, result)
      if attempt == 0 then break()
      if attempt > 0 then left = result
      else right = result
      result = (left + right) / 2

  // This line of code rounds the result to a multiple of 10
  val resultInt = (result.toInt + 9) / 10 * 10
  println(s"Lowest payment: ${resultInt}") // 440

def minMonthlyPayoff(balance: Double, annualInterestRate: Double) =
  // balance is: float
  // annualInterestRate is: float
  // returns: positive integer
  // Returns the minimum fixed monthly payment that is required
  // to pay off all balance.
  // Bisection search to find the minimum fixed monthly payment
  // that pays off balance
  var left = 0.0
  var right = balance
  var result = (left + right) / 2
  val epsilon = 1

  boundary:
    while math.abs(creditBalance(balance, annualInterestRate, result)) >= epsilon do
      val attempt = creditBalance(balance, annualInterestRate, result)
      if attempt == 0 then break()
      if attempt > 0 then left = result
      else right = result
      result = (left + right) / 2

  // This line of code rounds the result to a multiple of 10
  math.ceil(math.ceil(result) / 10) * 10
end minMonthlyPayoff

@main
def testMinMonthlyPayoff =
  // Tests the minMonthlyPayoff function.
  assert(minMonthlyPayoff(3329, 0.2) == 310)
  assert(minMonthlyPayoff(4773, 0.2) == 440)
  assert(minMonthlyPayoff(3926, 0.2) == 360)
  assert(minMonthlyPayoff(610, 0.2) == 60)
  assert(minMonthlyPayoff(824, 0.2) == 80)
  assert(minMonthlyPayoff(263, 0.25) == 30)
  assert(minMonthlyPayoff(794, 0.25) == 80)
  assert(minMonthlyPayoff(909, 0.25) == 90)
  assert(minMonthlyPayoff(230, 0.18) == 30)
  assert(minMonthlyPayoff(307, 0.25) == 30)
  assert(minMonthlyPayoff(751, 0.2) == 70)
  assert(minMonthlyPayoff(4822, 0.18) == 440)
  assert(minMonthlyPayoff(3031, 0.2) == 280)
  assert(minMonthlyPayoff(3248, 0.2) == 300)
  assert(minMonthlyPayoff(3492, 0.2) == 320)
  assert(minMonthlyPayoff(4356, 0.04) == 370)
  assert(minMonthlyPayoff(4080, 0.15) == 370)
  assert(minMonthlyPayoff(4633, 0.15) == 420)
  assert(minMonthlyPayoff(4668, 0.15) == 420)
  assert(minMonthlyPayoff(4146, 0.18) == 380)
  assert(minMonthlyPayoff(3632, 0.18) == 330)
  assert(minMonthlyPayoff(3765, 0.18) == 350)
  assert(minMonthlyPayoff(3265, 0.18) == 300)
  assert(minMonthlyPayoff(4763, 0.18) == 440)
  assert(minMonthlyPayoff(4781, 0.04) == 410)
  assert(minMonthlyPayoff(4593, 0.18) == 420)
  assert(minMonthlyPayoff(4211, 0.2) == 390)
  assert(minMonthlyPayoff(4142, 0.2) == 380)
  println("Tests pass!")
