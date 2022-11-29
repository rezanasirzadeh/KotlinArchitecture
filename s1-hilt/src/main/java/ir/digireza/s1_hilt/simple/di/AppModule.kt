package ir.digireza.s1_hilt.simple.di

import ir.digireza.s1_hilt.NAMED_APP_INFO
import ir.digireza.s1_hilt.R
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named(NAMED_APP_INFO)
    fun provideTextFromString(@ApplicationContext context: Context): String {
        return context.getString(R.string.textInfo)
    }
}