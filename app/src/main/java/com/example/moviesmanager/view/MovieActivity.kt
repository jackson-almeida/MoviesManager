package com.example.moviesmanager.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
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
                        amb.generoSp.visibility = View.GONE

                        amb.generoEt.isEnabled = false
                        amb.generoEt.visibility = View.VISIBLE

                        amb.salveBt.visibility = View.GONE
                        amb.generoEt.setText(genero)
                    }
                }
            }
        }

        var generoSelecionado: String = ""
        amb.generoSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, indice: Int, id: Long) {
                generoSelecionado = parent?.getItemAtPosition(indice).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        amb.salveBt.setOnClickListener {
            // Verifica se o nome está vazio
            if (amb.nomeEt.text.toString().length.equals(0)) Toast.makeText(this, "Nome inválido", Toast.LENGTH_LONG).show()

            // Verifica se a flag foi ativada e se o range da nota dada está entre 0 e 10
            if(amb.flagJaAssistidoCb.isChecked && (amb.notaEt.text.toString().toInt() < 0 || amb.notaEt.text.toString().toInt() > 10))
                Toast.makeText(this,"Essa nota não é válida!", Toast.LENGTH_LONG).show()

            else {
                var campoNota: Int = 0
                if (!amb.flagJaAssistidoCb.isChecked ) campoNota = 0
                else amb.notaEt.text.toString().toInt()

                val movie = Movie(
                    id = receivedMovie?.id ?: Random(System.currentTimeMillis()).nextInt(),
                    nome = amb.nomeEt.text.toString(),
                    anoLancamento = amb.lancamentoEt.text.toString().toInt(),
                    estudio = amb.estudioProdutoraEt.text.toString(),
                    duracao = amb.duracaoEt.text.toString().toInt(),
                    flag = amb.flagJaAssistidoCb.isChecked,
                    nota = campoNota,
                    genero = generoSelecionado
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_MOVIE, movie)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}