package com.althaaf.studentapp.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.althaaf.studentapp.R
import com.althaaf.studentapp.core.adapter.LoadingStateAdapter
import com.althaaf.studentapp.core.adapter.StudentListAdapter
import com.althaaf.studentapp.core.helper.MainViewModelFactory
import com.althaaf.studentapp.databinding.FoActivityDashboardBinding
import com.althaaf.studentapp.ui.splash.MainActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: FoActivityDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        initView()
        initAction()
    }

    private fun initAction() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
    }

    private fun logout() {
        dashboardViewModel.logout()
        Toast.makeText(this, "Logout Success", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
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

        adapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.Loading && adapter.itemCount < 1) {
                binding.rvStudents.visibility = View.GONE
                binding.animationLoading.visibility = View.VISIBLE
            } else {
                binding.rvStudents.visibility = View.VISIBLE
                binding.animationLoading.visibility = View.GONE
            }
        }
    }
}