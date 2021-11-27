package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val costInString = binding.cost.text.toString()
        val cost = costInString.toDoubleOrNull()

        if (cost == null) {
            binding.totalAmount.text = "Invalid Cost"
            return
        }

        if (cost == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twentyPercent -> 0.20
            R.id.eighteenPercent -> 0.18
            else -> 0.15
        }

        var tipAmount = cost * tipPercent
        if (binding.roundUpButton.isChecked) {
            tipAmount = ceil(tipAmount)
        }

        displayTip(tipAmount)
    }

    private fun displayTip(tipAmount: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.totalAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}