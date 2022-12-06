package com.example.moviesmanager.view

import android.os.Bundle
import android.view.View
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesmanager.databinding.ActivityMovieBinding
import com.example.moviesmanager.model.entity.Movie
import com.example.moviesmanager.model.Constant.EXTRA_MOVIE
import com.example.moviesmanager.model.Constant.VIEW_MOVIE

class MovieActivity : AppCompatActivity() {
    private val amb: ActivityMovieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        val viewMovie = intent.getBooleanExtra(VIEW_MOVIE, false)
        val receivedMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if(amb.flagJaAssistidoCb.isChecked) amb.notaEt.visibility = View.VISIBLE
        amb.flagJaAssistidoCb.setOnClickListener() {
            if (amb.flagJaAssistidoCb.isChecked.equals(false)) amb.notaEt.visibility = View.GONE
            else amb.notaEt.visibility = View.VISIBLE
        }

        receivedMovie?.let{ _receivedMovie ->
            with(amb) {
                with(_receivedMovie) {
                    amb.nomeEt.isEnabled = false
                    nomeEt.setText(nome)
                    lancamentoEt.setText(anoLancamento.toString())
                    estudioProdutoraEt.setText(estudio)
                    duracaoEt.setText(duracao.toString())
                    notaEt.setText(nota.toString())
                    if (flag.equals(true)) {
                        amb.notaEt.visibility = View.VISIBLE
                        amb.flagJaAssistidoCb.isChecked = true
                    }
                    if (viewMovie) {
                        amb.lancamentoEt.isEnabled = false
                        amb.estudioProdutoraEt.isEnabled = false
                        amb.duracaoEt.isEnabled = false
                        amb.notaEt.isEnabled = false
                        amb.notaEt.visibility = View.VISIBLE
                        amb.flagJaAssistidoCb.isEnabled = false

                        amb.generoSp.isEnabled = false
                        amb.generoEt.isEnabled = false
                        amb.salveBt.visibility = View.GONE
                        amb.generoSp.visibility = View.GONE
                        amb.generoEt.visibility = View.VISIBLE
                        amb.generoEt.setText(genero)
                    }
                }
            }
        }



    }
}