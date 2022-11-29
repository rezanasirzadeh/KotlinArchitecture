package ir.digireza.s1_hilt.retrofit

import ir.digireza.s1_hilt.databinding.ItemMoviesBinding
import ir.digireza.s1_hilt.retrofit.model.ResponseMoviesList
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoviesAdapter @Inject constructor(@ApplicationContext context: Context)
    : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemMoviesBinding.inflate(inflater, parent, false)
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
        fun bind(item: ResponseMoviesList.Data) {
            //InitView
            binding.apply {
                //Set text
                movieName.text = item.title
                movieImg.load(item.poster){
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
        override fun areItemsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}