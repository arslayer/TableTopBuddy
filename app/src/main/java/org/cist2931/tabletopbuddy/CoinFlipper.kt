package org.cist2931.tabletopbuddy

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CoinFlipper(
    modifier: Modifier = Modifier,
    duration: Int = 2_000,
    maxJumpHeight: Dp = 100.dp,
    forceRange: IntRange = (5..20),
    frontSide: @Composable BoxScope.(isRunning: Boolean, flip: () -> Unit) -> Unit,
    backSide: @Composable BoxScope.(isRunning: Boolean, flip: () -> Unit) -> Unit
) {
    require(duration > 0) { "Duration should be a positive value." }
    require(forceRange.first >= 0 && forceRange.last >= forceRange.first) { "Invalid force range." }

    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    val rotationAnim = remember { Animatable(0f) }
    val translationAnim = remember { Animatable(0f) }

    val jumpHeight = remember(maxJumpHeight) { with(density) { maxJumpHeight.toPx() } }
    val jumpStep = remember(jumpHeight, forceRange) { (jumpHeight / forceRange.last) }
    val isRunning = remember(rotationAnim.isRunning, translationAnim.isRunning) {
        (rotationAnim.isRunning || translationAnim.isRunning)
    }

    val performFlip = {
        if (!isRunning) {
            val force = forceRange.random()
            val rotationTarget = rotationAnim.value + (force * 3) * 180f
            val translationTarget = (force * jumpStep)

            scope.launch {
                rotationAnim.animateTo(
                    targetValue = rotationTarget,
                    animationSpec = tween(duration, easing = FastOutSlowInEasing)
                )
                rotationAnim.snapTo(rotationAnim.value % 360f)
            }
            scope.launch {
                translationAnim.animateTo(
                    targetValue = 0f,
                    animationSpec = jumpAnimation(duration, -translationTarget)
                )
            }
        }
    }

    Box(
        modifier = modifier.graphicsLayer {
            rotationX = rotationAnim.value
            translationY = translationAnim.value
            cameraDistance = (10f * this.density)
        }
    ) {
        val quarter = determineQuarter(rotationAnim.value)
        if (quarter == 1 || quarter == 4) {
            frontSide(isRunning, performFlip)
        } else {
            ReflectVertically {
                backSide(isRunning, performFlip)
            }
        }
    }
}

@Composable
private fun BoxScope.ReflectVertically(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .matchParentSize()
            .graphicsLayer { rotationX = 180f },
        content = content
    )
}

private fun determineQuarter(degree: Float): Int {
    val normalizedDegree = (degree + 360) % 360
    return when {
        normalizedDegree <= 90f -> 1
        normalizedDegree <= 180f -> 2
        normalizedDegree <= 270f -> 3
        else -> 4
    }
}

private fun jumpAnimation(duration: Int, jumpTarget: Float) = keyframes {
    durationMillis = duration
    0f at 0
    jumpTarget at (duration * 0.3f).toInt()
    0f at duration
}