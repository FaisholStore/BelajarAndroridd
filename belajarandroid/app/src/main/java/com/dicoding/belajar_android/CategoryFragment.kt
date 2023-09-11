package com.dicoding.belajar_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dicoding.belajar_android.databinding.FragmentCategoryBinding


@Suppress("NAME_SHADOWING")
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCategoryLifestyle.setOnClickListener {
            // Membuat objek action dengan menggunakan Safe Args dan mengirimkan data
            val action = CategoryFragmentDirections.actionCategoryFragmentToDetailCategoryFragment(
                name = "Lifestyle",
                stock = 7
            )

            // Melakukan navigasi ke DetailCategoryFragment dengan objek action
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


