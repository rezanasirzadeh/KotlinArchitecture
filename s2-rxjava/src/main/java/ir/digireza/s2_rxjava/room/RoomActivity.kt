package ir.digireza.s2_rxjava.room

import ir.digireza.s2_rxjava.databinding.ActivityRoomBinding
import ir.digireza.s2_rxjava.room.db.NoteDao
import ir.digireza.s2_rxjava.room.db.NoteEntity
import ir.digireza.s2_rxjava.room.repository.DbRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRoomBinding

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var dao: NoteDao

    @Inject
    lateinit var entity: NoteEntity

    //Other
    private val disposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init views
        binding.apply {
            //Save
            disposable.add(submit.clicks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //Fill entity
                    entity = NoteEntity(0, titleEdt.text.toString())
                    titleEdt.setText("")
                    //Save observable
                    //dao.saveNote(entity)
                    repository.saveNote(entity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Snackbar.make(binding.root, "Note Saved :)", Snackbar.LENGTH_SHORT).show()
                        }
                })
            //Ged data
            disposable.add(
                //dao.getAllNotes()
                repository.getAllNotes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        noteAdapter.setData(data)
                        notesList.apply {
                            layoutManager = LinearLayoutManager(this@RoomActivity)
                            adapter = noteAdapter
                        }
                    }, { err ->
                        Snackbar.make(binding.root, err.message.toString(), Snackbar.LENGTH_SHORT).show()
                    })
            )
            //Delete
            noteAdapter.setOnItemClickListener {
                val entity = NoteEntity(it.id, it.title)
                disposable.add(
                    //dao.deleteNote(entity)
                    repository.deleteNote(entity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Snackbar.make(binding.root, "Item deleted", Snackbar.LENGTH_SHORT).show()
                        })
            }
        }
    }
}