package ir.digireza.s1_livedata.room

import ir.digireza.s1_livedata.R
import ir.digireza.s1_livedata.databinding.ActivityNotesBinding
import ir.digireza.s1_livedata.room.db.NoteDatabase
import ir.digireza.s1_livedata.room.utils.NOTE_DATABASE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room

class NotesActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNotesBinding

    //Database
    val noteDb by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    //Other
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Navigation
            navController = findNavController(R.id.navHost)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}