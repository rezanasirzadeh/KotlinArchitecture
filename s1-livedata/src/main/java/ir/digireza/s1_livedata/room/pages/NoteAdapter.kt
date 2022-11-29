package ir.digireza.s1_livedata.room.pages

import ir.digireza.s1_livedata.databinding.ItemNotesBinding
import ir.digireza.s1_livedata.room.db.NoteEntity
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(differ.currentList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: NoteEntity) {
            binding.apply {
                titleTxt.text = item.title
                descTxt.text = item.desc
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}