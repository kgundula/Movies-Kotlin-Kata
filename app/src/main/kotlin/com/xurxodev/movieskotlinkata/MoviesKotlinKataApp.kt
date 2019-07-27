package com.xurxodev.movieskotlinkata

import android.app.Application
import org.codejargon.feather.Feather

class MoviesKotlinKataApp : Application() {

    val feather: Feather by lazy {
        Feather.with(ApplicationModule(this))
    }
}