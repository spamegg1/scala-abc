package curriculum

def creditBalance2(
    balance: Double,
    annualInterestRate: Double,
    monthlyPayment: Double
) =
  // balance is: float
  // annualInterestRate is: float
  // monthlyPayment is: float
  // returns: integer
  // Returns the credit card balance after one year if a person only pays the
  // monthly payment each month.

  // this variable will iterate through 12 months of the year
  var month = 1
  var result = balance

  while month <= 12 do
    val unpaidBalance = result - monthlyPayment

    // The bank charges interest each month on unpaid balance
    result = unpaidBalance + (annualInterestRate / 12.0) * unpaidBalance
    month += 1

  result
end creditBalance2

def minMonthlyPayoff2(balance: Double, annualInterestRate: Double) =
  // balance is: float
  // annualInterestRate is: float
  // returns: positive integer
  // Returns the minimum fixed monthly payment that is required
  // to pay off all balance.
  // Bisection search to find the minimum fixed monthly payment
  // that pays off balance
  var left = balance / 12
  var right = balance * math.pow(1 + annualInterestRate / 12.0, 12) / 12.0
  var result = (left + right) / 2
  val epsilon = 0.01

  while math.abs(creditBalance2(balance, annualInterestRate, result)) >= epsilon do
    val attempt = creditBalance2(balance, annualInterestRate, result)
    if attempt > 0 then left = result
    else right = result
    result = (left + right) / 2

  println(s"Lowest payment: ${result}")
  result.round(2)
end minMonthlyPayoff2

@main
def testMinMonthlyPayoff2 =
  // Tests the minMonthlyPayoff function.
  assert(minMonthlyPayoff2(320000, 0.2) == 29157.09)
  assert(minMonthlyPayoff2(290021, 0.2) == 26425.53)
  assert(minMonthlyPayoff2(333421, 0.2) == 30379.96)
  assert(minMonthlyPayoff2(329256, 0.21) == 30130.98)
  assert(minMonthlyPayoff2(103997, 0.21) == 9517.01)
  assert(minMonthlyPayoff2(298709, 0.22) == 27454.16)
  assert(minMonthlyPayoff2(334429, 0.22) == 30737.16)
  assert(minMonthlyPayoff2(91446, 0.18) == 8259.87)
  assert(minMonthlyPayoff2(431144, 0.22) == 39626.18)
  assert(minMonthlyPayoff2(999999, 0.18) == 90325.03)
  assert(minMonthlyPayoff2(334839, 0.18) == 30244.37)
  println("Tests pass!")
