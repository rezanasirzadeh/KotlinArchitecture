package ir.digireza.s3_mvp.retrofit.ui.detail.player

import ir.digireza.s3_mvp.databinding.ActivityPlayerBinding
import ir.digireza.s3_mvp.retrofit.utils.VIDEO_ID
import ir.digireza.s3_mvp.retrofit.utils.YOUTUBE_API_KEY
import ir.digireza.s3_mvp.utils.showSnackBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

@Suppress("DEPRECATION")
class PlayerActivity : YouTubeBaseActivity() {
    //Binding
    private lateinit var binding: ActivityPlayerBinding

    //Other
    private var videoId = ""
    private lateinit var player: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        //Full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        //Get data
        videoId = intent.getStringExtra(VIDEO_ID).toString()
        //InitViews
        binding.apply {
            val listener = object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer, p2: Boolean) {
                    player = p1
                    player.loadVideo(videoId)
                    player.play()
                }

                override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                    root.showSnackBar("Error")
                }

            }
            videoPlayer.initialize(YOUTUBE_API_KEY, listener)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}