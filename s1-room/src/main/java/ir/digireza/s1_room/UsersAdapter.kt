package ir.digireza.s1_room

import ir.digireza.s1_room.databinding.ItemUserBinding
import ir.digireza.s1_room.db.UserEntity
import ir.digireza.s1_room.utils.Constants
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private lateinit var binding: ItemUserBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemUserBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: UserEntity) {
            //InitView
            binding.apply {
                //Set text
                itemUserInfo.text = "${item.userId} - ${item.userName} ${item.userAge}"
                //Click
                root.setOnClickListener {
                    val intent = Intent(context, UpdateUserActivity::class.java)
                    intent.putExtra(Constants.BUNDLE_USER_ID, item.userId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}