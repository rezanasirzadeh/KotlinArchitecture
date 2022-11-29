package ir.digireza.s1_project.repository

import ir.digireza.s1_project.api.ApiServices
import ir.digireza.s1_project.db.MovieEntity
import ir.digireza.s1_project.db.MoviesDao
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices, private val dao: MoviesDao) {
    //Api
    suspend fun detailMovie(id: Int) = api.detailMovie(id)

    //Database
    suspend fun insertMovie(entity: MovieEntity) = dao.insertMovie(entity)
    suspend fun deleteMovie(entity: MovieEntity) = dao.deleteMovie(entity)
    suspend fun existsMovie(id: Int) = dao.existsMovie(id)
}