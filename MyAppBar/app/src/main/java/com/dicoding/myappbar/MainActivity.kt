package com.dicoding.myappbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.dicoding.myappbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDataVisible = false // Variabel status untuk melacak apakah data sudah ditampilkan atau belum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu1 -> {
                    // Jika data belum ditampilkan, tampilkan data
                    if (!isDataVisible) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, MenuFragment())
                            .addToBackStack(null)
                            .commit()
                        isDataVisible = true // Set status menjadi data ditampilkan
                    } else {
                        // Jika data sudah ditampilkan, hapus fragment
                        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                        if (fragment != null) {
                            supportFragmentManager.beginTransaction()
                                .remove(fragment)
                                .commit()
                        }
                        isDataVisible = false // Set status menjadi data ditutup
                    }
                    true
                }
                R.id.menu2 -> {
                    // Navigasi ke MenuActivity saat menu2 ditekan
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
