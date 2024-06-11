package com.ryusw

import androidx.core.os.bundleOf
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

    override fun init() { }

    override fun navigateSplashToLogin() {
        with(navHostFragment.navController) {
            popBackStack(com.ryusw.feature.splash.R.id.navigation_splash, true)
            navigate(R.id.action_splash_to_login)
        }
    }

    override fun navigateLoginToMovieList() {
        with(navHostFragment.navController){
            popBackStack(com.ryusw.feature.login.R.id.navigation_login, true)
            navigate(R.id.action_login_to_movie_list)
        }
    }

    override fun navigateMovieListToMovieDetail(id: Int) {
        val bundle = bundleOf("id" to id)
        navHostFragment.navController.navigate(R.id.action_movie_list_to_movie_detail, bundle)
    }

    override fun navigateMovieDetailToMovieList() {
        with(navHostFragment.navController){
            popBackStack(com.ryusw.feature.movie.list.R.id.navigation_movie_list, true)
            navigate(R.id.action_movie_detail_to_movie_list)
        }
    }
}