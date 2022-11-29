package ir.digireza.s3_mvp.retrofit.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.digireza.s3_mvp.databinding.FragmentFavoriteBinding
import ir.digireza.s3_mvp.retrofit.data.database.FoodEntity
import ir.digireza.s3_mvp.retrofit.ui.home.HomeFragmentDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteContracts.View {
    //Binding
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var presenter: FavoritePresenter

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Load data from database
        presenter.loadAllFood()
    }

    override fun showAllFoods(list: MutableList<FoodEntity>) {
        favoriteAdapter.setData(list)

        binding.favoriteList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnItemClickListener {
            val direction = HomeFragmentDirections.actionHomeToDetail(it.id)
            findNavController().navigate(direction)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}