package ir.digireza.s4_mvvm.food_app.ui.list

import ir.digireza.s4_mvvm.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.digireza.s4_mvvm.databinding.FragmentFoodsListBinding
import ir.digireza.s4_mvvm.food_app.ui.list.adapters.CategoriesAdapter
import ir.digireza.s4_mvvm.food_app.ui.list.adapters.FoodsAdapter
import ir.digireza.s4_mvvm.food_app.utils.*
import ir.digireza.s4_mvvm.food_app.viewmodel.FoodsListViewModel
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodsListFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    @Inject
    lateinit var connection: CheckConnection

    //Other
    private val viewModel: FoodsListViewModel by viewModels()

    enum class PageState { EMPTY, NETWORK, NONE }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodsListBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding?.apply {
            //Random food
            //viewModel.loadFoodRandom()
            viewModel.randomFoodData.observe(viewLifecycleOwner) {
                it[0].let { meal ->
                    headerImg.load(meal.strMealThumb) {
                        crossfade(true)
                        crossfade(500)
                    }
                }
            }
            //Filters
            viewModel.loadFilterList()
            viewModel.filtersListData.observe(viewLifecycleOwner) {
                filterSpinner.setupListWithAdapter(it) { letter ->
                    viewModel.loadFoodsList(letter)
                }
            }
            //Category
            //viewModel.loadCategoriesList()
            viewModel.categoriesListData.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        homeCategoryLoading.isVisible(true, categoryList)
                    }
                    MyResponse.Status.SUCCESS -> {
                        homeCategoryLoading.isVisible(false, categoryList)
                        categoriesAdapter.setData(it.data!!.categories)
                        categoryList.setupRecyclerView(
                            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                            categoriesAdapter
                        )
                    }
                    MyResponse.Status.ERROR -> {
                        homeCategoryLoading.isVisible(false, categoryList)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            categoriesAdapter.setOnItemClickListener {
                viewModel.loadFoodByCategory(it.strCategory.toString())
            }
            //Foods
            viewModel.loadFoodsList("A")
            viewModel.foodsListData.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        homeFoodsLoading.isVisible(true, foodsList)
                    }
                    MyResponse.Status.SUCCESS -> {
                        homeFoodsLoading.isVisible(false, foodsList)
                        if (it.data!!.meals != null) {
                            if (it.data.meals!!.isNotEmpty()) {
                                checkConnectionOrEmpty(false, PageState.NONE)
                                foodsAdapter.setData(it.data.meals)
                                foodsList.setupRecyclerView(
                                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                                    foodsAdapter
                                )
                            }
                        } else {
                            checkConnectionOrEmpty(true, PageState.EMPTY)
                        }
                    }
                    MyResponse.Status.ERROR -> {
                        homeFoodsLoading.isVisible(false, foodsList)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            foodsAdapter.setOnItemClickListener {
                val direction = FoodsListFragmentDirections.actionListToDetail(it.idMeal!!.toInt())
                findNavController().navigate(direction)
            }
            //Search
            searchEdt.addTextChangedListener {
                if (it.toString().length > 2) {
                    viewModel.loadFoodBySearch(it.toString())
                }
            }
            //Internet
            connection.observe(viewLifecycleOwner) {
                if (it) {
                    checkConnectionOrEmpty(false, PageState.NONE)
                } else {
                    checkConnectionOrEmpty(true, PageState.NETWORK)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

    private fun checkConnectionOrEmpty(isShownError: Boolean, state: PageState) {
        binding?.apply {
            if (isShownError) {
                homeDisLay.isVisible(true, homeContent)
                when (state) {
                    PageState.EMPTY -> {
                        statusLay.disImg.setImageResource(R.drawable.box)
                        statusLay.disTxt.text = getString(R.string.emptyList)
                    }
                    PageState.NETWORK -> {
                        statusLay.disImg.setImageResource(R.drawable.disconnect)
                        statusLay.disTxt.text = getString(R.string.checkInternet)
                    }
                    else -> {}
                }
            } else {
                homeDisLay.isVisible(false, homeContent)
            }
        }
    }
}