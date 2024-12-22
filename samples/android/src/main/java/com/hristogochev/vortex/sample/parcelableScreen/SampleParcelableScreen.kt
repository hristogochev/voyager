package com.hristogochev.vortex.sample.parcelableScreen

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hristogochev.vortex.screen.Screen
import com.hristogochev.vortex.screen.uniqueScreenKey
import com.hristogochev.vortex.navigator.LocalNavigator
import com.hristogochev.vortex.screen.ScreenDisposableEffect
import com.hristogochev.vortex.util.currentOrThrow
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableContent(
    val index: Int,
) : Parcelable

@Parcelize
data class SampleParcelableScreen(
    val parcelable: ParcelableContent,
    val wrapContent: Boolean = false,
    override val key: String = uniqueScreenKey(),
) : Screen, Parcelable {


    @Composable
    override fun Content() {
        ScreenDisposableEffect{
            Log.d("Navigator", "Start screen #${parcelable.index}")
            onDispose {
                Log.d("Navigator", "Dispose screen #${parcelable.index}")
            }
        }


        val navigator = LocalNavigator.currentOrThrow

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.run {
                if (wrapContent) {
                    padding(vertical = 16.dp).wrapContentHeight()
                } else {
                    fillMaxSize()
                }
            }
        ) {
            Text(
                text = "Screen #${parcelable.index}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    enabled = navigator.canPop,
                    onClick = navigator::pop,
                    modifier = Modifier.weight(.5f)
                ) {
                    Text(text = "Pop")
                }

                Spacer(modifier = Modifier.weight(.1f))

                Button(
                    onClick = {
                        navigator.push(
                            SampleParcelableScreen(
                                parcelable.copy(index = parcelable.index.inc()),
                                wrapContent
                            )
                        )
                    },
                    modifier = Modifier.weight(.5f)
                ) {
                    Text(text = "Push")
                }

                Spacer(modifier = Modifier.weight(.1f))

                Button(
                    onClick = {
                        navigator.replace(
                            SampleParcelableScreen(
                                parcelable.copy(index = parcelable.index.inc()),
                                wrapContent
                            )
                        )
                    },
                    modifier = Modifier.weight(.5f)
                ) {
                    Text(text = "Replace")
                }
            }

            LazyColumn(
                modifier = Modifier.height(100.dp)
            ) {
                items(100) {
                    Text("Item #$it")
                }
            }
        }
    }
}
