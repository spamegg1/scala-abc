package C.w1

import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.Button
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Label
import scalafx.animation.AnimationTimer

class TetrisRoot:
  val root = new Scene // add bg, title, width, height

class TetrisTimer(timer: AnimationTimer):
  def stop: Unit = timer.stop()
  def start: Unit = timer.start()

class TetrisCanvas:
  val canvas = new Canvas
  def place = ???
  def unPlace = ???
  def delete = ???

class TetrisLabel:
  val label = new Label
  def place = ???
  def text = ???

class TetrisButton(label: String, color: Color):
  val button = Button(label)
  def place: Unit = ???

class TetrisRect(canvas: Canvas, w: Double, h: Double, color: Color):
  var rect = Rectangle(w, h, color)

  def remove: Unit = ???
  def move(dx: Double, dy: Double): Unit = ???
