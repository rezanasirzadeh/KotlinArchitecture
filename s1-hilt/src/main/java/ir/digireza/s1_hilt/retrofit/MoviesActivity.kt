package ir.digireza.s1_hilt.retrofit

import ir.digireza.s1_hilt.R
import ir.digireza.s1_hilt.databinding.ActivityMoviesBinding
import ir.digireza.s1_hilt.retrofit.model.ResponseMoviesList
import ir.digireza.s1_hilt.retrofit.repository.ApiRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMoviesBinding

    @Inject
    lateinit var repository: ApiRepository

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    //Other
    private val TAG = "RetrofitHiltLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //View loading
            movieLoading.visibility = View.VISIBLE
            //Call all movies api
            repository.getAllMovies().enqueue(object : Callback<ResponseMoviesList> {
                override fun onResponse(call: Call<ResponseMoviesList>, response: Response<ResponseMoviesList>) {
                    //Gone loading
                    movieLoading.visibility = View.GONE
                    //Show data
                    if (response.isSuccessful) {
                        response.body()?.let { itBody ->
                            itBody.data?.let { itData ->
                                if (itData.isNotEmpty()) {
                                    moviesAdapter.differ.submitList(itData)
                                    moviesList.apply {
                                        layoutManager = LinearLayoutManager(this@MoviesActivity)
                                        adapter = moviesAdapter
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    movieLoading.visibility = View.GONE
                    Log.e(TAG, t.message.toString())
                }
            })
        }
    }
}