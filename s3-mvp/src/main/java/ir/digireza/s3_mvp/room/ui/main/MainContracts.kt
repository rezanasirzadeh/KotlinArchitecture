package ir.digireza.s3_mvp.room.ui.main

import ir.digireza.s3_mvp.base.BasePresenter
import ir.digireza.s3_mvp.room.data.model.NoteEntity
import dagger.Binds
import dagger.Provides

interface MainContracts {

    interface View {
        fun showAllNotes(notes: List<NoteEntity>)
        fun showEmpty()
        fun deleteMessage()
    }

    interface Presenter : BasePresenter {
        fun loadAllNotes()
        fun deleteNote(entity: NoteEntity)
        fun filterNote(priority: String)
        fun searchNote(title: String)
    }
}