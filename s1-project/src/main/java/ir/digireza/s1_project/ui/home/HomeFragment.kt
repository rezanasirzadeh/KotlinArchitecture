package ir.digireza.s1_project.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.digireza.s1_project.databinding.FragmentHomeBinding
import ir.digireza.s1_project.ui.home.adapters.GenresAdapter
import ir.digireza.s1_project.ui.home.adapters.LastMoviesAdapter
import ir.digireza.s1_project.ui.home.adapters.TopMoviesAdapter
import ir.digireza.s1_project.utils.initRecycler
import ir.digireza.s1_project.utils.showInvisible
import ir.digireza.s1_project.viewmodel.HomeViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    //Other
    private val viewModel: HomeViewModel by viewModels()
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Call api
        viewModel.loadTopMoviesList(3)
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Get top movies
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)
                //RecyclerView
                topMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter
                )
                //Indicator
                pagerHelper.attachToRecyclerView(topMoviesRecycler)
                topMoviesIndicator.attachToRecyclerView(topMoviesRecycler, pagerHelper)
            }
            //Get genres
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)
                genresRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter
                )
            }
            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.data)
                //RecyclerView
                lastMoviesRecycler.initRecycler(LinearLayoutManager(requireContext()), lastMoviesAdapter)
            }
            //Click
            lastMoviesAdapter.setOnItemClickListener {
                val direction = HomeFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInvisible(true)
                    moviesScrollLay.showInvisible(false)
                } else {
                    moviesLoading.showInvisible(false)
                    moviesScrollLay.showInvisible(true)
                }
            }
        }
    }
}