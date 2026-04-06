// import scalafx.application.JFXApp3
// import scalafx.application.JFXApp3.*
// import scalafx.geometry.Insets
// import scalafx.scene.Scene
// import scalafx.scene.effect.DropShadow
// import scalafx.scene.layout.HBox
// import scalafx.scene.shape.Rectangle
// import scalafx.scene.paint.Color.*
// import scalafx.scene.paint.*
// import scalafx.scene.text.Text
// import doodle.core.*
// import doodle.image.*
// import doodle.image.syntax.*
// import doodle.image.syntax.all.*
// import doodle.image.syntax.core.*
// import doodle.java2d.*
// import cats.effect.*
// import cats.effect.unsafe.implicits.global

// object Main extends IOApp.Simple:
//   def run: IO[Unit] = IO(Image.triangle(60, 40).draw())

//@main
def main: Unit =
  println("run successful")

// object Main extends JFXApp3:
// override def start(): Unit =
//   val tetrisLabel = "Tetris"
//   val tetrisColor = LightBlue
//   val (tetrisHeight, tetrisWidth) = (615, 205)

//   val tetrisRectangle = new Rectangle:
//     width = tetrisWidht
//     height = tetrisHeight

//   val tetrisScene = new Scene:
//     fill = tetrisColor
//     content = tetrisRectangle

//   stage = new PrimaryStage:
//     title = tetrisLabel
//     scene = tetrisScene
