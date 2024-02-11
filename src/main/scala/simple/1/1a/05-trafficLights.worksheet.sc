import doodle.core.Color
import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer
import cats.effect.unsafe.implicits.global

def colorCircle(c: Color): Image = Image.circle(200).fillColor(c)

val redCircle = colorCircle(Color.red)
val yellowCircle = colorCircle(Color.yellow)
val greenCircle = colorCircle(Color.green)

val trafficLights =
  redCircle
    .above(yellowCircle)
    .above(greenCircle)

trafficLights.draw()
