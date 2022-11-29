package ir.digireza.s3_mvp.room.ui.add

import ir.digireza.s3_mvp.base.BasePresenter
import ir.digireza.s3_mvp.room.data.model.NoteEntity

interface NoteContracts {

    interface View {
        fun close()
        fun loadNote(note: NoteEntity)
    }

    interface Presenter : BasePresenter {
        fun saveNote(entity: NoteEntity)
        fun detailNote(id: Int)
        fun updateNote(entity: NoteEntity)
    }
}