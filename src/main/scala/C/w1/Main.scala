package C.w1

import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

type Snake = List[(Double, Double)]
type Food = (Double, Double)
type Width = Int
type Height = Int
type Dimensions = (Width, Height)

enum Dir:
  case Up, Down, Left, Right
import Dir.*

case class State(snake: Snake, food: Food)(using d: Dimensions):
  def rectangles: List[Rectangle] =
    val foodSquare = State.square(food._1, food._2, Red)
    val snakeSquares = snake.map((x, y) => State.square(x, y, Green))
    foodSquare :: snakeSquares

  def newState(dir: Dir): State =
    val (x, y) = snake.head

    val (newX, newY) = dir match
      case Up    => (x, y - 25)
      case Down  => (x, y + 25)
      case Left  => (x - 25, y)
      case Right => (x + 25, y)

    val newSnake: Snake =
      if State.check(snake)(newX, newY) then State.initialSnake
      else if food == (newX, newY) then food :: snake
      else (newX, newY) :: snake.init

    val newFood = if food == (newX, newY) then State.randomFood else food

    State(newSnake, newFood)

object State:
  def square(xr: Double, yr: Double, color: Color) = new Rectangle:
    x = xr
    y = yr
    width = 25
    height = 25
    fill = color

  val initialSnake: Snake = List((250, 200), (225, 200), (200, 200))

  def check(snake: Snake)(x: Double, y: Double)(using d: Dimensions): Boolean =
    x < 0 || x >= d._1 || y < 0 || y >= d._1 || snake.tail.contains((x, y))

  def randomFood(using d: Dimensions): Food =
    (Random.nextInt(d._1 / 25) * 25.0, Random.nextInt(d._2 / 25) * 25.0)

object SnakeFx extends JFXApp3:
  import State.*
  given d: Dimensions = (700, 700)

  def gameLoop(update: => Unit): Unit =
    Future { update; Thread.sleep(80) }
      .flatMap(_ => Future(gameLoop(update)))

  override def start(): Unit =
    val state = ObjectProperty(State(initialSnake, randomFood))
    val frame = IntegerProperty(0)
    val direction = ObjectProperty(Right)

    frame.onChange(state.update(state.value.newState(direction.value)))

    stage = new JFXApp3.PrimaryStage:
      width = d._1.toDouble
      height = d._2.toDouble
      scene = new Scene:
        fill = White
        content = state.value.rectangles
        onKeyPressed = key =>
          key.getText match
            case "w" => direction.value = Up
            case "s" => direction.value = Down
            case "a" => direction.value = Left
            case "d" => direction.value = Right

        state.onChange(Platform.runLater { content = state.value.rectangles })

    gameLoop(frame.update(frame.value + 1))
