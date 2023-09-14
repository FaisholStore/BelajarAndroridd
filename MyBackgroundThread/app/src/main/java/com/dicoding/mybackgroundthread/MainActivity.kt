package com.dicoding.mybackgroundthread


import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var startButton: Button
    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        startButton = findViewById(R.id.btn_start)
        statusTextView = findViewById(R.id.tv_status)

        startButton.setOnClickListener {
            startAsyncTask()
        }
    }

    private fun startAsyncTask() {
        val task = MyAsyncTask()
        task.execute()
    }

    private inner class MyAsyncTask : AsyncTask<Void, Int, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            // Pekerjaan pra-eksekusi di sini, seperti menampilkan ProgressBar dan mengubah status TextView.
            progressBar.visibility = View.VISIBLE
            statusTextView.text = "Loading..."
        }

        override fun doInBackground(vararg params: Void?): Void? {
            // Pekerjaan latar belakang dilakukan di sini.
            for (i in 1..100) {
                // Contoh: Pembaruan kemajuan pekerjaan.
                publishProgress(i)
                try {
                    Thread.sleep(100) // Simulasi pekerjaan yang memakan waktu.
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            // Memperbarui ProgressBar selama pekerjaan berlangsung.
            val  progress  = values[0] ?: 0
            progressBar.progress  = progress
            statusTextView.text ="ProgressBar: $progress %"

            if (progress == 100){
                statusTextView.text = "Proses loading telah selesai"

                // Pindah ke activity baru setelah proses loading selesai.
                val intent = Intent(this@MainActivity, ActivityBaru::class.java)
                startActivity(intent)
            }
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            // Pekerjaan selesai di sini, seperti menyembunyikan ProgressBar dan mengubah status TextView.
            progressBar.visibility = View.GONE
            statusTextView.text = "Proses loading telah selesai."

            // Menampilkan pesan Toast yang memberi tahu pengguna bahwa proses loading telah selesai.
            Toast.makeText(applicationContext, "Proses loading telah selesai.", Toast.LENGTH_SHORT).show()
        }
    }
}
