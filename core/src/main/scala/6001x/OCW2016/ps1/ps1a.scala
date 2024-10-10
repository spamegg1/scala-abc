def monthsA(
    salary: Int,
    save: Double,
    total: Int,
    down: Double,
    rate: Double
): Int =
  // Calculates how many months it takes to save for a down payment.
  // salary: Int: your annual salary.
  // save: Double: the percentage of your salary that you save each month.
  //     For example save = 0.15
  // total: Int: the total cost of your dream home.
  //     total * down would give the cost of the down payment.
  // down: Double : percentage of the total cost that is required as down payment.
  //     For example down = 0.15
  // rate: Double : annual Interest rate that you receive on your investments.
  //     For example rate = 0.04
  var month: Int = 0
  var currentSavings: Double = 0.0

  while currentSavings < total * down do
    val investReturn = currentSavings * rate / 12
    val monthlySave = salary / 12 * save
    currentSavings += monthlySave + investReturn
    month += 1

  month

def testA =
  assert(monthsA(120000, 0.1, 1000000, 0.25, 0.04) == 183)
  assert(monthsA(80000, 0.15, 500000, 0.25, 0.04) == 105)
  println("Tests pass.")

@main
def ps1a =
  testA

  val portionDownPay = 0.25
  val annualRate = 0.04
  val annualSalary = readLine("Enter your annual salary: ").toInt

  val portionSaved = readLine(
    "Enter the percent of your salary to save, as a decimal: "
  ).toDouble

  val totalCost: Int = readLine("Enter the cost of your dream home: ").toInt

  val result = monthsA(
    annualSalary,
    portionSaved,
    totalCost,
    portionDownPay,
    annualRate
  )

  println(s"Number of months: $result")
