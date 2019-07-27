package com.xurxodev.movieskotlinkata

import com.xurxodev.movieskotlinkata.data.FakeMovieRepository
import com.xurxodev.movieskotlinkata.data.MovieRepository
import org.codejargon.feather.Provides
import javax.inject.Singleton

class ApplicationModule(private val moviesKotlinKataApp: MoviesKotlinKataApp) {


    @Provides
    @Singleton
    fun movieRepository(): MovieRepository {
        return FakeMovieRepository(moviesKotlinKataApp)
    }
}