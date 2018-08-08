package net.dreamgallery.myvideoplayer

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "H9yC7WEobRU"
const val YOUTUBE_PLAYLIST = "PLodM0rRo_Akue2LP7r9KeJ3hUD8kiZfcw"
val nullValue = null

class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "YoutubeActivity"
    private val DIALOG_REQUEST_CODE = 1

    val playerView by lazy { YouTubePlayerView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_video)
        //val layout = findViewById<ConstraintLayout>(R.id.activity_video)
        val layout = layoutInflater.inflate(R.layout.activity_video, nullValue) as ConstraintLayout
        setContentView(layout)

        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
                                         wasRestore: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialize Youtube Player successfully", Toast.LENGTH_SHORT).show()

        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)

        if(!wasRestore) {
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        } else {
            youTubePlayer?.play()
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         youTubeInitializationResult: YouTubeInitializationResult?) {
       if(youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
       } else {
           val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
           Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
       }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {
            //Toast.makeText(this@VideoActivity, "Good video is playing ok", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            //Toast.makeText(this@VideoActivity, "Video has stopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            //Toast.makeText(this@VideoActivity, "Video has paused", Toast.LENGTH_SHORT).show()
        }

    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            //Toast.makeText(this@VideoActivity, "Click ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {

        }

        override fun onVideoStarted() {
            //Toast.makeText(this@VideoActivity, "Video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {

        }

        override fun onVideoEnded() {
            //Toast.makeText(this@VideoActivity, "Congratulations! You've completed another video.", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult: called with request code $resultCode for request $requestCode")

        if(requestCode == DIALOG_REQUEST_CODE) {
            Log.d(TAG, intent?.toString())
            Log.d(TAG, intent?.extras.toString())
            playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
        }
    }
}
