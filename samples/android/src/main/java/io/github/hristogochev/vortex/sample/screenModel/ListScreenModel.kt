package io.github.hristogochev.vortex.sample.screenModel

import io.github.hristogochev.vortex.model.ScreenModel
import io.github.hristogochev.vortex.sample.sampleItems

class ListScreenModel : ScreenModel {

    val items = sampleItems

    override fun onDispose() {
        println("ScreenModel: dispose list")
    }
}
