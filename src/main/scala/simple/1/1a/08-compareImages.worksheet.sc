import doodle.image.Image.Elements.Rectangle

/* Based on the two constants provided: */
val img1 = Rectangle(10, 15)
val img2 = Rectangle(15, 10)

/**
  * (using .w and .h to access width and height)
  * write three expressions to determine whether:
  */

/*  1) IMAGE1 is taller than IMAGE2 */
val oneTallerThanTwo: Boolean = img1.h > img2.h

/* 2) IMAGE1 is narrower than IMAGE2 */
val oneNarrowerThanTwo = img1.w < img2.w

/* 3) IMAGE1 has both the same width AND height as IMAGE2 */
val sameWidthHeight = img1.w == img2.w && img1.h == img2.h
