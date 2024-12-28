package io.github.hristogochev.vortex.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
@NonRestartableComposable
internal actual fun rememberConfigurationChecker(): ConfigurationChecker {
    val context = LocalContext.current
    return remember(context) { AndroidConfigurationChecker(context.getActivity()) }
}

@Stable
internal class AndroidConfigurationChecker(private val activity: Activity?) :
    ConfigurationChecker {
    override fun isChangingConfigurations(): Boolean {
        return activity?.isChangingConfigurations ?: false
    }
}
