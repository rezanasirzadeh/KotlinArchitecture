package ir.digireza.s1_koin.room

import ir.digireza.s1_koin.databinding.ItemNotesBinding
import ir.digireza.s1_koin.room.db.NoteModel
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter  : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

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
        fun bind(item: NoteModel) {
            binding.apply {
                titleTxt.text = "${item.id} : ${item.title}"
            }
        }
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