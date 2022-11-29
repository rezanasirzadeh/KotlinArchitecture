package ir.digireza.s1_livedata.network

import ir.digireza.s1_livedata.R
import ir.digireza.s1_livedata.databinding.ActivityNetworkBinding
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class NetworkActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityNetworkBinding

    //Other
    private val checkConnection by lazy { CheckConnection(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Network status
            checkConnection.observe(this@NetworkActivity) {
                if (it) {
                    networkImg.setImageResource(R.drawable.ic_round_wifi_24)
                    networkImg.setColorFilter(
                        ContextCompat.getColor(this@NetworkActivity, R.color.green),
                        PorterDuff.Mode.MULTIPLY
                    )
                    networkStatus.text = "CONNECTED :)"
                    networkStatus.setTextColor(ContextCompat.getColor(this@NetworkActivity, R.color.green))
                } else {
                    networkImg.setImageResource(R.drawable.ic_round_wifi_off_24)
                    networkImg.setColorFilter(
                        ContextCompat.getColor(this@NetworkActivity, R.color.red),
                        PorterDuff.Mode.MULTIPLY
                    )
                    networkStatus.text = "OPPPS! DISCONNECTED :("
                    networkStatus.setTextColor(ContextCompat.getColor(this@NetworkActivity, R.color.red))
                }
            }
        }
    }
}