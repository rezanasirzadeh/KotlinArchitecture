package ir.digireza.s1_paging.utils

import ir.digireza.s1_paging.di.moviesModule
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.startKoin

class MyAppKoin : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(moviesModule)
        }
    }
}