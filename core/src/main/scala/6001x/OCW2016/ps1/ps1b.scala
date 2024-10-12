package curriculum
package mit6001x
package ocw2016

def monthsB(
    salary: Int,
    save: Double,
    total: Int,
    semi: Double,
    down: Double,
    rate: Double
): Int =
  // Calculates how many months it takes to save for a down payment.
  // salary: Int: your annual salary.
  // save: Double: the percentage of your salary that you save each month.
  //     For example save = 0.15
  // total: Int: the total cost of your dream home.
  //     total * down would give the cost of the down payment.
  // semi: Double : your semi-annual salary raise (as a percentage of salary).
  //     For example semi = 0.03
  // down: Double : percentage of the total cost that is required as down payment.
  //     For example down = 0.15
  // rate: Double : annual Interest rate that you receive on your investments.
  //     For example rate = 0.04
  var month = 0
  var currentSavings = 0.0
  var updatedSalary = salary.toDouble

  while currentSavings < total * down do
    val investReturn: Double = currentSavings * rate / 12
    val monthlySave: Double = updatedSalary / 12 * save
    currentSavings += monthlySave + investReturn

    month += 1
    if month % 6 == 0 then updatedSalary += updatedSalary * semi

  month

def testB =
  assert(monthsB(120000, 0.05, 500000, 0.03, 0.25, 0.04) == 142)
  assert(monthsB(80000, 0.1, 800000, 0.03, 0.25, 0.04) == 159)
  assert(monthsB(75000, 0.05, 1500000, 0.05, 0.25, 0.04) == 261)
  println("Tests pass.")

@main
def ps1b =
  testB

  val portionDownPay = 0.25
  val annualRate = 0.04
  val annualSalary = readLine("Enter your annual salary: ").toInt

  val portionSaved = readLine(
    "Enter the percent of your salary to save, as a decimal: "
  ).toDouble

  val totalCost = readLine("Enter the cost of your dream home: ").toInt

  val semiAnnualRaise = readLine(
    "Enter the semi-annual raise, as a decimal: "
  ).toDouble

  val result = monthsB(
    annualSalary,
    portionSaved,
    totalCost,
    semiAnnualRaise,
    portionDownPay,
    annualRate
  )
  println(s"Number of months: $result")
