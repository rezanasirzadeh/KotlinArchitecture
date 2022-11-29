package ir.digireza.s1_koin.retrofit

import ir.digireza.s1_koin.databinding.ActivityRetrofitBinding
import ir.digireza.s1_koin.retrofit.viewmodel.MoviesViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject

class RetrofitActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRetrofitBinding

    //Inject
    private val viewModel: MoviesViewModel by inject()

    //Other
    private val moviesAdapter by lazy { MoviesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Loading
            viewModel.loading.observe(this@RetrofitActivity) {
                if (it) {
                    moviesLoader.visibility = View.VISIBLE
                } else {
                    moviesLoader.visibility = View.GONE
                }
            }
            //Load movie
            viewModel.loadMovies()
            viewModel.moviesList.observe(this@RetrofitActivity) {
                moviesAdapter.differ.submitList(it.data)

                moviesRecycler.apply {
                    layoutManager = LinearLayoutManager(this@RetrofitActivity)
                    adapter = moviesAdapter
                }
            }
        }
    }
}