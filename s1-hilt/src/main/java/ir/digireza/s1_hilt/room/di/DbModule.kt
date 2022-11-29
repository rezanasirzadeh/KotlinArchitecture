package ir.digireza.s1_hilt.room.di

import ir.digireza.s1_hilt.NOTE_DATABASE
import ir.digireza.s1_hilt.room.db.NoteDatabase
import ir.digireza.s1_hilt.room.db.NoteModel
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
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NoteDatabase::class.java, NOTE_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: NoteDatabase) = db.noteDao()

    @Provides
    fun provideEntity() = NoteModel()
}