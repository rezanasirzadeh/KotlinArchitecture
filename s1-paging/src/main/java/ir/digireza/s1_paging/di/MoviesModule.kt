package ir.digireza.s1_paging.di

import ir.digireza.s1_paging.adapter.MoviesAdapterKoin
import ir.digireza.s1_paging.api.*
import ir.digireza.s1_paging.repository.MoviesRepositoryKoin
import ir.digireza.s1_paging.viewmodel.MoviesViewModelKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    single { baseUrl }
    single { networkTime }
    single { provideGson() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get(), get()) }

    single { MoviesRepositoryKoin(get()) }
    viewModel { MoviesViewModelKoin(get()) }
    single { MoviesAdapterKoin() }
}