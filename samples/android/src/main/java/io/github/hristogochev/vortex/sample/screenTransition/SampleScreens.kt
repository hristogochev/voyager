package io.github.hristogochev.vortex.sample.screenTransition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.hristogochev.vortex.model.ScreenModel
import io.github.hristogochev.vortex.model.rememberScreenModel
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.screen.ScreenTransition
import io.github.hristogochev.vortex.transitions.FadeTransition
import io.github.hristogochev.vortex.transitions.SlideTransition

private val colors = listOf(
    Color.Red,
    Color.Yellow,
    Color.Green,
    Color.Blue,
    Color.Black
)

class BaseSampleScreenModel(
    val index: Int,
) : ScreenModel {

    init {
        println("Init BaseSampleScreenModel $index")
    }

    override fun onDispose() {
        println("Disposing BaseSampleScreenModel $index")
    }
}

abstract class BaseSampleScreen(
    private val transitionType: String,
    private val index: Int,
    override val key: String = "SampleScreen$index",
    override val onAppearTransition: ScreenTransition? = null,
    override val onDisappearTransition: ScreenTransition? = null,
) : Screen {


    @Composable
    override fun Content() {
        val model = rememberScreenModel {
            BaseSampleScreenModel(index)
        }
        val color = remember {
            colors.getOrNull(index % colors.size) ?: colors.random()
        }
        val contentColor = remember {
            color.average()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Screen $index",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = transitionType,
                fontSize = 18.sp,
                color = contentColor
            )
        }
    }
}

private fun Color.average(): Color {
    return Color(
        (255 - red * 255) / 255,
        (255 - green * 255) / 255,
        (255 - blue * 255) / 255,
        alpha
    )
}

data class NoCustomAnimationSampleScreen(private val index: Int) :
    BaseSampleScreen(transitionType = "No transition", index = index)

data class FadeAnimationSampleScreen(private val index: Int) :
    BaseSampleScreen(
        "Fade transition",
        index = index,
        onAppearTransition = FadeTransition,
        onDisappearTransition = FadeTransition
    )

data class SlideInVerticallyAnimationSampleScreen(
    val index: Int,
) : BaseSampleScreen(
    "slide in vertically transition",
    index = index,
    onAppearTransition = SlideTransition.Vertical.Appear,
    onDisappearTransition = SlideTransition.Vertical.Disappear
)