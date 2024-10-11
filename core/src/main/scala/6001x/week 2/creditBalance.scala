package curriculum

def creditBalance1(
    balance: Double,
    annualInterestRate: Double,
    monthlyPaymentRate: Double
) =
  // balance is: float
  // annualInterestRate is: float
  // monthlyPaymentRate is: float
  // returns: float, rounded to 2 decimal places

  // Returns the credit card balance after one year if a person only pays the
  // minimum monthly payment required by the credit card company each month.

  // this variable will iterate through 12 months of the year
  var month = 0
  var newBalance = balance

  while month < 12 do
    val monthlyPayment = newBalance * monthlyPaymentRate
    val unpaidBalance = newBalance - monthlyPayment

    // The bank charges interest each month on unpaid balance
    newBalance = unpaidBalance + (annualInterestRate / 12.0) * unpaidBalance
    month += 1

  // Don't forget to round answer to two decimal places
  newBalance.round(2)

@main
def testCreditBalance1 =
  // Tests the creditBalance function.
  assert(creditBalance1(42, 0.2, 0.04) == 31.38)
  assert(creditBalance1(484, 0.2, 0.04) == 361.61)
  println("Tests passed!")
