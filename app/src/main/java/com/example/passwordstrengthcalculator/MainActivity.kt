package com.example.passwordstrengthcalculator

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.passwordstrengthcalculator.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val strengthCalculator = StrengthCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etPassword.addTextChangedListener(strengthCalculator)
        lifecycleScope.launchWhenCreated {
            strengthCalculator.passwordUIState.collect{
                binding.apply {
                    pbPasswordStrength.progress = it.progressScore
                    pbPasswordStrength.progressTintList = ColorStateList.valueOf(it.color)
                    twPasswordStrength.text = it.strength.name
                }
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}