package ir.digireza.s1_koin

import ir.digireza.s1_koin.interfaces.bmwModule
import ir.digireza.s1_koin.qualifiers.humanModule
import ir.digireza.s1_koin.retrofit.api.networkModule
import ir.digireza.s1_koin.retrofit.di.moviesModule
import ir.digireza.s1_koin.room.di.databaseModule
import ir.digireza.s1_koin.scopes.personModule
import ir.digireza.s1_koin.simple.userModule
import ir.digireza.s1_koin.viewmodel.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinReflectAPI
import org.koin.core.context.startKoin

@KoinReflectAPI
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //Koin
        startKoin {
            //Context
            androidContext(this@MyApp)
            //Simple
            //modules(userModule)

            //Interface
            //modules(bmwModule)

            //Qualifiers
            //modules(humanModule)

            //ViewModel
            //modules(viewModelModule)

            //Room
            //modules(databaseModule, repositoryModule, roomViewModelModule)
            //modules(databaseModule)

            //Retrofit
            //modules(networkModule, moviesModule)

            //Scope
            modules(personModule)
        }
    }
}