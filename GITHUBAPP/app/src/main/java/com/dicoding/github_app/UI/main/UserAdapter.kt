package com.dicoding.github_app.UI.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dicoding.github_app.data.Model.User
import com.dicoding.github_app.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = ArrayList<User>()

    // Callback yang digunakan untuk menghandle item yang diklik
    private var onItemClickCallback: OnItemClickCallback? = null

    // Setter untuk mengatur callback saat item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Metode ini mengikat data pengguna ke tampilan item di RecyclerView
        fun bind(user: User) {
            binding.root.setOnClickListener {
                // Memanggil callback saat item diklik dengan data pengguna
                onItemClickCallback?.OnItemClicked(user)
            }
            binding.apply {
                // Menggunakan Glide untuk memuat dan menampilkan avatar pengguna
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .transform(CircleCrop())
                    .into(ivUsername)

                // Menampilkan nama pengguna di TextView
                tvUsername.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Membuat ViewHolder dan menghubungkannya dengan tata letak item_user.xml
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // Mengikat data pengguna ke ViewHolder
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah item dalam daftar
        return userList.size
    }

    // Metode ini digunakan untuk mengatur daftar pengguna dengan data baru
    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    // Interface untuk menghandle klik item
    interface OnItemClickCallback {
        fun OnItemClicked(data: User)
    }
}
