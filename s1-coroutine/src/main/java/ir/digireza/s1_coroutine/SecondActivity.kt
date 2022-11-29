package ir.digireza.s1_coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.digireza.s1_coroutine.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}