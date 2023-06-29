package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfServiceTextview.text.toString().toDoubleOrNull()
        if (cost == null) {
            binding.tipResultTextview.text = ""
            return
        }
        val tipPercentage = when(binding.tipOptionsButton.checkedRadioButtonId){
            R.id.twenty_percent_button -> 0.20
            R.id.eighteen_percent_button -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        if(binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResultTextview.text = getString(R.string.tip_amount, formattedTip)
    }
}