package com.ymejia.notes.General

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ymejia.notes.General.util.LoadingDialog
import com.ymejia.notes.R
import com.ymejia.notes.databinding.ActivityHomeBinding



    lateinit var binding: ActivityHomeBinding
    lateinit var navController: NavController

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentC) as NavHostFragment;
        navController = NavHostFragment.navController

        val bottomNavigationView = binding.bottomNav
        //val navController = findNavController(R.id.fragmentC)

        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.bottomNav,navController)


        bottomNavigationView.setupWithNavController(navController)

        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{

            override fun run() {
                loading.isDismiss()
            }

        },1000)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}