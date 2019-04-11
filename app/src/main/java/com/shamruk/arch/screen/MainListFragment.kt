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
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.sample_list_fragment.*

class MainListFragment : BaseFragment() {

    companion object {
        const val TAG: String = "MainListFragment"

        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sample_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
        viewModel.start()
    }

    override fun onPause() {
        super.onPause()
        unbindViewModel()
    }

    override fun bindViewModel() {
        compactDisposable.add(viewModel.getTestListObservable()
            .compose(RxUtil.ioObservable())
            .subscribe(this::onTestListReceive, this::onTestListError))

        compactDisposable.add(viewModel.getLoadingStateObservable()
            .compose(RxUtil.ioObservable())
            .subscribe{state: Boolean -> onLoadingStateChanged(state) })
    }

    private fun onLoadingStateChanged(state: Boolean) {
        if(state){
            progressBar.visibility = View.VISIBLE
        } else{
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun onTestListError(error: Throwable) {
        Log.d(TAG, "onTestListError: " + error.message)
    }

    private fun onTestListReceive(testList: List<String>) {
        Log.d(TAG, "onTestListReceive:$testList")
        val adapter = MainListAdapter(testList)
        mainListRecycler.adapter = adapter
        linearLayoutManager = LinearLayoutManager(context)
        mainListRecycler.layoutManager = linearLayoutManager
    }
}
