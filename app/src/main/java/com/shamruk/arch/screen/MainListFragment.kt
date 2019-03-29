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

class MainListFragment : Fragment() {

    companion object {
        const val TAG: String = "MainListFragment"

        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager

    private var compactDisposable: CompositeDisposable = CompositeDisposable()

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
    }

    override fun onPause() {
        super.onPause()
        unbindViewModel()
    }

    private fun unbindViewModel(){
        compactDisposable.clear()
    }

    private fun bindViewModel() {
        compactDisposable.add(viewModel.getTestList()
            .compose(RxUtil.ioSingle())
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
        Toast.makeText(context, "onTestListReceive:$testList", Toast.LENGTH_SHORT).show()
        val adapter = MainListAdapter(testList)
        mainListRecycler.adapter = adapter
        linearLayoutManager = LinearLayoutManager(context)
        mainListRecycler.layoutManager = linearLayoutManager
    }
}
