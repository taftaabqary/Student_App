package com.althaaf.studentapp.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.althaaf.studentapp.core.helper.MainViewModelFactory
import com.althaaf.studentapp.databinding.FoActivityLoginBinding
import com.althaaf.studentapp.ui.dashboard.DashboardActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable.combineLatest
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: FoActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()
        setupViewModel()
        setupAction()
    }

    private fun initView() {
        val usernameStream = RxTextView.textChanges(binding.inputLoginUsername)
            .skipInitialValue()
            .map {
                it.isEmpty()
            }

        val passwordStream = RxTextView.textChanges(binding.inputLoginPassword)
            .skipInitialValue()
            .map {
                it.isEmpty()
            }

        val invalidFieldStream = combineLatest(
            usernameStream,
            passwordStream
        ) { usernameInvalid: Boolean, passwordInvalid: Boolean ->
            !usernameInvalid && !passwordInvalid
        }

        val invalidFieldsStreamSubscription = invalidFieldStream.subscribe { isValid ->
            binding.btnLogin.isEnabled = isValid
        }

        compositeDisposable.add(invalidFieldsStreamSubscription)
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            MainViewModelFactory.getInstance(this)
        )[LoginViewModel::class.java]
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            hideKeyboard()
            val inputUsername = binding.inputLoginUsername.text.toString().trim()
            val inputPassword = binding.inputLoginPassword.text.toString().trim()

            lifecycleScope.launch(Dispatchers.Main) {
                binding.animationLoading.visibility = View.VISIBLE
                binding.btnLogin.visibility = View.GONE
                delay(3000)

                withContext(Dispatchers.Main) {
                    if (inputUsername == "alfagift-admin" && inputPassword == "asdf") {
                        loginViewModel.saveUserToken()
                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_LONG).show()
                        binding.animationLoading.visibility = View.GONE
                        binding.btnLogin.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}