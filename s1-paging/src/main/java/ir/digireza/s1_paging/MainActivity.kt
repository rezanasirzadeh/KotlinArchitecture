package ir.digireza.s1_paging

import ir.digireza.s1_paging.adapter.LoadMoreAdapter
import ir.digireza.s1_paging.adapter.MoviesAdapter
import ir.digireza.s1_paging.adapter.MoviesAdapterKoin
import ir.digireza.s1_paging.databinding.ActivityMainBinding
import ir.digireza.s1_paging.viewmodel.MoviesViewModel
import ir.digireza.s1_paging.viewmodel.MoviesViewModelKoin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import javax.inject.Inject

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Hilt
/*    //Inject
    private val viewModel: MoviesViewModel by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter*/

    //Koin
    private val viewModel: MoviesViewModelKoin by inject()
    private val moviesAdapter: MoviesAdapterKoin by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Load data
            lifecycleScope.launchWhenCreated {
                viewModel.moviesList.collect {
                    moviesAdapter.submitData(it)
                }
            }
            //Loading
            lifecycleScope.launchWhenCreated {
                moviesAdapter.loadStateFlow.collect {
                    val state = it.refresh

                    moviesLoading.isVisible = state is LoadState.Loading
                }
            }
            //RecyclerView
            moviesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = moviesAdapter
            }
            //SwipeRefresh
            movieSwipe.setOnRefreshListener {
                movieSwipe.isRefreshing = false
                moviesAdapter.refresh()
            }
            //Load more
            moviesRecycler.adapter = moviesAdapter.withLoadStateFooter(
                LoadMoreAdapter { moviesAdapter.retry() }
            )
        }
    }
}