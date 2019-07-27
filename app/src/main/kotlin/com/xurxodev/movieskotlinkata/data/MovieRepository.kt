package com.xurxodev.movieskotlinkata.data

import com.xurxodev.movieskotlinkata.model.Movie

interface MovieRepository {
    fun getById(id: Long): Movie
    fun getAll(): List<Movie>
}