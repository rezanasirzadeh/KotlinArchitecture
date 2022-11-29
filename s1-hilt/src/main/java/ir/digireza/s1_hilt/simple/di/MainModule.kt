package ir.digireza.s1_hilt.simple.di

import ir.digireza.s1_hilt.NAMED_SITE_NAME
import ir.digireza.s1_hilt.NAMED_USERNAME
import ir.digireza.s1_hilt.simple.di.qualifier.SiteName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    @Named(NAMED_USERNAME)
    fun provideUserName(): String = "Reza Nasirzadeh"

    @Provides
    //@SiteName
    @Named(NAMED_SITE_NAME)
    fun provideSiteName(): String {
        return "digireza.ir"
    }
}