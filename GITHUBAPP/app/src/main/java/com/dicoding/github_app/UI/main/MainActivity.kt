package com.dicoding.github_app.UI.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.github_app.UI.Detail.DetailUsserActivity
import com.dicoding.github_app.data.Model.User
import com.dicoding.github_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun OnItemClicked(data: User) {
                Intent(this@MainActivity,DetailUsserActivity::class.java).also {
                    it.putExtra(DetailUsserActivity.EXTRA_NAME,data.login)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()
            }

            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        // Observer untuk daftar pengguna
        viewModel.users.observe(this, { users ->
            if (users != null)
                adapter.setList(users)
            showLoading(false)
        })
    }

    private fun searchUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isNotEmpty()) {
                showLoading(true)
                // Panggil fungsi di ViewModel untuk melakukan pencarian data pengguna
                // Anda perlu mengimplementasikan fungsi ini di dalam MainViewModel
                viewModel.searchUsers(query)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    fun searchUser(view: View) {}
}
