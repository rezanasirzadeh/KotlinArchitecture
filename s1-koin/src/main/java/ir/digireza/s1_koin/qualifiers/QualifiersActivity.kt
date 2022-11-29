package ir.digireza.s1_koin.qualifiers

import ir.digireza.s1_koin.R
import ir.digireza.s1_koin.databinding.ActivityQualifiersBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject

class QualifiersActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityQualifiersBinding

    private val human: Human by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQualifiersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoTxt.text = human.showInfo()
    }
}