package ir.digireza.s4_mvvm.food_app.ui.detail.player

import ir.digireza.s4_mvvm.databinding.ActivityPlayerBinding
import ir.digireza.s4_mvvm.food_app.utils.VIDEO_ID
import ir.digireza.s4_mvvm.food_app.utils.YOUTUBE_API_KEY
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

@Suppress("DEPRECATION")
class PlayerActivity : YouTubeBaseActivity() {
    //Binding
    private var _binding: ActivityPlayerBinding? = null
    private val binding get() = _binding!!

    //Other
    private lateinit var player: YouTubePlayer
    private var videoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        //Full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)
        //Get id
        videoId = intent.getStringExtra(VIDEO_ID).toString()
        //Player initialize
        val listener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer, p2: Boolean) {
                player = p1
                player.loadVideo(videoId)
                player.play()
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

            }

        }

        binding.videoPlayer.initialize(YOUTUBE_API_KEY, listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}