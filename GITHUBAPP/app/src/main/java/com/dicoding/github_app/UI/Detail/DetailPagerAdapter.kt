package com.dicoding.github_app.UI.Detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.github_app.R


class DetailPagerAdapter(private val mContext: Context, fm: FragmentManager, private val data: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()
    private val fragmentTitles = mutableListOf<String>()
    private var dataAvailable = false // Inisialisasi dataAvailable sesuai kebutuhan
    @StringRes
    private val  tab_title = intArrayOf(R.string.tab_1, R.string.tab_2)
    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    // Contoh jika jumlah fragment bergantung pada kondisi tertentu
    override fun getCount(): Int {
        return if (dataAvailable) {
            3 // Jika data tersedia, tampilkan 3 fragment
        } else {
            2 // Jika data tidak tersedia, tampilkan 2 fragment
        }
    }

    // Setter untuk mengubah status dataAvailable
    fun setDataAvailable(available: Boolean) {
        dataAvailable = available
        notifyDataSetChanged() // Perbarui tampilan jika ada perubahan dataAvailable
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> {
                fragment = FollowersFragment()
                fragment.arguments = data // Kirim bundle ke FollowersFragment
            }
            1 -> {
                fragment = FollowingFragment()
                fragment.arguments = data // Kirim bundle ke FollowingFragment
            }
            else -> throw IllegalArgumentException("Invalid position")
        }
        return fragment
    }


    override fun getPageTitle(position: Int): CharSequence? {
       return  mContext.resources.getString(tab_title[position])
    }
}


