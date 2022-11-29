package ir.digireza.s1_room

import ir.digireza.s1_room.databinding.ActivityMainBinding
import ir.digireza.s1_room.db.UserDatabase
import ir.digireza.s1_room.utils.Constants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Database
    private val userDb: UserDatabase by lazy {
        Room.databaseBuilder(this, UserDatabase::class.java, Constants.USER_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private val usersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Add new user
            addUserBtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddUserActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //Show Items
        checkItems()
    }

    private fun checkItems() {
        binding.apply {
            if (userDb.dao().getAllUser().isNotEmpty()) {
                usersList.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
                //Setup recycler
                usersAdapter.differ.submitList(userDb.dao().getAllUser())
                setupRecyclerView()

            } else {
                usersList.visibility = View.GONE
                emptyText.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = usersAdapter
        }
    }
}