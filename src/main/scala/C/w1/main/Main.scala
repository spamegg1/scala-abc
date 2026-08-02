package C.w1

import scalafx.application.{JFXApp3, Platform}
import scalafx.Includes.*
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

case class State(x: Int):
  def newState                    = this
  def rectangles: List[Rectangle] = Nil

object TetrisGame extends JFXApp3:
  def handleKeys(key: KeyEvent): Unit = ()

  def gameLoop(update: => Unit): Unit =
    Future:
      update
      Thread.sleep(Constants.SleepTime)
    .flatMap(_ => Future(gameLoop(update)))

  override def start(): Unit =
    val state = ObjectProperty(State(0))
    val frame = IntegerProperty(0)

    frame.onChange:
      state.update:
        state.value.newState

    stage = new JFXApp3.PrimaryStage:
      width = Constants.Width
      height = Constants.Height
      scene = new Scene:
        fill = Blue
        content = state.value.rectangles
        onKeyPressed = handleKeys
        state.onChange:
          Platform.runLater:
            content = state.value.rectangles

    gameLoop:
      frame.update(frame.value + 1)
