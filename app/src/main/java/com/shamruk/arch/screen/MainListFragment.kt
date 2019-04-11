package com.shamruk.arch.screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.shamruk.arch.R
import com.shamruk.arch.adapter.MainListAdapter
import com.shamruk.arch.databinding.MainListFragmentBinding
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_list_fragment.*
import java.util.ArrayList

class MainListFragment : BaseFragment() {

    companion object {
        const val TAG: String = "MainListFragment"

        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var listAdapter: MainListAdapter

    private lateinit var viewDataBinding: MainListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = MainListFragmentBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewModel?.start()
    }

    override fun getFragmentName():String{
        return TAG
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewModel
        if (viewModel != null) {
            listAdapter = MainListAdapter()
            val mainListRecycler = viewDataBinding.mainListRecycler
            mainListRecycler.adapter = listAdapter
            linearLayoutManager = LinearLayoutManager(context)
            mainListRecycler.layoutManager = linearLayoutManager
        } else {
            Log.w(TAG, "ViewModel not initialized when attempting to set up adapter.")
        }
    }
}
