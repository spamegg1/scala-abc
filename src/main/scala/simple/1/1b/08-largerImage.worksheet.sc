import doodle.image.Image
import doodle.image.Image.Elements.*

// Image Image -> Boolean
// produce true if first image's both height and width are larger than second's,
// false otherwise
val rectangle1 = Rectangle(10, 10)
val rectangle2 = Rectangle(9, 9)
val rectangle3 = Rectangle(10, 9)
val rectangle4 = Rectangle(9, 10)

// Tests
!largerImage(rectangle1, rectangle1)
largerImage(rectangle1, rectangle2)
!largerImage(rectangle2, rectangle1)
!largerImage(rectangle1, rectangle3)
!largerImage(rectangle1, rectangle4)
!largerImage(rectangle4, rectangle1)
!largerImage(rectangle3, rectangle4)
!largerImage(rectangle4, rectangle3)

// def largerImage(i1: Image, i2: Image): Boolean = ??? // stub

// template
// def largerImage(i1: Image, i2: Image): Boolean =
//   someFun(i1.w, i1.h, i2.w, i2.h)

def largerImage(i1: Rectangle, i2: Rectangle): Boolean =
  i1.w > i2.w && i1.h > i2.h

