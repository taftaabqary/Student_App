package com.althaaf.studentapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.althaaf.studentapp.databinding.FoActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: FoActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}