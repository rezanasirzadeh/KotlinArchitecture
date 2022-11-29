package ir.digireza.kotlinarchitecture

import ir.digireza.kotlinarchitecture.databinding.ItemUserBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var binding: ItemUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: UserModel) {
            binding.itemUserInfo.text = "${item.name} ${item.age}"
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}