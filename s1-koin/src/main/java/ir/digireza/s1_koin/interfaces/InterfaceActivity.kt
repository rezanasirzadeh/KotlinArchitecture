package ir.digireza.s1_koin.interfaces

import ir.digireza.s1_koin.databinding.ActivityInterfaceBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject

class InterfaceActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityInterfaceBinding

    private val bmw : BMW by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterfaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoTxt.text = bmw.showCarInfo()
    }
}