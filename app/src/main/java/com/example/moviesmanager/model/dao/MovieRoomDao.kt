package com.example.moviesmanager.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.moviesmanager.model.entity.Movie

@Dao
interface MovieRoomDao {
    companion object Constant {
        const val MOVIE_DATABASE_FILE = "movies_room"
        const val MOVIE_TABLE = "movie"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "nome"
        const val NOTA_COLUMN = "nota"
//        const val ANO_LANCAMENTO_COLUMN = "anoLancamento"
//        const val ESTUDIO_COLUMN = "estudio"
//        const val DURACAO_COLUMN = "duracao"
//        const val FLAG_COLUMN = "flag"
//        const val GENERO_COLUMN = "genero"
    }

    @Insert
    fun createMovie(movie: Movie)

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $ID_COLUMN = :id")
    fun retrieveMovie(id: Int): Movie?

    @Query("SELECT * FROM $MOVIE_TABLE ORDER BY $NAME_COLUMN")
    fun retrieveMovies(): MutableList<Movie>

    @Update
    fun updateMovie(movie: Movie): Int

    @Delete
    fun deleteMovie(movie: Movie): Int
}