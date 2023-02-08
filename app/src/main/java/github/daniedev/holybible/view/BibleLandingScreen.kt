package github.daniedev.holybible.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import github.daniedev.holybible.viewmodel.BibleLandingViewModel
import kotlinx.coroutines.async

@Composable
fun BibleLandingScreen(navigateToBibleVerseScreen : () -> Unit, bibleLandingViewModel: BibleLandingViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val bibleVerseItem = coroutineScope.async { bibleLandingViewModel.getBibleVerseItems("GEN.1.1") }
}