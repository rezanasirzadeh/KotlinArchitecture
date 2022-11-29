package ir.digireza.s1_hilt.simple

import ir.digireza.s1_hilt.NAMED_APP_INFO
import ir.digireza.s1_hilt.R
import ir.digireza.s1_hilt.databinding.ActivityMainBinding
import ir.digireza.s1_hilt.simple.di.qualifier.SiteName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    @Inject
    //@SiteName
    @Named(NAMED_APP_INFO)
    lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            infoTxt.text = userName
        }
    }
}