package com.alicimsamil.cointracker.feature.auth.presentation

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.alicimsamil.cointracker.core.ui.base.BaseFragment
import com.alicimsamil.cointracker.feature.auth.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(FragmentSignInBinding::inflate) {
    override val viewModel by viewModels<SignInViewModel>()
    override var state = SignInUiState()

    override fun initialize() {
        super.initialize()
        observeUiState()
        initListeners()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    state = it
                    handleFailure()
                    handleLoading()
                    goListing()
                }
            }
        }
    }

    private fun initListeners(){
        binding.btnSignIn.setOnClickListener {
            viewModel.onEvent(SignInEvents.SignIn(binding.etMail.text.toString(), binding.etPassword.text.toString()))
        }
    }

    private fun goListing(){
        if (state.signInSuccess){
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.alicimsamil.cointracker/listing_fragment".toUri())
                .build()
            findNavController().navigate(request)
        }
    }

}