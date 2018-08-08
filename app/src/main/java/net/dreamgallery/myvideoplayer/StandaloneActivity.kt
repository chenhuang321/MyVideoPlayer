package net.dreamgallery.myvideoplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)
        btnPlayVideo.setOnClickListener(this)
        btnPlayList.setOnClickListener(this)
        /** Note: These methods almost do the same thing
        btnPlayVideo.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("Not implemented")
            }
        })

        btnPlayVideo.setOnClickListener(View.OnClickListener {
            TODO("Not implemented")
        })

        val listener = View.OnClickListener { v ->
            TODO("Not implemented")
        }
        btnPlayVideo.setOnClickListener(listener)
        btnPlayList.setOnClickListener(listener)
         **/
    }

    override fun onClick(view: View?) {
        val intent = when (view?.id) {
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                    this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID, 0, true, false)
            R.id.btnPlayList -> YouTubeStandalonePlayer.createPlaylistIntent(
                    this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST, 0, 0, true, true)
            else -> throw IllegalArgumentException("Undefined button clicked!")
        }
        startActivity(intent)
    }
}