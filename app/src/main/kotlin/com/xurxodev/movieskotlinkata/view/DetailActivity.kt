package com.xurxodev.movieskotlinkata.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xurxodev.moviesandroidkotlin.R
import com.xurxodev.movieskotlinkata.data.FakeMovieRepository
import com.xurxodev.movieskotlinkata.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getLongExtra(EXTRA_ID, -1)

        loadMovie(id)
    }


    private fun loadMovie(id: Long) {
        loadingMovie()

        GlobalScope.launch(Dispatchers.Main) {
            val movies = asyncLoadMovies(id).await()

            loadedMovie(movies)
        }
    }

    private fun asyncLoadMovies(id: Long) = GlobalScope.async {
        FakeMovieRepository(this@DetailActivity).getById(id)
    }

    private fun loadingMovie() {
        movie_detail_container.visibility = View.GONE
        pb_loading.visibility = View.VISIBLE
    }

    private fun loadedMovie(movie: Movie) {
        pb_loading.visibility = View.GONE
        movie_detail_container.visibility = View.VISIBLE

        item_image.loadUrl(movie.url)
        item_title.text = movie.title
        item_overview_content.text = movie.overview
    }
}
