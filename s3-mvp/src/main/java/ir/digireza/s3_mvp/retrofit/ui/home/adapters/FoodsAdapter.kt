package ir.digireza.s3_mvp.retrofit.ui.home.adapters

import ir.digireza.s3_mvp.databinding.ItemFoodsBinding
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseFoodsList.*
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import javax.inject.Inject

class FoodsAdapter @Inject constructor() : RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    private lateinit var binding: ItemFoodsBinding
    private var moviesList = emptyList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun bind(item: Meal) {
            binding.apply {
                itemFoodsImg.load(item.strMealThumb) {
                    crossfade(true)
                    crossfade(500)
                }
                itemFoodsTitle.text = item.strMeal
                //Category
                if (item.strCategory.isNullOrEmpty().not()) {
                    itemFoodsCategory.text = item.strCategory
                    itemFoodsCategory.visibility = View.VISIBLE
                } else {
                    itemFoodsCategory.visibility = View.GONE
                }
                //Area
                if (item.strArea.isNullOrEmpty().not()) {
                    itemFoodsArea.text = item.strArea
                    itemFoodsArea.visibility = View.VISIBLE
                } else {
                    itemFoodsArea.visibility = View.GONE
                }
                //Source
                if (item.strSource != null) {
                    itemFoodsCount.visibility = View.VISIBLE
                } else {
                    itemFoodsCount.visibility = View.GONE
                }

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Meal>) {
        val moviesDiffUtil = MoviesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<Meal>, private val newItem: List<Meal>) : DiffUtil.Callback() {
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