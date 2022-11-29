package ir.digireza.s2_flow.retrofit

import ir.digireza.s2_flow.databinding.ActivityRetrofitBinding
import ir.digireza.s2_flow.retrofit.adapter.MoviesAdapter
import ir.digireza.s2_flow.retrofit.viewmodel.MovieViewModel
import ir.digireza.s2_flow.retrofit.viewmodel.MovieViewModelParallel
import ir.digireza.s2_flow.retrofit.viewmodel.MovieViewModelSeries
import ir.digireza.s2_flow.utils.MyResponse
import ir.digireza.s2_flow.utils.isVisible
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRetrofitBinding

    //val viewModel: MovieViewModel by viewModels()
    //val viewModel : MovieViewModelSeries by viewModels()
    val viewModel: MovieViewModelParallel by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Load movies
            viewModel.loadMovies()
            viewModel.moviesList.observe(this@RetrofitActivity) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        movieLoading.isVisible(true, moviesList)
                    }
                    MyResponse.Status.SUCCESS -> {
                        movieLoading.isVisible(false, moviesList)
                        moviesAdapter.differ.submitList(it.data)
                        //RecyclerView
                        moviesList.apply {
                            layoutManager = LinearLayoutManager(this@RetrofitActivity)
                            adapter = moviesAdapter
                        }
                    }
                    MyResponse.Status.ERROR -> {
                        movieLoading.isVisible(false, moviesList)
                        Toast.makeText(this@RetrofitActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}