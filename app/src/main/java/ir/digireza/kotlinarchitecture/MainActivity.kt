package ir.digireza.kotlinarchitecture

import ir.digireza.kotlinarchitecture.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Set data
        userAdapter.differ.submitList(loadData())

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun loadData(): MutableList<UserModel> {
        val users: MutableList<UserModel> = mutableListOf()
        users.add(UserModel(1, "Reza", 29))
        users.add(UserModel(2, "Ali", 35))
        users.add(UserModel(3, "Maryam", 24))
        users.add(UserModel(4, "Nooshin", 27))
        users.add(UserModel(5, "Mohsen", 14))
        users.add(UserModel(6, "Sahar", 32))
        users.add(UserModel(7, "Reyhane", 19))
        users.add(UserModel(8, "Naser", 45))
        users.add(UserModel(9, "Peyman", 39))
        users.add(UserModel(10, "Reza", 34))
        users.add(UserModel(11, "Elnaz", 30))

        return users
    }
}