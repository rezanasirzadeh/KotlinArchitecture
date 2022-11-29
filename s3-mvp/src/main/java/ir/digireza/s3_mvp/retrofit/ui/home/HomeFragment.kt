package ir.digireza.s3_mvp.retrofit.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.digireza.s3_mvp.R
import ir.digireza.s3_mvp.databinding.FragmentHome2Binding
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseCategoriesList
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseFoodsList
import ir.digireza.s3_mvp.retrofit.ui.home.adapters.CategoriesAdapter
import ir.digireza.s3_mvp.retrofit.ui.home.adapters.FoodsAdapter
import ir.digireza.s3_mvp.utils.isNetworkAvailable
import ir.digireza.s3_mvp.utils.showSnackBar
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import greyfox.rxnetwork.RxNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeContracts.View {
    //Binding
    private lateinit var binding: FragmentHome2Binding

    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHome2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Call api
            presenter.callFoodRandom()
            presenter.callCategoriesList()
            presenter.callFoodsList("A")
            //Search
            searchEdt.textChanges()
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.toString().length > 1) {
                        //Call api
                        presenter.callSearchFood(it.toString())
                    }
                }
            //Filter
            filterFood()
            //Check internet
            RxNetwork.init(requireContext()).observe()
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe {
                    internetError(it.isConnected)
                }
        }
    }

    private fun filterFood() {
        val filters = listOf('A'..'Z').flatten()
        val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, filters)
        adapter.setDropDownViewResource(R.layout.item_spinner_list)
        binding.filterSpinner.adapter = adapter
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Call api
                presenter.callFoodsList(filters[p2].toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun loadFoodRandom(data: ResponseFoodsList) {
        binding.headerImg.load(data.meals?.get(0)?.strMealThumb)
    }

    override fun loadCategories(data: ResponseCategoriesList) {
        categoriesAdapter.setData(data.categories)

        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }

        categoriesAdapter.setOnItemClickListener {
            presenter.callFoodsByCategory(it.strCategory.toString())
        }
    }

    override fun loadFoodsList(data: ResponseFoodsList) {
        //Visibility mode
        binding.foodsList.visibility = View.VISIBLE
        binding.homeDisLay.visibility = View.GONE

        data.meals?.let {
            foodsAdapter.setData(it)
        }

        binding.foodsList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = foodsAdapter
        }

        foodsAdapter.setOnItemClickListener {
            val direction = HomeFragmentDirections.actionHomeToDetail(it.idMeal!!.toInt())
            findNavController().navigate(direction)
        }
    }

    override fun foodsLoadingState(isShown: Boolean) {
        binding.apply {
            if (isShown) {
                homeFoodsLoading.visibility = View.VISIBLE
                foodsList.visibility = View.GONE
            } else {
                homeFoodsLoading.visibility = View.GONE
                foodsList.visibility = View.VISIBLE
            }
        }
    }

    override fun emptyList() {
        binding.apply {
            foodsList.visibility = View.GONE
            homeDisLay.visibility = View.VISIBLE
            //Change view
            disconnectLay.disImg.setImageResource(R.drawable.box)
            disconnectLay.disTxt.text = getString(R.string.emptyList)
        }
    }

    override fun showLoading() {
        binding.homeCategoryLoading.visibility = View.VISIBLE
        binding.categoryList.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.homeCategoryLoading.visibility = View.GONE
        binding.categoryList.visibility = View.VISIBLE
    }

    override fun checkInternet(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun internetError(hasInternet: Boolean) {
        binding.apply {
            if (!hasInternet) {
                homeContent.visibility = View.GONE
                homeDisLay.visibility = View.VISIBLE
                //Change view
                disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                disconnectLay.disTxt.text = getString(R.string.checkInternet)
            } else {
                homeContent.visibility = View.VISIBLE
                homeDisLay.visibility = View.GONE
                //Call api
                //presenter.callCategoriesList()
                //presenter.callFoodsList("A")
            }
        }
    }

    override fun serverError(message: String) {
        binding.root.showSnackBar(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }
}