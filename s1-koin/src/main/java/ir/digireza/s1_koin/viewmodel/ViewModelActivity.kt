package ir.digireza.s1_koin.viewmodel

import ir.digireza.s1_koin.R
import ir.digireza.s1_koin.databinding.ActivityViewModelBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject

class ViewModelActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityViewModelBinding

    private val viewModel: UserViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoTxt.text = viewModel.showUserInfo()
    }
}