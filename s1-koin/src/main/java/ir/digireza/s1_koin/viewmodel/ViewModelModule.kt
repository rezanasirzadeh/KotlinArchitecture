package ir.digireza.s1_koin.viewmodel

import ir.digireza.s1_koin.R
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModule = module {
    factory { UserRepository(androidContext().getString(R.string.family)) } bind UserInfo::class

    viewModel { UserViewModel(get()) }
}