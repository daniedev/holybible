package github.daniedev.holybible.util.view.viewpager

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun <T : Any> Pager(
    items: List<T>,
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Horizontal,
    initialIndex: Int = 0,
    itemFraction: Float = 1f,
    itemSpacing: Dp = 0.dp,
    overshootFraction: Float = .5f,
    onItemSelect: (T) -> Unit = {},
    contentFactory: @Composable (T) -> Unit = {}
) {
    require(initialIndex in 0 .. items.lastIndex) { "Initial Index Out of Bounds"}
    require(itemFraction > 0f && itemFraction <= 1f) { "Item fraction must be in the range 0f to 1f"}
    require(overshootFraction > 0f && itemFraction <= 1f) { "Overshoot fraction must be in the range 0f to 1f"}
    val scope = rememberCoroutineScope()
    val state = rememberPagerState()
    state.currentIndex = initialIndex
    state.numberOfItems = items.size
    state.itemFraction = itemFraction
    state.overShootFraction = overshootFraction
    state.itemSpacing = with(LocalDensity.current) { itemSpacing.toPx() }
    state.orientation = orientation
    state.listener = { index -> onItemSelect(items[index]) }
    state.scope = scope

    Layout(
        content = {
            items.map {
                item ->
                   Box(
                    modifier = when (orientation) {
                        Orientation.Horizontal -> Modifier.fillMaxWidth()
                        Orientation.Vertical -> Modifier.fillMaxHeight()
                    },
                       contentAlignment = Alignment.Center
                ) {
                       contentFactory(item)
                   }
            }
        },
        modifier = modifier
            .clipToBounds()
            .then(state.inputModifier)
    ) {
        measurables, constraints ->
        val dimension = constraints.dimension(orientation)
        val looseConstraints = constraints.toLooseConstraints(orientation, state.itemFraction)
        val placeables = measurables.map {measurable -> measurable.measure(looseConstraints)}
        val size = placeables.getSize(orientation, dimension)
        val itemDimension = (dimension * state.itemFraction ).roundToInt()
        state.itemDimension = itemDimension
        val halfItemDimension = itemDimension / 2
        layout(size.width, size.height) {
            val centerOffset = dimension / 2 - halfItemDimension
            val dragOffset = state.dragOffset.value
            val roundedDragOffset = dragOffset.roundToInt()
            val spacing = state.itemSpacing.roundToInt()
            val itemDimensionWithSpace = itemDimension + state.itemSpacing
            val first  = ceil((dragOffset - itemDimension - centerOffset) / itemDimensionWithSpace).toInt().coerceAtLeast(0)
            val last = ceil((dimension + dragOffset - centerOffset) / itemDimensionWithSpace).toInt().coerceAtMost(items.lastIndex)
            for (i in first..last) {
                val offset = i * (itemDimension + spacing) - roundedDragOffset + centerOffset
                placeables[i].place(
                    x = when (orientation) {
                        Orientation.Horizontal -> offset
                        Orientation.Vertical -> 0
                    },
                    y = when (orientation) {
                        Orientation.Horizontal -> 0
                        Orientation.Vertical -> offset
                    }

                )
            }
        }
    }

    LaunchedEffect(key1 = items, key2 = initialIndex) {
        state.snapTo(initialIndex)
    }

}

@Composable
private fun rememberPagerState(): PagerState = remember {
    PagerState()
}

private fun Constraints.dimension(orientation: Orientation) = when(orientation) {
    Orientation.Horizontal -> maxWidth
    Orientation.Vertical -> maxHeight
}

private fun Constraints.toLooseConstraints(orientation: Orientation, itemFraction: Float) : Constraints {
    val dimension = dimension(orientation)
    val adjustedDimension = (itemFraction * dimension).roundToInt()
    return when(orientation) {
        Orientation.Horizontal -> copy(
            minWidth = adjustedDimension,
            maxWidth = adjustedDimension,
            minHeight = 0
        )
        Orientation.Vertical -> copy(
            minWidth = 0,
            minHeight = adjustedDimension,
            maxHeight = adjustedDimension
        )
    }
}

private fun List<Placeable>.getSize(
    orientation: Orientation,
    dimension: Int
): IntSize = when(orientation) {
    Orientation.Horizontal -> IntSize(dimension, maxByOrNull { it.height }?.height ?: 0)
    Orientation.Vertical -> IntSize(maxByOrNull { it.width }?.width ?: 0, dimension)
}

fun VelocityTracker.calculateVelocity(orientation: Orientation) = when (orientation) {
    Orientation.Horizontal -> calculateVelocity().x
    Orientation.Vertical -> calculateVelocity().y
}

fun PointerInputChange.calculateDragChange(orientation: Orientation) = when (orientation) {
    Orientation.Horizontal -> positionChange().x
    Orientation.Vertical -> positionChange().y
}


