package com.shamruk.arch.screen

import androidx.fragment.app.FragmentManager

class NavigationHelper {
    companion object {
        fun showMainListFragment(fragmentManager: FragmentManager, rootLayout: Int) {
            val transaction = fragmentManager.beginTransaction()
            val fragment = MainListFragment.newInstance()
            transaction.replace(rootLayout, fragment, MainListFragment.TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fun showLoginFragment(fragmentManager: FragmentManager, rootLayout: Int) {
            val transaction = fragmentManager.beginTransaction()
            val fragment = LoginFragment.newInstance()
            transaction.replace(rootLayout, fragment, LoginFragment.TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}