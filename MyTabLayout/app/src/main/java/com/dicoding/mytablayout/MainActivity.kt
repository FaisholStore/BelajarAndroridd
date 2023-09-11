package com.dicoding.mytablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private lateinit var calculationViewModel: CalculationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)

        // Inisialisasi adapter untuk ViewPager2
        val pagerAdapter = SectionsPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // Menghubungkan ViewPager2 dengan TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Input"
                1 -> tab.text = "Result"
                else -> tab.text = "Tab $position" // Default, jika posisi tidak valid
            }
        }.attach()
    }

    private inner class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            // Jumlah tab (fragment) yang akan ditampilkan
            return 2 // Ubah sesuai dengan jumlah tab yang Anda miliki
        }

        override fun createFragment(position: Int): Fragment {
            // Mengembalikan fragment yang sesuai dengan posisi tab
            return when (position) {
                0 -> InputFragment()
                1 -> ResultFragment()
                else -> InputFragment() // Default, jika posisi tidak valid
            }
        }
    }
}
