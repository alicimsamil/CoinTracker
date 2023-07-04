package com.alicimsamil.cointracker.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alicimsamil.cointracker.R
import com.alicimsamil.cointracker.feature.detail.R.id.detailFragment
import com.alicimsamil.cointracker.feature.auth.R.id.signInFragment
import com.alicimsamil.cointracker.core.common.extension.gone
import com.alicimsamil.cointracker.core.common.extension.visible
import com.alicimsamil.cointracker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private var state: MainUiState = MainUiState()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            state.splashState
        }
        splashScreenAnimationExitListener()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeUiState()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navController = this.findNavController(R.id.fragment_container)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                signInFragment -> {
                    binding.bottomNavigationView.gone()
                }
                detailFragment -> {
                    binding.bottomNavigationView.gone()
                }
                else -> {
                    binding.bottomNavigationView.visible()
                }
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collectLatest {
                    state = it
                }
            }
        }
    }
}