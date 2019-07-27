package com.xurxodev.movieskotlinkata.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xurxodev.moviesandroidkotlin.R
import com.xurxodev.movieskotlinkata.MoviesKotlinKataApp
import com.xurxodev.movieskotlinkata.data.MovieRepository
import com.xurxodev.movieskotlinkata.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var itemAdapter: ItemAdapter

    private val movieRepository: MovieRepository by lazy {
        ((application as MoviesKotlinKataApp).feather.instance(MovieRepository::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyclerView()
        initializeRefreshButton()
        loadMovies()
    }

    private fun initializeRecyclerView() {
        this.itemAdapter = ItemAdapter() { item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.Companion.EXTRA_ID, item.id)
            startActivity(intent)
        }

        recycler.adapter = itemAdapter
    }

    private fun initializeRefreshButton() = refresh_button.setOnClickListener { loadMovies() }

    private fun loadMovies() {
        loadingMovies()

        GlobalScope.launch(Dispatchers.Main) {
            val movies = asyncLoadMovies().await()

            loadedMovies(movies)
        }
    }

    private fun asyncLoadMovies() = GlobalScope.async {
        movieRepository.getAll()
    }

    private fun loadingMovies() {
        itemAdapter.clearMovies()
        pb_loading.visibility = View.VISIBLE
        movies_title_text_view.text = getString(R.string.loading_movies_text)
    }

    private fun loadedMovies(movies: List<Movie>) {
        itemAdapter.setMovies(movies)
        pb_loading.visibility = View.GONE
        refreshTitleWithMoviesCount(movies)
    }

    private fun refreshTitleWithMoviesCount(movies: List<Movie>) {
        val countText = getString(R.string.movies_count_text)

        movies_title_text_view.text = String.format(countText, movies.size)
    }
}