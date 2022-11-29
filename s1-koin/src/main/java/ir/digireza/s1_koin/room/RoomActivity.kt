package ir.digireza.s1_koin.room

import ir.digireza.s1_koin.databinding.ActivityRoomBinding
import ir.digireza.s1_koin.room.db.NoteModel
import ir.digireza.s1_koin.room.viewmodel.RoomViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject

class RoomActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRoomBinding

    //Inject
    private val viewModel: RoomViewModel by inject()

    //Other
    private val noteAdapter by lazy { NoteAdapter() }
    private val note by lazy { NoteModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save
            btnSave.setOnClickListener {
                val title = titleEdt.text.toString()
                note.id = 0
                note.title = title
                viewModel.saveNote(note)

                titleEdt.setText("")
            }
            //Load
            viewModel.notesList.observe(this@RoomActivity) {
                noteAdapter.differ.submitList(it)

                notesList.apply {
                    layoutManager = LinearLayoutManager(this@RoomActivity)
                    adapter = noteAdapter
                }
            }
        }
    }
}