package ir.digireza.s1_paging.adapter

import ir.digireza.s1_paging.databinding.ItemMoviesBinding
import ir.digireza.s1_paging.model.ResponseMovies.Data
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import javax.inject.Inject

class MoviesAdapterKoin : PagingDataAdapter<Data, MoviesAdapterKoin.ViewHolder>(differCallback) {

    private lateinit var binding: ItemMoviesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapterKoin.ViewHolder {
        binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesAdapterKoin.ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Data) {
            binding.apply {
                itemMovieName.text = item.title
                itemMoviesImg.load(item.poster) {
                    crossfade(true)
                    crossfade(500)
                }
            }
        }
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}