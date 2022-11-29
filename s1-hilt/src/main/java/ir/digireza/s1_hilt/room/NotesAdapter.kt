package ir.digireza.s1_hilt.room

import ir.digireza.s1_hilt.databinding.ItemNotesBinding
import ir.digireza.s1_hilt.room.db.NoteModel
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(item: NoteModel) {
            binding.apply {
                titleTxt.text = "${item.id} : ${item.title}"
                //Click
                root.setOnClickListener {
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

    private val differCallback = object : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}