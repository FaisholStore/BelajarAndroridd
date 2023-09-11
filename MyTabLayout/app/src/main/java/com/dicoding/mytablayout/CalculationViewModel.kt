package com.dicoding.mytablayout

import androidx.lifecycle.ViewModel

class CalculationViewModel : ViewModel() {

    var number1: Float = 0.0f
    var number2: Float = 0.0f
    var result: Float = 0.0f

    // Properti tambahan untuk hasil operasi tambahan
    var additionResult: Float = 0.0f
    var subtractionResult: Float = 0.0f
    var multiplicationResult: Float = 0.0f

    // Fungsi untuk menghitung dan menyimpan hasil operasi tambahan
    fun calculateAdditionalResult() {
        additionResult = number1 + number2
    }

    fun calculateSubtractionResult() {
        subtractionResult = number1 - number2
    }

    fun calculateMultiplicationResult() {
        multiplicationResult = number1 * number2
    }
    fun resetResults() {
        additionResult = 0.0f
        subtractionResult = 0.0f
        multiplicationResult = 0.0f
    }
    

}
