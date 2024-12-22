package com.hristogochev.vortex.sample.screenModel

import com.hristogochev.vortex.model.ScreenModel
import com.hristogochev.vortex.sample.sampleItems

class ListScreenModel : ScreenModel {

    val items = sampleItems

    override fun onDispose() {
        println("ScreenModel: dispose list")
    }
}
