@main
def pset2Problem3 =
  // Test case 1
  // val balance = 320000.0
  // val annualInterestRate = 0.2
  // Lowest Payment: 29157.09

  // Test case 2
  val balance = 999999.0
  val annualInterestRate = 0.18
  // Lowest Payment: 90325.03

  var left = balance / 12
  var right = balance * math.pow(1 + annualInterestRate / 12.0, 12) / 12.0
  var monthlyPayment = (left + right) / 2
  val epsilon = 0.01
  var attempt = balance

  while math.abs(attempt) >= epsilon do
    attempt = balance
    var month = 1
    while month <= 12 do
      val unpaidBalance = attempt - monthlyPayment
      attempt = unpaidBalance + (annualInterestRate / 12.0) * unpaidBalance
      month += 1

    if attempt > 0 then left = monthlyPayment
    else right = monthlyPayment
    monthlyPayment = (left + right) / 2

  println(s"Lowest payment: ${round(monthlyPayment, 2)}") // 90325.03
