package org.cist2931.tabletopbuddy

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.unit.dp

/**
 * Code from https://medium.com/@kappdev/coin-flipper-animation-with-jetpack-compose-33a367c9cde4
 */

val TShape = GenericShape { size, _ ->
    val height = size.height
    val width = size.width

    val heightTenth = (height / 10)
    val widthTenth = (width / 10)

    moveTo((widthTenth * 3), (heightTenth * 2.5f))
    lineTo(width - (widthTenth * 3), (heightTenth * 2.5f))
    lineTo(width - (widthTenth * 3), (heightTenth * 3.5f))
    lineTo((width / 2) + (widthTenth / 2), (heightTenth * 3.5f))
    lineTo((width / 2) + (widthTenth / 2), (height - (heightTenth * 2.5f)))
    lineTo((width / 2) - (widthTenth / 2), (height - (heightTenth * 2.5f)))
    lineTo((width / 2) - (widthTenth / 2), (heightTenth * 3.5f))
    lineTo((widthTenth * 3), (heightTenth * 3.5f))
    close()
}
val FShape = GenericShape { size, _ ->
    val height = size.height
    val width = size.width

    val heightTenth = (height / 10)
    val widthTenth = (width / 10)

    moveTo((widthTenth * 3), (heightTenth * 2.5f))
    lineTo(width - (widthTenth * 3), (heightTenth * 2.5f))
    lineTo(width - (widthTenth * 3), (heightTenth * 3.5f))
    lineTo((widthTenth * 4), (heightTenth * 3.5f))
    lineTo((widthTenth * 4), (heightTenth * 4.5f))
    lineTo(width - (widthTenth * 3), (heightTenth * 4.5f))
    lineTo(width - (widthTenth * 3), (heightTenth * 5.5f))
    lineTo((widthTenth * 4), (heightTenth * 5.5f))
    lineTo((widthTenth * 4), height - (heightTenth * 2.5f))
    lineTo((widthTenth * 3), height - (heightTenth * 2.5f))
    close()
}

val ShadowBlack = Color.Black.copy(0.8f)
val ShadowWhite = Color.White.copy(0.8f)
val LightShadowBlack = Color.Black.copy(0.36f)
val LightShadowWhite = Color.White.copy(0.36f)

val Gold = Color(0xFFFFD700)
val Violet = Color(0xFF5c5174)

@Composable
fun BoxScope.Coin(
    symbolShape: Shape,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interSource = remember { MutableInteractionSource() }

    // Collect the pressed state using interaction source
    val pressed by interSource.collectIsPressedAsState()

    // Animate the scale of the coin based on pressed state
    val coinScale by animateFloatAsState(
        targetValue = if (pressed) 1.15f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy),
        label = "Coin Scale Animation"
    )

    Box(
        modifier = Modifier
            .matchParentSize()
            .scale(coinScale) // Scale the coin
            .background(Color.Yellow, CircleShape)
            // Apply inner shadows for a convex effect
            .innerShadow(
                shape = CircleShape, color = ShadowBlack,
                offsetY = (-2).dp, offsetX = (-2).dp
            )
            .innerShadow(
                shape = CircleShape, color = ShadowWhite,
                offsetY = 2.dp, offsetX = 2.dp
            )
            .clickable(
                interactionSource = interSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .innerShadow(
                    shape = symbolShape, color = LightShadowWhite,
                    blur = 2.dp,
                    offsetY = (-1).dp, offsetX = 1.dp
                )
        ) {
            val outline = symbolShape.createOutline(size, layoutDirection, this)
            drawOutline(outline, Gold)
        }

    }
}
