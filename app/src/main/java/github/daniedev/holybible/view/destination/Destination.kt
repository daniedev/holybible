package github.daniedev.holybible.view.destination

sealed class Destination(val route: String) {
    object LandingScreen : Destination("landing")
    object BibleVerseScreen: Destination("bibleVerse")
}
