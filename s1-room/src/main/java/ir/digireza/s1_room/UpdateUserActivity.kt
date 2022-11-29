package ir.digireza.s1_room

import ir.digireza.s1_room.databinding.ActivityUpdateUserBinding
import ir.digireza.s1_room.db.UserDatabase
import ir.digireza.s1_room.db.UserEntity
import ir.digireza.s1_room.utils.Constants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar

class UpdateUserActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityUpdateUserBinding

    //Database
    private val userDb: UserDatabase by lazy {
        Room.databaseBuilder(this, UserDatabase::class.java, Constants.USER_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var userEntity: UserEntity
    private var userID = 0
    private var defaultName = ""
    private var defaultAge = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Intent
        intent.extras?.let {
            userID = it.getInt(Constants.BUNDLE_USER_ID)
        }
        //Binding
        binding.apply {
            defaultName = userDb.dao().getUser(userID).userName
            defaultAge = userDb.dao().getUser(userID).userAge
            nameEdt.setText(defaultName)
            ageEdt.setText(defaultAge.toString())
            //Delete
            deleteBtn.setOnClickListener {
                userEntity = UserEntity(userID, defaultName, defaultAge)
                userDb.dao().deleteUser(userEntity)
                finish()
            }
            //Update
            updateBtn.setOnClickListener {
                val name = nameEdt.text.toString()
                val age = ageEdt.text.toString()

                if (name.isNotEmpty() || age.isNotEmpty()) {
                    userEntity = UserEntity(userID, name, age.toInt())
                    userDb.dao().updateUser(userEntity)
                    finish()
                } else {
                    Snackbar.make(it, "Name and age cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}