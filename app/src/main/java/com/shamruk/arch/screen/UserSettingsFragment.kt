package com.shamruk.arch.screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shamruk.arch.R

class UserSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = UserSettingsFragment()
    }

    private lateinit var viewModel: UserSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserSettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
