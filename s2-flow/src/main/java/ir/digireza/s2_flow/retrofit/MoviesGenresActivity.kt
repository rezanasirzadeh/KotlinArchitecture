package ir.digireza.s2_flow.retrofit

import ir.digireza.s2_flow.databinding.ActivityMoviesGenresBinding
import ir.digireza.s2_flow.retrofit.adapter.GenresAdapter
import ir.digireza.s2_flow.retrofit.adapter.MoviesAdapter
import ir.digireza.s2_flow.retrofit.viewmodel.MoviesViewModelWithGenres
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@AndroidEntryPoint
class MoviesGenresActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMoviesGenresBinding

    val viewModel: MoviesViewModelWithGenres by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesGenresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Load data
            lifecycleScope.launchWhenCreated {
                viewModel.loadMoviesWithGenres()
                    .onStart { movieLoading.visibility = View.VISIBLE }
                    .onCompletion { movieLoading.visibility = View.GONE }
                    .collect {
                        val (movies, genres) = it.unzip()
                        //Movies
                        moviesAdapter.differ.submitList(movies)
                        moviesList.apply {
                            layoutManager = LinearLayoutManager(this@MoviesGenresActivity)
                            adapter = moviesAdapter
                        }
                        //genres
                        genresAdapter.differ.submitList(genres)
                        genresList.apply {
                            layoutManager = LinearLayoutManager(
                                this@MoviesGenresActivity,
                                LinearLayoutManager.HORIZONTAL, false
                            )
                            adapter = genresAdapter
                        }
                    }
            }
        }
    }
}