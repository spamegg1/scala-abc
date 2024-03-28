import BigDecimal.RoundingMode.HALF_EVEN

def round(double: Double, places: Int): Double =
  BigDecimal(double).setScale(places, HALF_EVEN).toDouble

// Test Case 1:
// var balance = 42.0
// val annualInterestRate = 0.2
// val monthlyPaymentRate = 0.04
// Result Your Code Should Generate Below:
// Remaining balance: 31.38

// Test Case 2:
var balance = 484.0
val annualInterestRate = 0.2
val monthlyPaymentRate = 0.04
// Result Your Code Should Generate Below:
// Remaining balance: 361.61

// balance is: float
// annualInterestRate is: float
// monthlyPaymentRate is: float
// returns: float, rounded to 2 decimal places

// Returns the credit card balance after one year if a person only pays the
// minimum monthly payment required by the credit card company each month.

val monthlyInterestRate = annualInterestRate / 12.0

// this variable will iterate through 12 months of the year
var month = 1

while
  month <= 12
do
  val minimumMonthlyPayment = balance * monthlyPaymentRate
  val unpaidBalance = balance - minimumMonthlyPayment

  // The bank charges interest each month on unpaid balance
  balance = unpaidBalance + monthlyInterestRate * unpaidBalance
  month += 1

// Don't forget to round answer to two decimal places
balance = round(balance, 2)
print(s"Remaining balance: ${balance}")
