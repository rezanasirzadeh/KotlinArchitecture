package ir.digireza.s2_rxjava.retrofit.single

import ir.digireza.s2_rxjava.databinding.ActivityRetrofitBinding
import ir.digireza.s2_rxjava.retrofit.model.ResponseMovies
import ir.digireza.s2_rxjava.retrofit.repository.ApiRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRetrofitBinding

    @Inject
    lateinit var repository: ApiRepository

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    //Other
    private val disposable by lazy { CompositeDisposable() }
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init views
        binding.apply {
            //Init RecyclerView
            moviesList.apply {
                layoutManager = LinearLayoutManager(this@RetrofitActivity)
                adapter = moviesAdapter
            }
            //Load data  - Method 1
            /*disposable.add(
                searchEdt.textChanges()
                    .skipInitialValue()
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        moviesLoading.visibility = View.VISIBLE
                        repository.searchMovies(it.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(moviesObserver())
                    }
            )*/

            //Load data - Method 2
            disposable.add(
                searchEdt.textChanges()
                    .skipInitialValue()
                    .debounce(1000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { text ->
                        if (text.isNotEmpty())
                        moviesLoading.visibility = View.VISIBLE
                        publishSubject.onNext(text.toString())
                    }
            )

            disposable.add(publishSubject.debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(Function {
                    return@Function repository.searchMovies(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                }).subscribeWith(moviesObserver2())
            )
        }
    }

    private fun moviesObserver(): DisposableObserver<Response<ResponseMovies>> {
        return object : DisposableObserver<Response<ResponseMovies>>() {
            override fun onNext(t: Response<ResponseMovies>) {
                if (t.isSuccessful) {
                    t.body()?.let { itBody ->
                        moviesAdapter.setData(itBody.data)
                    }
                }
            }

            override fun onError(e: Throwable) {
                Snackbar.make(binding.root, e.message.toString(), Snackbar.LENGTH_LONG).show()
            }

            override fun onComplete() {
                binding.moviesLoading.visibility = View.GONE
            }
        }
    }

    private fun moviesObserver2(): DisposableObserver<Response<ResponseMovies>> {
        return object : DisposableObserver<Response<ResponseMovies>>() {
            override fun onNext(t: Response<ResponseMovies>) {
                if (t.isSuccessful) {
                    t.body()?.let { itBody ->
                        moviesAdapter.setData(itBody.data)
                    }
                    binding.moviesLoading.visibility = View.GONE
                }
            }

            override fun onError(e: Throwable) {
                Snackbar.make(binding.root, e.message.toString(), Snackbar.LENGTH_LONG).show()
                binding.moviesLoading.visibility = View.GONE
            }

            override fun onComplete() {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}