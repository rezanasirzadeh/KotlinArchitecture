package ir.digireza.s2_flow.retrofit.adapter

import ir.digireza.s2_flow.databinding.ItemGenresBinding
import ir.digireza.s2_flow.retrofit.model.ResponseGenresList.ResponseGenresListItem
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private lateinit var binding: ItemGenresBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemGenresBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseGenresListItem) {
            //InitView
            binding.apply {
                //Set text
                genreTitle.text = item.name
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseGenresListItem>() {
        override fun areItemsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}