package com.dicoding.mytablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class InputFragment : Fragment() {

    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText
    private lateinit var addButton: Button
    private lateinit var subtractButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var calculateButton: Button

    private lateinit var calculationViewModel: CalculationViewModel
    private var selectedOperation: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        editTextNumber1 = view.findViewById(R.id.editTextNumber1)
        editTextNumber2 = view.findViewById(R.id.editTextNumber2)
        addButton = view.findViewById(R.id.addButton)
        subtractButton = view.findViewById(R.id.subtractButton)
        multiplyButton = view.findViewById(R.id.multiplyButton)
        calculateButton = view.findViewById(R.id.calculateButton)

        calculationViewModel = ViewModelProvider(requireActivity()).get(CalculationViewModel::class.java)

        // Mengatur listener untuk tombol operasi
        addButton.setOnClickListener { selectedOperation = "+" }
        subtractButton.setOnClickListener { selectedOperation = "-" }
        multiplyButton.setOnClickListener { selectedOperation = "*" }

        calculateButton.setOnClickListener {
            // Mengambil angka dari editTextNumber1 dan editTextNumber2
            val number1 = editTextNumber1.text.toString().toFloatOrNull() ?: 0.0f
            val number2 = editTextNumber2.text.toString().toFloatOrNull() ?: 0.0f

            // Simpan nilai input ke dalam ViewModel
            calculationViewModel.number1 = number1
            calculationViewModel.number2 = number2

            // Mengeksekusi operasi yang dipilih
            val result = selectedOperation?.let { calculateResult(number1, number2, it) } ?: 0.0f

            // Menyimpan hasil perhitungan ke dalam ViewModel
            when (selectedOperation) {
                "+" -> calculationViewModel.additionResult = result
                "-" -> calculationViewModel.subtractionResult = result
                "*" -> calculationViewModel.multiplicationResult = result
            }

            // Memindahkan tampilan ke ResultFragment
            val parentFragmentManager = parentFragmentManager
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, ResultFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    private fun calculateResult(number1: Float, number2: Float, operation: String): Float {
        return when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            else -> 0.0f
        }
    }

}
