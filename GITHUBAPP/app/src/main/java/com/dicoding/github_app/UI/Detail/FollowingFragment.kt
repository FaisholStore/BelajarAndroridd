package com.dicoding.github_app.UI.Detail

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.github_app.R
import com.dicoding.github_app.UI.main.UserAdapter
import com.dicoding.github_app.databinding.FragmentFollowBinding

class FollowingFragment  : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter
    private lateinit var username: String
    private lateinit var viewModel: FollowingView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)

        val  args  = arguments
        username = args?.getString(DetailUsserActivity.EXTRA_NAME).toString()

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowingView::class.java)
        // Panggil fungsi untuk mendapatkan daftar pengikut berdasarkan username
        viewModel.setFollowingByUsername(username)

        viewModel.getFollowingByUsername().observe(viewLifecycleOwner){followingList ->

            if (followingList != null) {
                if (followingList.isEmpty()) {
                    // Tampilkan peringatan jika daftar pengikut kosong
                    binding.tvNoFollow.visibility = View.VISIBLE
                    binding.tvNoFollow.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
                } else {
                    // Sembunyikan peringatan jika ada pengikut
                    binding.tvNoFollow.visibility = View.GONE
                    binding.tvNoFollow.clearAnimation()
                    adapter.setList(followingList)
                }
                showLoading(false)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



}