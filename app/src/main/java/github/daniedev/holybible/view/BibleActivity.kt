@file:OptIn(ExperimentalPagerApi::class)

package github.daniedev.holybible.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*
import github.daniedev.holybible.view.destination.Destination
import github.daniedev.holybible.view.theme.HolyBibleTheme
import github.daniedev.holybible.viewmodel.BibleLandingViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

val items = listOf(
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Yellow,
    Color.Cyan,
    Color.Magenta,
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Yellow,
)

class BibleActivity : ComponentActivity() {

    private val viewModel: BibleLandingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HolyBibleTheme {
                HolyBibleApp()
            }
        }
    }

    @Composable
    fun HolyBibleApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = Destination.LandingScreen.route) {
            addLandingGraph(navController)
            addBibleVerseGraph()
        }
    }

    private fun NavGraphBuilder.addLandingGraph(navController: NavController) {
        composable(route = Destination.LandingScreen.route) {
            BibleLandingScreen(navigateToBibleVerseScreen = { navController.navigate(Destination.BibleVerseScreen.route)}, bibleLandingViewModel = viewModel)
        }
    }

    private fun NavGraphBuilder.addBibleVerseGraph() {
        composable(route = Destination.BibleVerseScreen.route) {
            BibleVerseScreen()
        }
    }
}




@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Sample() {
    val pagerState = rememberPagerState()
    Column {
        HorizontalPagerWithOffsetTransition(Modifier.fillMaxHeight(.95f), pagerState)
//            ActionsRow(
//                pagerState = pagerState,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
    }
}

@Composable
fun HorizontalPagerWithOffsetTransition(modifier: Modifier = Modifier, pagerState: PagerState) {
    HorizontalPager(
        count = 30,
        state = pagerState,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        modifier = modifier
            .fillMaxWidth()
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                Modifier.background(
                    color = items[page % 10]
                )
            ) {
//                Image(
//                    painter = rememberImagePainter(
//                        data = rememberRandomSampleImageUrl(width = 600),
//                    ),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),
//                )
                Text(
                    text = "item number: $page",
                    modifier = Modifier.align(Alignment.Center)
                )

//                ProfilePicture(
//                    Modifier
//                        .align(Alignment.BottomCenter)
//                        .padding(16.dp)
//                        // We add an offset lambda, to apply a light parallax effect
//                        .offset {
//                            // Calculate the offset for the current page from the
//                            // scroll position
//                            val pageOffset =
//                                this@HorizontalPager.calculateCurrentOffsetForPage(page)
//                            // Then use it as a multiplier to apply an offset
//                            IntOffset(
//                                x = (36.dp * pageOffset).roundToPx(),
//                                y = 0
//                            )
//                        }
//                )
            }
        }
    }
}

@Composable
internal fun ActionsRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    infiniteLoop: Boolean = false
) {
    Row(modifier) {
        val scope = rememberCoroutineScope()

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        ) {
            Icon(Icons.Default.FirstPage, null)
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateBefore, null)
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateNext, null)
        }

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            }
        ) {
            Icon(Icons.Default.LastPage, null)
        }
    }
}

@Composable
private fun ProfilePicture(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(4.dp, MaterialTheme.colors.surface)
    ) {
        Image(
            painter = rememberAsyncImagePainter(rememberRandomSampleImageUrl()),
            contentDescription = null,
            modifier = Modifier.size(72.dp),
        )
    }
}

private val rangeForRandom = (0..100000)

@Composable
fun rememberRandomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String = remember { randomSampleImageUrl(seed, width, height) }

fun randomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String {
    return "https://picsum.photos/seed/$seed/$width/$height"
}


