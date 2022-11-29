package ir.digireza.s1_livedata.room.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.digireza.s1_livedata.databinding.FragmentAddNoteBinding
import ir.digireza.s1_livedata.room.NotesActivity
import ir.digireza.s1_livedata.room.db.NoteEntity
import androidx.navigation.fragment.findNavController

class AddNoteFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentAddNoteBinding

    //Other
    private lateinit var note: NoteEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Save note
            btnSave.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descEdt.text.toString()

                note = NoteEntity(0, title, desc)

                (activity as NotesActivity).noteDb.noteDao().saveNote(note)
                findNavController().popBackStack()
            }
        }
    }

}