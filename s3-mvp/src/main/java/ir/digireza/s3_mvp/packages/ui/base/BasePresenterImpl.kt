package ir.digireza.s3_mvp.packages.ui.base

import androidx.annotation.NonNull
import io.reactivex.rxjava3.disposables.Disposable

class BasePresenterImpl : BasePresenter {

    @NonNull
    var disposable: Disposable? = null

    override fun onStop() {
        disposable?.let {
            it.dispose()
        }
    }
}