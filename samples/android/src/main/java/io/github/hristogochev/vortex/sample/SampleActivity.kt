package io.github.hristogochev.vortex.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.hristogochev.vortex.sample.basicNavigation.BasicNavigationActivity
import io.github.hristogochev.vortex.sample.bottomSheetNavigation.BottomSheetNavigationActivity
import io.github.hristogochev.vortex.sample.kodeinIntegration.KodeinIntegrationActivity
import io.github.hristogochev.vortex.sample.koinIntegration.KoinIntegrationActivity
import io.github.hristogochev.vortex.sample.nestedNavigation.NestedNavigationActivity
import io.github.hristogochev.vortex.sample.parcelableScreen.ParcelableActivity
import io.github.hristogochev.vortex.sample.screenModel.ScreenModelActivity
import io.github.hristogochev.vortex.sample.stateStack.StateStackActivity
import io.github.hristogochev.vortex.sample.tabNavigation.TabNavigationActivity

class SampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp)
        ) {
            item {
                StartSampleButton<StateStackActivity>("SnapshotStateStack")
                StartSampleButton<BasicNavigationActivity>("Basic Navigation")
                StartSampleButton<ParcelableActivity>("Basic Navigation with Parcelable")
                StartSampleButton<TabNavigationActivity>("Tab Navigation")
                StartSampleButton<BottomSheetNavigationActivity>("BottomSheet Navigation")
                StartSampleButton<NestedNavigationActivity>("Nested Navigation")
                StartSampleButton<ScreenModelActivity>("ScreenModel")
                StartSampleButton<KoinIntegrationActivity>("Koin Integration")
                StartSampleButton<KodeinIntegrationActivity>("Kodein Integration")
                StartSampleButton<io.github.hristogochev.vortex.sample.screenTransition.ScreenTransitionActivity>("Screen Transition")
            }
        }
    }

    @Composable
    private inline fun <reified T : Activity> StartSampleButton(text: String) {
        val context = LocalContext.current

        Button(
            onClick = { context.startActivity(Intent(this, T::class.java)) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = text)
        }
    }
}
