package ir.digireza.s1_paging.paging

import ir.digireza.s1_paging.model.ResponseMovies
import ir.digireza.s1_paging.repository.MoviesRepository
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val repository: MoviesRepository) :
    PagingSource<Int, ResponseMovies.Data>() {

    override fun getRefreshKey(state: PagingState<Int, ResponseMovies.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseMovies.Data> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllMovies(currentPage)
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<ResponseMovies.Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}