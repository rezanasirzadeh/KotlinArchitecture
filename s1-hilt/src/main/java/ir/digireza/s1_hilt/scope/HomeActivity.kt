package ir.digireza.s1_hilt.scope

import ir.digireza.s1_hilt.R
import ir.digireza.s1_hilt.TAG
import ir.digireza.s1_hilt.databinding.ActivityHomeBinding
import ir.digireza.s1_hilt.retrofit.repository.ApiRepository
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var repository1: ApiRepository

    @Inject
    lateinit var repository2: ApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(TAG, repository1.hashCode().toString())
        Log.e(TAG, repository2.hashCode().toString())

        binding.btnPage.setOnClickListener {
            Intent(this, ProfileActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}