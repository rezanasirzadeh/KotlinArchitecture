package ir.digireza.s2_flow.retrofit.viewmodel

import ir.digireza.s2_flow.retrofit.model.ResponseGenresList
import ir.digireza.s2_flow.retrofit.model.ResponseMovies
import ir.digireza.s2_flow.retrofit.repository.MoviesRepositoryWithGenres
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MoviesViewModelWithGenres @Inject constructor(private val repository: MoviesRepositoryWithGenres) : ViewModel() {

    val moviesFlow = repository.moviesList()
    val genresFlow = repository.genresList()

    fun loadMoviesWithGenres(): Flow<List<Pair<ResponseMovies.Data, ResponseGenresList.ResponseGenresListItem>>> =
        combine(moviesFlow, genresFlow) { movieList: Response<ResponseMovies>, genreList: Response<ResponseGenresList> ->

            val movies = movieList.body()?.data
            val genre = genreList.body()!!
            return@combine movies?.zip(genre)!!
        }
}