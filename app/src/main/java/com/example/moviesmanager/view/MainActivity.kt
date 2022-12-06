package com.example.moviesmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesmanager.R
import com.example.moviesmanager.adapter.MovieAdapter
import com.example.moviesmanager.databinding.ActivityMainBinding
import com.example.moviesmanager.model.entity.Movie

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var movieList: MutableList<Movie> = mutableListOf()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun updateMovieList(_movieList: MutableList<Movie>) {
        movieList.clear()
        movieList.addAll(_movieList)
        movieAdapter.notifyDataSetChanged()
    }
}