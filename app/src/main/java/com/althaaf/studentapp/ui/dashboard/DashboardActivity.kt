package com.althaaf.studentapp.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.althaaf.studentapp.databinding.FoActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: FoActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}