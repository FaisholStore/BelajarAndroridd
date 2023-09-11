package com.dicoding.belajar_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.dicoding.belajar_android.databinding.FragmentHomeBinding

@Suppress("NAME_SHADOWING")
class HomeFragment : Fragment() {
    // Variabel untuk mengikat tampilan fragment menggunakan data binding
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate (mengisi) layout fragment dengan menggunakan data binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Menambahkan event listener untuk tombol "btnCategory"
        binding.btnCategory.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_categoryFragment)
        )
        // Menambahkan event listener untuk tombol "btnProfile"
        binding.btnProfile.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_profileActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Membersihkan _binding untuk mencegah memory leaks saat fragment dihancurkan
        _binding = null
    }
}