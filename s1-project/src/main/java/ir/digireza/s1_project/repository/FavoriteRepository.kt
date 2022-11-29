package ir.digireza.s1_project.repository

import ir.digireza.s1_project.db.MoviesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: MoviesDao) {

    fun allFavoriteList() = dao.getAllMovies()
}