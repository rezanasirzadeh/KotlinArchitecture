package ir.digireza.s2_flow.room.adapter

import ir.digireza.s2_flow.databinding.ItemNotesBinding
import ir.digireza.s2_flow.room.db.NoteModel
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesAdapter @Inject constructor() : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding
    private var notesList = emptyList<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        holder.setData(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(item: NoteModel) {
            binding.apply {
                titleTxt.text = "${item.id} : ${item.title}"
                //Click
                deleteImg.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((NoteModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (NoteModel) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<NoteModel>?) {
        val moviesDiffUtil = NotesDiffUtils(notesList, data!!)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        notesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NotesDiffUtils(private val oldItem: List<NoteModel>, private val newItem: List<NoteModel>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}