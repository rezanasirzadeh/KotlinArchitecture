package ir.digireza.s4_mvvm.food_app.utils.di

import ir.digireza.s4_mvvm.food_app.data.database.FoodDatabase
import ir.digireza.s4_mvvm.food_app.data.database.FoodEntity
import ir.digireza.s4_mvvm.food_app.utils.FOOD_DB_DATABASE
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, FoodDatabase::class.java, FOOD_DB_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: FoodDatabase) = db.foodDao()

    @Provides
    @Singleton
    fun provideEntity() = FoodEntity()
}