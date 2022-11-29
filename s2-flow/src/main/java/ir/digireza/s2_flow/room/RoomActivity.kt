package ir.digireza.s2_flow.room

import ir.digireza.s2_flow.databinding.ActivityRoomBinding
import ir.digireza.s2_flow.room.adapter.NotesAdapter
import ir.digireza.s2_flow.room.db.NoteModel
import ir.digireza.s2_flow.room.viewmodel.NoteViewModel
import ir.digireza.s2_flow.utils.MyResponse
import ir.digireza.s2_flow.utils.isVisible
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRoomBinding

    //Inject
    @Inject
    lateinit var notesAdapter: NotesAdapter

    @Inject
    lateinit var entity: NoteModel

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save
            btnSave.setOnClickListener {
                entity.id = 0
                entity.title = titleEdt.text.toString()
                viewModel.saveNote(entity)
                titleEdt.setText("")
            }
            //load data
            viewModel.getAllNotes()

            viewModel.notesList.observe(this@RoomActivity) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        notesLoading.isVisible(true, noteContainer)
                    }
                    MyResponse.Status.SUCCESS -> {
                        notesLoading.isVisible(false, noteContainer)
                        notesAdapter.setData(it.data)
                        //RecyclerView
                        notesList.apply {
                            layoutManager = LinearLayoutManager(this@RoomActivity)
                            adapter = notesAdapter
                        }
                    }
                    MyResponse.Status.ERROR -> {
                        notesLoading.isVisible(false, noteContainer)
                        Toast.makeText(this@RoomActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            //Delete
            notesAdapter.setOnItemClickListener {
                viewModel.deleteNote(it)
            }
        }
    }
}