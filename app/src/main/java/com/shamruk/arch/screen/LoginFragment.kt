package com.shamruk.arch.screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shamruk.arch.R
import com.shamruk.arch.model.LoginUserDetails

import com.shamruk.arch.utils.RxUtil
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment() {

    companion object {
        const val TAG: String = "LoginFragment"

        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
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
        compactDisposable.add(viewModel.isProgressSubject
            .compose(RxUtil.ioObservable())
            .subscribe({ t -> setProgressVisibility(t) }, {})
        )
        compactDisposable.add(viewModel.loginTitle
            .compose(RxUtil.ioObservable())
            .subscribe({ t -> updateTitles(t) }, {})
        )
    }

    private fun updateTitles(userDetails: LoginUserDetails) {
        screenTitle.text = userDetails.name
        screenSubtitle.text = userDetails.lastName
        Picasso.get()
            .load(userDetails.avatarUrl)
            .into(avatarImageView)
    }

    private fun setProgressVisibility(isVisible: Boolean) {
        if (isVisible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }
}
