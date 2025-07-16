package com.example.presentation.screens.enter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.presentation.R
import com.example.presentation.databinding.FragmentEnterScreenBinding
import com.example.presentation.viewmodels.EnterScreenViewModel
import kotlinx.coroutines.launch

class EnterFragment : Fragment() {

    private var _binding: FragmentEnterScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EnterScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Настройка Email поля
        val initialEmail = viewModel.email.value
        binding.emailField.label.text = getString(R.string.email)

        if (initialEmail.isNotEmpty()) {
            binding.emailField.editText.setText(initialEmail)
            binding.emailField.editText.setSelection(initialEmail.length)
        } else {
            binding.emailField.editText.hint = getString(R.string.hint_email)
        }


        // Настройка Password поля
        val initialPassword = viewModel.password.value
        binding.passwordField.label.text = getString(R.string.password)

        if (initialPassword.isNotEmpty()) {
            binding.passwordField.editText.setText(initialPassword)
            binding.passwordField.editText.setSelection(initialPassword.length)
        } else {
            binding.passwordField.editText.hint = getString(R.string.enter_a_password)
        }

        // Клики

        binding.emailField.editText.addTextChangedListener { editable ->
            val input = editable?.toString() ?: ""
            val filtered = viewModel.onEmailChanged(input)

            if (input != filtered) {
                binding.emailField.editText.setText(filtered)
                binding.emailField.editText.setSelection(filtered.length)
            }
        }


        binding.passwordField.editText.addTextChangedListener { editable ->
            viewModel.onPasswordChanged(editable?.toString() ?: "")
        }

        binding.btnEnter.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_enter_to_main)
        }
        binding.btnVk.setOnClickListener {
            viewModel.onVkButtonClick()
        }
        binding.btnOk.setOnClickListener {
            viewModel.onOkButtonClick()
        }

        binding.textViewForgotPassword.setOnClickListener {
            // TODO
        }
        binding.textViewRegistration.setOnClickListener {
            // TODO
        }
        observeEvents()
        observeValid()
    }

    private fun observeValid() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isFormValid.collect { isValid ->
                    binding.btnEnter.isEnabled = isValid
                }
            }
        }
    }
    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.openUrlEvent.collect { url ->
                    openUrlInBrowser(url)
                }
            }
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}