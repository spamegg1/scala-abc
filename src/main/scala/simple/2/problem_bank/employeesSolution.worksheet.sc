import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

// PROBLEM A:
// You work in the Human Resources department at a ski lodge.
// Because the lodge is busier at certain times of year,
// the number of employees fluctuates.
// There are always more than 10, but the maximum is 50.
// Design a data definition to represent the number of ski lodge employees.
// Call it Employees.
// Data definitions:
// Employees is Natural(10, 50]
// Interp. range of employees working at a ski lodge at one time
case class Employees(n: Int):
  require(10 < n && n <= 50)

val e1 = Employees(11)
val e2 = Employees(40)
val e3 = Employees(50)

def fnForEmployees(employees: Employees) =
  employees.n // ???

// Template rules used:
//  - atomic non-distinct: Natural(10, 50]

// PROBLEM B:
// Now design a function that will calculate the total payroll for the quarter.
// Each employee is paid $1,500 per quarter. Call it calculate-payroll.
// Employees -> Natural
// calculates the ski lodge's payroll based on $1,500/employee
calculatePayroll(e1) == 16500
calculatePayroll(e2) == 60000
calculatePayroll(e3) == 75000

// def calculatePayroll(employees: Employees): Double = 0.0 // stub

// <template from Employees>
def calculatePayroll(employees: Employees): Double =
  employees.n * 1500