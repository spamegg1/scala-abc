package C.w1

import scalafx.animation.AnimationTimer

class TetrisTimer(timer: AnimationTimer):
  def stop: Unit = timer.stop()
  def start: Unit = timer.start()
