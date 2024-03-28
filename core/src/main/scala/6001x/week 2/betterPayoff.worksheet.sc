import math.*
import BigDecimal.RoundingMode.HALF_EVEN

def round(x: Double, n: Int): Double =
  BigDecimal(x).setScale(n, HALF_EVEN).toDouble

def creditBalance(
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

  while
    month <= 12
  do
    val unpaidBalance = result - monthlyPayment

    // The bank charges interest each month on unpaid balance
    result = unpaidBalance + (annualInterestRate / 12.0) * unpaidBalance
    month += 1

  result


def minMonthlyPayoff(balance: Double, annualInterestRate: Double) =
  // balance is: float
  // annualInterestRate is: float
  // returns: positive integer
  // Returns the minimum fixed monthly payment that is required
  // to pay off all balance.
  // Bisection search to find the minimum fixed monthly payment
  // that pays off balance
  var left = balance / 12
  var right = balance * pow(1 + annualInterestRate / 12.0, 12) / 12.0
  var result = (left + right) / 2
  val epsilon = 0.01

  while
    abs(creditBalance(balance, annualInterestRate, result)) >= epsilon
  do
    val attempt = creditBalance(balance, annualInterestRate, result)
    if attempt > 0 then
      left = result
    else
      right = result
    result = (left + right) / 2

  // println(s"Lowest payment: ${result}" )
  round(result, 2)


def testMinMonthlyPayoff =
  // Tests the minMonthlyPayoff function.
  assert(minMonthlyPayoff(320000, 0.2) == 29157.09)
  assert(minMonthlyPayoff(290021, 0.2) == 26425.53)
  assert(minMonthlyPayoff(333421, 0.2) == 30379.96)
  assert(minMonthlyPayoff(329256, 0.21) == 30130.98)
  assert(minMonthlyPayoff(103997, 0.21) == 9517.01)
  assert(minMonthlyPayoff(298709, 0.22) == 27454.16)
  assert(minMonthlyPayoff(334429, 0.22) == 30737.16)
  assert(minMonthlyPayoff(91446, 0.18) == 8259.87)
  assert(minMonthlyPayoff(431144, 0.22) == 39626.18)
  assert(minMonthlyPayoff(999999, 0.18) == 90325.03)
  assert(minMonthlyPayoff(334839, 0.18) == 30244.37)
  println("Tests pass!")

testMinMonthlyPayoff