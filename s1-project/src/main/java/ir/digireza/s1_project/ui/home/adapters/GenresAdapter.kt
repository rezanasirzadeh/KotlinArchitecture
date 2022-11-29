package ir.digireza.s1_project.ui.home.adapters

import ir.digireza.s1_project.databinding.ItemHomeGenreListBinding
import ir.digireza.s1_project.databinding.ItemHomeMoviesTopBinding
import ir.digireza.s1_project.models.home.ResponseGenresList
import ir.digireza.s1_project.models.home.ResponseGenresList.*
import ir.digireza.s1_project.models.home.ResponseMoviesList.*
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomeGenreListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.ViewHolder {
        binding = ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: GenresAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseGenresListItem) {
            binding.apply {
                nameTxt.text = item.name
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