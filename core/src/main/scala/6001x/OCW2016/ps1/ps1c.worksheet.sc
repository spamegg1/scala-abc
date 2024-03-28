def savings(
  start: Double,
  portion: Int,
  investRate: Double,
  semiRaise: Double
): Double =

  var saving = 0.0
  var salary = start

  for
    month <- 0 until 36
  do
    if month > 0 && month % 6 == 0 then
      salary += salary * semiRaise

    val investReturn = saving * investRate / 12
    saving += salary * portion / 120000
    saving += investReturn

  saving

def bisearchOneStep(
  saving: Double,
  downPay: Double,
  left: Int,
  right: Int,
  portion: Int,
  steps: Int
): (Int, Int, Int, Int) =

  var (newLeft, newRight, newSteps) = (left, right, steps + 1)
  if saving < downPay then
    newLeft = portion
  else
    newRight = portion

  val newPortion = (newLeft + newRight) / 2
  (newLeft, newRight, newPortion, newSteps)


def bisearch(
  salary: Double,
  investRate: Double,
  semiRaise: Double,
  downPay: Double,
  left: Int,
  right: Int,
  portion: Int,
  steps: Int
): (Int, Int) =

  val saving = savings(salary, portion, investRate, semiRaise)

  if math.abs(saving - downPay) < 100 then
    (portion, steps)
  else
    val result = bisearchOneStep(saving, downPay, left, right, portion, steps)
    val (newLeft, newRight, newPortion, newSteps) = result

    bisearch(salary, investRate, semiRaise, downPay,
             newLeft, newRight, newPortion, newSteps)

def optimalSavingRate(
  salary: Double,
  investRate: Double,
  semiRaise: Double,
  downPay: Double
): Unit =

  val maxSaving = savings(salary, 10000, investRate, semiRaise)

  if maxSaving < downPay then
    println("It is not possible to pay the down payment in three years.")
  else
    val (left, right, portion, steps) = (0, 10000, 5000, 1)
    val (portionRes, stepsRes) = bisearch(
      salary, investRate, semiRaise, downPay, left, right, portion, steps
    )
    println("Best savings rate: " + (portionRes / 10000).toString)
    println("Steps in bisection search: " + stepsRes.toString)


def tests(investRate: Double, semiRaise: Double, downPay: Double): Unit =
  val (left, right, portion, steps) = (0, 10000, 5000, 1)

  assert(savings(150000, 5000, investRate, semiRaise) == 283387.20615677064)
  assert(savings(150000, 2500, investRate, semiRaise) == 141693.60307838532)
  assert(savings(150000, 3750, investRate, semiRaise) == 212540.40461757814)
  assert(savings(300000, 5000, investRate, semiRaise) == 566774.4123135413)
  assert(savings(300000, 2500, investRate, semiRaise) == 283387.20615677064)
  assert(savings(300000, 1250, investRate, semiRaise) == 141693.60307838532)
  println("Testing savings: passed!")

  assert((4411, 12) == bisearch(
    150000, investRate, semiRaise, downPay, left, right, portion, steps))
  assert((2206,  9) == bisearch(
    300000, investRate, semiRaise, downPay, left, right, portion, steps))
  println("Testing bisearch: passed!")


// Constants given to us by the problem
val SEMIRAISE = 0.07
val INVESTRATE = 0.04
val TOTAL_COST = 1000000
val DOWNPAY = TOTAL_COST * 0.25

tests(INVESTRATE, SEMIRAISE, DOWNPAY)

// SALARY = readLine("Enter the starting salary: ").toDouble
// optimalSavingRate(SALARY, INVESTRATE, SEMIRAISE, DOWNPAY)
