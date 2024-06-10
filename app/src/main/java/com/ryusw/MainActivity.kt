package com.ryusw

import androidx.navigation.fragment.NavHostFragment
import com.ryusw.databinding.ActivityMainBinding
import com.ryusw.common.ui.base.BaseActivity
import com.ryusw.common.ui.base.NavigationEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationEvent {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
    }

    override fun init() {
        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                ryusw.feature.login.R.id.loginFragment -> {

                }
            }
        }
    }

    override fun navigateSplashToLogin() {
        with(navHostFragment.navController){
            popBackStack(com.ryusw.feature.splash.R.id.navigation_splash, true)
            navigate(R.id.action_splash_to_login)
        }
    }

    override fun navigateLoginToMovieList() {
        navHostFragment.navController.navigate(R.id.action_login_to_movie_list)
    }

    override fun navigateMovieListToMovieInfo(id: Int) {

    }
}