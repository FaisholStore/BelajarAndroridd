package com.dicoding.mytablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mytablayout.R
class ResultFragment : Fragment() {

    private lateinit var additionResultTextView: TextView
    private lateinit var subtractionResultTextView: TextView
    private lateinit var multiplicationResultTextView: TextView
    private lateinit var calculationViewModel: CalculationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        additionResultTextView = view.findViewById(R.id.additionResultTextView)
        subtractionResultTextView = view.findViewById(R.id.subtractionResultTextView)
        multiplicationResultTextView = view.findViewById(R.id.multiplicationResultTextView)

        calculationViewModel = ViewModelProvider(requireActivity()).get(CalculationViewModel::class.java)

        // Mengambil hasil perhitungan dari CalculationViewModel
        val additionResult = calculationViewModel.additionResult
        val subtractionResult = calculationViewModel.subtractionResult
        val multiplicationResult = calculationViewModel.multiplicationResult

        // Menampilkan hasil sesuai dengan operasi yang dipilih di InputFragment
        if (additionResult != null) {
            additionResultTextView.text = "Hasil Pertambahan: $additionResult"
            additionResultTextView.visibility = View.VISIBLE
        }
        if (subtractionResult != null) {
            subtractionResultTextView.text = "Hasil Pengurangan: $subtractionResult"
            subtractionResultTextView.visibility = View.VISIBLE
        }
        if (multiplicationResult != null) {
            multiplicationResultTextView.text = "Hasil Perkalian: $multiplicationResult"
            multiplicationResultTextView.visibility = View.VISIBLE
        }

        return view
    }
}

