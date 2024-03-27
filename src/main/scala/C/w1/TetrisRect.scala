package C.w1

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.canvas.Canvas

class TetrisRect(canvas: Canvas, w: Double, h: Double, color: Color):
  var rect = Rectangle(w, h, color)

  def remove: Unit = ???
  def move(dx: Double, dy: Double): Unit = ???
