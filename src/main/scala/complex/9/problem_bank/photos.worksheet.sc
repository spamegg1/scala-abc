// Data definitions:
case class Photo(location: String, album: String, isFavorite: Boolean)
// interp. a photo having a location, belonging to an album and having a
// favourite status (true if photo is a favourite, false otherwise)
val photo1 = Photo("photos/2012/june", "Victoria", true)
val photo2 = Photo("photos/2013/birthday", "Birthday", true)
val photo3 = Photo("photos/2012/august", "Seattle", true)

// Functions:
// PROBLEM:
// Design a function called to-frame that consumes an album name and a list of
// photos and that produces a list of only those photos that are favourites and
// that belong to the given album. You must use built-in abstract functions
// wherever possible.
// String (listof Photo) -> (listof Photo)
// produces a list of photos that are favourites and belong to the given album
toFrame("Family", Nil).isEmpty
toFrame("Victoria", List(photo1, photo2, photo3)) == List(photo1)
toFrame("Seattle", List(photo1, photo3)) == List(photo3)

// <template as call to filter>
def toFrame(albumName: String, photos: List[Photo]): List[Photo] =
  val isChosen = (photo: Photo) => albumName == photo.album && photo.isFavorite
  photos filter isChosen
