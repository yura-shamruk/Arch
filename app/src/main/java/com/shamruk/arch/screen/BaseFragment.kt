package com.shamruk.arch.screen

import androidx.fragment.app.Fragment
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(){

    companion object {
        const val TAG = "BaseFragment"
    }


    open fun getFragmentName(): String{
        return TAG
    }

}