package com.example.moviesmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.moviesmanager.R
import com.example.moviesmanager.adapter.MovieAdapter
import com.example.moviesmanager.controller.MovieRoomController
import com.example.moviesmanager.databinding.ActivityMainBinding
import com.example.moviesmanager.model.entity.Movie
import com.example.moviesmanager.model.Constant.EXTRA_MOVIE
import com.example.moviesmanager.model.Constant.VIEW_MOVIE

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val movieController: MovieRoomController by lazy {
        MovieRoomController(this)
    }

    private var movieList: MutableList<Movie> = mutableListOf()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieController.getMovies()
        movieAdapter = MovieAdapter(this, movieList)
        amb.moviesLv.adapter = movieAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val movie = result.data?.getParcelableExtra<Movie>(EXTRA_MOVIE)

                movie?.let { _movie ->
                    val verificaNome = movieList.indexOfFirst { it.nome == _movie.nome }
                    val posicao = movieList.indexOfFirst { it.id == _movie.id }

                    if (posicao != -1) movieController.editMovie(_movie)
                    else {
                        if (verificaNome.equals(-1)) movieController.insertMovie(_movie)
                        else Toast.makeText(this, "Esse filme já existe!", Toast.LENGTH_LONG).show()
                    }
                    movieAdapter.notifyDataSetChanged()
                }

            }
        }

        registerForContextMenu(amb.moviesLv)
        amb.moviesLv.onItemClickListener =
        AdapterView.OnItemClickListener { _, _, indice, _ ->
            val movie = movieList[indice]
            val movieIntent = Intent(this@MainActivity, MovieActivity::class.java)
            movieIntent.putExtra(EXTRA_MOVIE, movie)
            movieIntent.putExtra(VIEW_MOVIE, true)
            startActivity(movieIntent)
        }
    }

    fun updateMovieList(_movieList: MutableList<Movie>) {
        movieList.clear()
        movieList.addAll(_movieList)
        movieAdapter.notifyDataSetChanged()
    }
}