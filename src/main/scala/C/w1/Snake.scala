package C.w1
package snake

import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import javafx.scene.input.KeyEvent
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

type Food = (x: Double, y: Double)
type Snake = List[Food]

object Food:
  def random: Food = (x = Random.nextInt(24) * 25, y = Random.nextInt(24) * 25)
  extension (f: Food)
    def next(dir: Int): Food = dir match
      case 1 => (f.x, f.y - 25)
      case 2 => (f.x, f.y + 25)
      case 3 => (f.x - 25, f.y)
      case 4 => (f.x + 25, f.y)
      case _ => (f.x, f.y)

object Snake:
  val init: Snake = List((250.0, 200.0), (225.0, 200.0), (200.0, 200.0))
  extension (s: Snake)
    def check(f: Food) =
      f.x < 0 || f.x >= 600 || f.y < 0 || f.y >= 600 || s.tail.contains((f.x, f.y))

object State:
  val init = State(Snake.init, Food.random)
  def square(xr: Double, yr: Double, color: Color) = new Rectangle:
    x = xr
    y = yr
    width = 25
    height = 25
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
  def controls(dir: IntegerProperty, key: KeyEvent) =
    key.getText match
      case "w" => dir.value = 1
      case "s" => dir.value = 2
      case "a" => dir.value = 3
      case "d" => dir.value = 4

  def gameLoop(update: () => Unit): Unit =
    Future:
      update()
      Thread.sleep(1000 / 25 * 2)
    .flatMap(_ => Future(gameLoop(update)))

  override def start(): Unit =
    val state = ObjectProperty(State.init)
    val frame = IntegerProperty(0)
    val direction = IntegerProperty(4) // right

    frame.onChange:
      state.update:
        state.value.newState(direction.value)

    stage = new JFXApp3.PrimaryStage:
      width = 600
      height = 600
      scene = new Scene:
        fill = White
        content = state.value.rectangles
        onKeyPressed = key => controls(direction, key)
        state.onChange:
          Platform.runLater:
            content = state.value.rectangles

    gameLoop(() => frame.update(frame.value + 1))
