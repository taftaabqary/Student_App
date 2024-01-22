package com.althaaf.studentapp.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.althaaf.studentapp.core.adapter.LoadingStateAdapter
import com.althaaf.studentapp.core.adapter.StudentListAdapter
import com.althaaf.studentapp.core.helper.MainViewModelFactory
import com.althaaf.studentapp.databinding.FoActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: FoActivityDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        initView()
    }

    private fun setupViewModel() {
        dashboardViewModel = ViewModelProvider(
            this,
            MainViewModelFactory.getInstance(this)
        )[DashboardViewModel::class.java]
    }

    private fun initView() {
        val adapter = StudentListAdapter()
        binding.rvStudents.layoutManager = GridLayoutManager(this, 2)
        binding.rvStudents.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        dashboardViewModel.getStudentsList().observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}