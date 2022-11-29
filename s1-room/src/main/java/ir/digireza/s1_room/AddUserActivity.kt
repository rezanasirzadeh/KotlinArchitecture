package ir.digireza.s1_room

import ir.digireza.s1_room.databinding.ActivityAddUserBinding
import ir.digireza.s1_room.db.UserDatabase
import ir.digireza.s1_room.db.UserEntity
import ir.digireza.s1_room.utils.Constants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar

class AddUserActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityAddUserBinding

    //Database
    private val userDb: UserDatabase by lazy {
        Room.databaseBuilder(this, UserDatabase::class.java, Constants.USER_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var user: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save
            saveBtn.setOnClickListener {
                val name = nameEdt.text.toString()
                val age = ageEdt.text.toString()

                if (name.isNotEmpty() || age.isNotEmpty()) {
                    user = UserEntity(0, name, age.toInt())
                    userDb.dao().insertUser(user)
                    finish()
                } else {
                    Snackbar.make(it, "Name and age cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}