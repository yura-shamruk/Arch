package com.shamruk.arch.screen

import androidx.fragment.app.FragmentManager

class NavigationHelper {
    companion object {
        fun showFragment(fragmentManager: FragmentManager, rootLayout: Int, fragment:BaseFragment){
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(rootLayout, fragment, fragment.getFragmentName())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}