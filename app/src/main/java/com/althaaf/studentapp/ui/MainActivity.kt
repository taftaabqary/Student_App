package com.althaaf.studentapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.althaaf.studentapp.databinding.FoActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: FoActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}