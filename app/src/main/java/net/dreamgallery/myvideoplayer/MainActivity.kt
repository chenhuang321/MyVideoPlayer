package net.dreamgallery.myvideoplayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlaySingle.setOnClickListener(this)
        btnStandalone.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = when (v?.id) {
            R.id.btnPlaySingle -> Intent(this, VideoActivity::class.java)
            R.id.btnStandalone -> Intent(this, StandaloneActivity::class.java)
            else -> throw IllegalArgumentException("Undefined button clicked!")
        }
        startActivity(intent)
    }
}
