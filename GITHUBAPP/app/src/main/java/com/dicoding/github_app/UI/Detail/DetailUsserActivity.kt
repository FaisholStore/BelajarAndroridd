package com.dicoding.github_app.UI.Detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.github_app.databinding.ActivityDetailUsserBinding

class DetailUsserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUsserBinding
    private lateinit var viewModel: DetailViewModel

    companion object {
        const val EXTRA_NAME = "user_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUsserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra(EXTRA_NAME)
          val  bundle = Bundle()
        bundle.putString(EXTRA_NAME, username)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        if (username != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.setUserDetail(username)

            viewModel.getUserDetail().observe(this, {
                if (it != null) {
                    binding.apply {
                        tvName.text = it.name
                        tvUsername.text = it.login
                        tvFollowers.text = "${it.followers} Followers"
                        tvFollowing.text = "${it.following} Following"

                        Glide.with(this@DetailUsserActivity)
                            .load(it.avatar_url)
                            .load(it.avatar_url)
                            .centerCrop()
                            .into(ivUsername)
                    }
                }
            })

            val DetailPagerAdapter = DetailPagerAdapter(this, supportFragmentManager,bundle)
            binding.apply {
                viewPager.adapter = DetailPagerAdapter
                tabs.setupWithViewPager(viewPager)
            }

        }
    }
}
