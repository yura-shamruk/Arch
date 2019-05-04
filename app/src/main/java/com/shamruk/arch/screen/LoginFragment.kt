package com.shamruk.arch.screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController

import com.shamruk.arch.R

import com.shamruk.arch.databinding.LoginFragmentBinding

class LoginFragment : BaseFragment() {

    companion object {
        const val TAG: String = "LoginFragment"

        fun newInstance() = LoginFragment()
    }


    private lateinit var viewDataBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = LoginFragmentBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewmodel?.start()

        val observer = Observer<Boolean> {
            findNavController(this).navigate(R.id.action_loginFragment_to_userSettingsFragment)
        }
        viewDataBinding.viewmodel?.openUserSettingsScreen?.observe(this, observer)
    }

    override fun getFragmentName():String{
        return MainListFragment.TAG
    }
}
