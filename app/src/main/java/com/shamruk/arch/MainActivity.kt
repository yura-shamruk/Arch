package com.shamruk.arch

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.shamruk.arch.screen.NavigationHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.main_list_screen -> {
                NavigationHelper.showMainListFragment(supportFragmentManager, R.id.mainContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.login_screen -> {
                NavigationHelper.showLoginFragment(supportFragmentManager, R.id.mainContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if(savedInstanceState == null) {
            navigation.selectedItemId = R.id.main_list_screen
        }
    }
}
