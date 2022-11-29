package ir.digireza.s1_hilt.room

import ir.digireza.s1_hilt.R
import ir.digireza.s1_hilt.databinding.ActivityNoteBinding
import ir.digireza.s1_hilt.room.db.NoteModel
import ir.digireza.s1_hilt.room.repository.DbRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var notesAdapter: NotesAdapter

    @Inject
    lateinit var entity: NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save
            btnSave.setOnClickListener {
                entity.id = 0
                entity.title = titleEdt.text.toString()
                repository.saveNote(entity)
                titleEdt.setText("")
                notesAdapter.differ.submitList(repository.getAllNotes())
            }
            //Get data
            notesAdapter.differ.submitList(repository.getAllNotes())
            notesList.apply {
                layoutManager = LinearLayoutManager(this@NoteActivity)
                adapter = notesAdapter
            }
            //Adapter clicked
            notesAdapter.setOnItemClickListener {
                Toast.makeText(this@NoteActivity, "${it.id} ${it.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}