package com.dicoding.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class ActivityBaru : AppCompatActivity() {
    private lateinit var imageStatusTextView: TextView
    private var isImageVisible = false
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var handler: Handler
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baru)

        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        imageStatusTextView = findViewById(R.id.imageStatusTextView)
        handler = Handler(Looper.getMainLooper())

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button.setOnClickListener {
            if (isImageVisible) {
                imageView.visibility = View.GONE
                isImageVisible = false
                imageStatusTextView.text = "Gambar tidak terlihat"
            } else {
                imageView.visibility = View.VISIBLE
                isImageVisible = true
                imageStatusTextView.text = "Gambar terlihat"

                handler.postDelayed({
                    imageStatusTextView.text = ""
                }, 2000) // 2000 ms (2 detik)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
