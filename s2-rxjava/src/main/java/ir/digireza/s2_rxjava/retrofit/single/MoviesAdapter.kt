package ir.digireza.s2_rxjava.retrofit.single

import ir.digireza.s2_rxjava.databinding.ItemMoviesBinding
import ir.digireza.s2_rxjava.retrofit.model.ResponseMovies.*
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding
    private var moviesList = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun bind(item: Data) {
            binding.apply {
                movieName.text = item.title
                movieImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    fun setData(data: List<Data>) {
        val moviesDiffUtil = MoviesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<Data>, private val newItem: List<Data>) : DiffUtil.Callback() {
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