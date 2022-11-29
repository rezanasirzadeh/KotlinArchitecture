package ir.digireza.s2_rxjava.retrofit.multi

import ir.digireza.s2_rxjava.databinding.ActivityMultiApi2Binding
import ir.digireza.s2_rxjava.databinding.ActivityMultiApiBinding
import ir.digireza.s2_rxjava.retrofit.model.ResponseGenres
import ir.digireza.s2_rxjava.retrofit.model.ResponseMovies
import ir.digireza.s2_rxjava.retrofit.repository.ApiRepository
import ir.digireza.s2_rxjava.retrofit.single.MoviesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MultiApiActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMultiApi2Binding

    @Inject
    lateinit var repository: ApiRepository

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    //Other
    private val disposable by lazy { CompositeDisposable() }
    private val moviesData: MutableList<ResponseMovies.Data> = mutableListOf()
    private val genresData: MutableList<ResponseGenres.ResponseGenresItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiApi2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Init movies list
            /*moviesList.apply {
                layoutManager = LinearLayoutManager(this@MultiApiActivity)
                adapter = moviesAdapter
            }*/
            moviesList.apply {
                layoutManager = LinearLayoutManager(
                    this@MultiApiActivity, LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = moviesAdapter
            }
            //Init genres list
            genresList.apply {
                layoutManager = LinearLayoutManager(
                    this@MultiApiActivity, LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = genresAdapter
            }
            //Loading
            moviesLoading.visibility = View.VISIBLE
            //Zip - Parallel
            /*disposable.add(
                Single.zip(repository.moviesApi1(1), repository.moviesApi2(2)) { api1, api2 ->
                    api1.body()?.let { moviesData.addAll(it.data) }
                    api2.body()?.let { moviesData.addAll(it.data) }
                    moviesData
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        binding.moviesLoading.visibility = View.GONE
                        moviesAdapter.setData(response)
                    }, { err ->
                        binding.moviesLoading.visibility = View.GONE
                        Snackbar.make(binding.root, err.message.toString(), Snackbar.LENGTH_LONG).show()
                    })
            )*/

            //FlatMap - Sequence
            /*disposable.add(
                repository.moviesApi1(1)
                    .subscribeOn(Schedulers.io())
                    .flatMap { api1 ->
                        api1.body()?.let { moviesData.addAll(it.data) }
                        repository.moviesApi2(2)
                    }.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ api2 ->
                        binding.moviesLoading.visibility = View.GONE
                        api2.body()?.let { moviesData.addAll(it.data) }

                        moviesAdapter.setData(moviesData)
                    }, { err ->
                        binding.moviesLoading.visibility = View.GONE
                        Snackbar.make(binding.root, err.message.toString(), Snackbar.LENGTH_LONG).show()
                    })
            )*/

            //FlatMap - Multi api
            disposable.add(
                repository.moviesApi1(2)
                    .subscribeOn(Schedulers.io())
                    .flatMap { api1 ->
                        api1.body()?.let { moviesData.addAll(it.data) }
                        repository.genreList()
                    }.flatMap { genres ->
                        genres.body()?.let { genresData.addAll(it) }
                        repository.movieDetail(1)
                    }.map { response -> response }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        binding.moviesLoading.visibility = View.GONE
                        //List
                        moviesAdapter.setData(moviesData)
                        genresAdapter.setData(genresData)
                        //Detail
                        posterImg.load(response.body()?.poster) {
                            crossfade(true)
                            crossfade(800)
                        }
                        nameTxt.text = response.body()?.title
                    }, { err ->
                        binding.moviesLoading.visibility = View.GONE
                        Snackbar.make(binding.root, err.message.toString(), Snackbar.LENGTH_LONG).show()
                    })
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}