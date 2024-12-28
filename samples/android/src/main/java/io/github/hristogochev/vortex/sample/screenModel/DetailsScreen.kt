package io.github.hristogochev.vortex.sample.screenModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import io.github.hristogochev.vortex.model.rememberScreenModel
import io.github.hristogochev.vortex.screen.Screen
import io.github.hristogochev.vortex.screen.uniqueScreenKey
import io.github.hristogochev.vortex.navigator.LocalNavigator
import io.github.hristogochev.vortex.sample.DetailsContent
import io.github.hristogochev.vortex.sample.LoadingContent
import io.github.hristogochev.vortex.util.currentOrThrow

data class DetailsScreen(
    val index: Int,
    override val key: String = uniqueScreenKey(),
) : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { DetailsScreenModel(index) }
        val state by screenModel.state.collectAsState()

        when (val result = state) {
            is DetailsScreenModel.State.Loading -> LoadingContent()
            is DetailsScreenModel.State.Result -> DetailsContent(
                screenModel,
                result.item,
                navigator::pop
            )
        }

        LaunchedEffect(currentCompositeKeyHash) {
            screenModel.getItem(index)
        }
    }
}
