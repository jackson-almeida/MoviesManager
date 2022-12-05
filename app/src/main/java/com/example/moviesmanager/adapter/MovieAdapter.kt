package com.example.moviesmanager.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.moviesmanager.R
import com.example.moviesmanager.model.entity.Movie

class MovieAdapter(
    context: Context,
    private val movieList: MutableList<Movie>
) : ArrayAdapter<Movie>(context, R.layout.tile_movie, movieList) {
    private data class TileMovieHolder(val nomeTv: TextView, val duracaoTv: TextView, val generoTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val movie = movieList[position]
        var movieTileView = convertView
        if (movieTileView == null) {
            // Inflo uma nova célula
            movieTileView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_movie,
                    parent,
                    false
                )

            val tileMovieHolder = TileMovieHolder(
                movieTileView.findViewById(R.id.nomeTv),
                movieTileView.findViewById(R.id.duracaoTv),
                movieTileView.findViewById(R.id.generoSp),
            )
            movieTileView.tag = tileMovieHolder
        }

        with(movieTileView?.tag as TileMovieHolder) {
            nomeTv.text = "Nome do filme: " + movie.nome + " | Gênero: " + movie.genero
            duracaoTv.text = "O filme tem " + movie.duracao.toString() + " minutos de duração"
        }

        return movieTileView
    }
}