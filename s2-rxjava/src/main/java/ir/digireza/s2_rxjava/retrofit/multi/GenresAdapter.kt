package ir.digireza.s2_rxjava.retrofit.multi

import ir.digireza.s2_rxjava.databinding.ItemGenresBinding
import ir.digireza.s2_rxjava.retrofit.model.ResponseGenres.ResponseGenresItem
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private lateinit var binding: ItemGenresBinding
    private var moviesList = emptyList<ResponseGenresItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(moviesList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseGenresItem) {
            binding.apply {
                titleTxt.text = item.name
            }
        }
    }

    fun setData(data: List<ResponseGenresItem>) {
        val moviesDiffUtil = MoviesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<ResponseGenresItem>, private val newItem: List<ResponseGenresItem>) : DiffUtil.Callback() {
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