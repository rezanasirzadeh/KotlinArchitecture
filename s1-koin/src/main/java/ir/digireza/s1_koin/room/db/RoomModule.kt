package ir.digireza.s1_koin.room.db

import ir.digireza.s1_koin.NOTE_DATABASE
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db: NoteDatabase) = db.noteDao()

/*
val roomModule = module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }
}*/
