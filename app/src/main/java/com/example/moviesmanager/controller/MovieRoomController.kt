package com.example.moviesmanager.controller

import android.os.AsyncTask
import androidx.room.Room
import com.example.moviesmanager.model.dao.MovieRoomDao
import com.example.moviesmanager.model.database.MovieRoomDaoDatabase
import com.example.moviesmanager.view.MainActivity
import com.example.moviesmanager.model.dao.MovieRoomDao.Constant.MOVIE_DATABASE_FILE
import com.example.moviesmanager.model.entity.Movie

class MovieRoomController(private val mainActivity: MainActivity) {
    private val movieDaoImpl: MovieRoomDao by lazy {
        Room.databaseBuilder(
            mainActivity,
            MovieRoomDaoDatabase::class.java,
            MOVIE_DATABASE_FILE
        ).build().getMovieRoomDao()
    }

    fun insertMovie(movie: Movie) {
        Thread {
            movieDaoImpl.createMovie(movie)
            getMovies()
        }.start()
    }

    fun getMovie(id: Int) = movieDaoImpl.retrieveMovie(id)

    fun getMovies() {
        object: AsyncTask<Unit, Unit, MutableList<Movie>>(){
            override fun doInBackground(vararg params: Unit?): MutableList<Movie> {
                val returnList = mutableListOf<Movie>()
                returnList.addAll(movieDaoImpl.retrieveMovies())
                return returnList
            }

            override fun onPostExecute(result: MutableList<Movie>?) {
                super.onPostExecute(result)
                if (result != null){
                    mainActivity.updateMovieList(result)
                }
            }
        }.execute()
    }

    fun editMovie(movie: Movie) {
        Thread {
            movieDaoImpl.updateMovie(movie)
            getMovies()
        }.start()
    }

    fun removeMovie(movie: Movie) {
        Thread {
            movieDaoImpl.deleteMovie(movie)
            getMovies()
        }.start()
    }

    fun getMoviesOrderName() {
        object: AsyncTask<Unit, Unit, MutableList<Movie>>(){
            override fun doInBackground(vararg params: Unit?): MutableList<Movie> {
                val returnList = mutableListOf<Movie>()
                returnList.addAll(movieDaoImpl.getFilmesPorNome())
                return returnList
            }

            override fun onPostExecute(result: MutableList<Movie>?) {
                super.onPostExecute(result)
                if (result != null){
                    mainActivity.updateMovieList(result)
                }
            }
        }.execute()
    }

    fun getMoviesOrderGrade()  {
        object: AsyncTask<Unit, Unit, MutableList<Movie>>(){
            override fun doInBackground(vararg params: Unit?): MutableList<Movie> {
                val returnList = mutableListOf<Movie>()
                returnList.addAll(movieDaoImpl.getFilmesPorNota())
                return returnList
            }

            override fun onPostExecute(result: MutableList<Movie>?) {
                super.onPostExecute(result)
                if (result != null){
                    mainActivity.updateMovieList(result)
                }
            }
        }.execute()
    }
}