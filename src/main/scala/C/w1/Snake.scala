package C.w1
package snake

import scalafx.application.{JFXApp3, Platform}
import scalafx.Includes.*
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color, Color.{Red, Green, White}
import scalafx.scene.shape.Rectangle

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

type Food  = (x: Double, y: Double)
type Snake = List[Food]

object Constants:
  val Width     = 600
  val Height    = 600
  val Size      = 25
  val Start     = 200.0
  val SleepTime = 100

object Food:
  import Constants.Size

  def random: Food =
    (x = Random.nextInt(Size - 1) * Size, y = Random.nextInt(Size - 1) * Size)

  extension (f: Food)
    def next(dir: Int): Food = dir match
      case 1 => (f.x, f.y - Size)
      case 2 => (f.x, f.y + Size)
      case 3 => (f.x - Size, f.y)
      case 4 => (f.x + Size, f.y)
      case _ => (f.x, f.y)

object Snake:
  import Constants.{Start, Size}

  val init: Snake = List((Start + 2 * Size, Start), (Start + Size, Start), (Start, Start))

  extension (s: Snake)
    def check(f: Food) =
      f.x < 0 || f.x >= 600 || f.y < 0 || f.y >= 600 || s.tail.contains(f)

object State:
  val init = State(Snake.init, Food.random)

  def square(xr: Double, yr: Double, color: Color) = new Rectangle:
    x = xr
    y = yr
    width = Constants.Size
    height = Constants.Size
    fill = color
end State

case class State(snake: Snake, food: Food):
  def rectangles: List[Rectangle] =
    State.square(food.x, food.y, Red) ::
      snake.map((x, y) => State.square(x, y, Green))

  def newSnake(f: Food): Snake =
    import Snake.check
    if snake.check(f) then Snake.init
    else if food == f then food :: snake
    else f :: snake.init

  def newFood(f: Food) = if food == f then Food.random else food

  def newState(dir: Int): State =
    import Food.next
    val f = snake.head.next(dir)
    State(newSnake(f), newFood(f))
end State

object SnakeFx extends JFXApp3:
  @annotation.nowarn
  def controls(dir: IntegerProperty)(key: KeyEvent): Unit =
    key.getCode() match
      case KeyCode.Up    => dir.value = 1
      case KeyCode.Down  => dir.value = 2
      case KeyCode.Left  => dir.value = 3
      case KeyCode.Right => dir.value = 4
      case _             => ()

  def gameLoop(update: () => Unit): Unit =
    Future:
      update()
      Thread.sleep(Constants.SleepTime)
    .flatMap(_ => Future(gameLoop(update)))

  override def start(): Unit =
    val state     = ObjectProperty(State.init)
    val frame     = IntegerProperty(0)
    val direction = IntegerProperty(4) // right

    frame.onChange:
      state.update:
        state.value.newState(direction.value)

    stage = new JFXApp3.PrimaryStage:
      width = Constants.Width
      height = Constants.Height
      scene = new Scene:
        fill = White
        content = state.value.rectangles
        onKeyPressed = controls(direction)
        state.onChange:
          Platform.runLater:
            content = state.value.rectangles

    gameLoop(() => frame.update(frame.value + 1))
