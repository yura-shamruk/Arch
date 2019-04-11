package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel;
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.model.LoginUserDetails
import com.shamruk.arch.utils.RxUtil
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject


class LoginViewModel : BaseViewModel() {

    private var userDetails: LoginUserDetails? = null

    var loginTitle: BehaviorSubject<LoginUserDetails> = BehaviorSubject.create()

    var isProgressSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()


    override fun start() {
        showProgress()

        disposables.add(getUserDetailsObservable()
            .compose(RxUtil.ioSingle())
            .doFinally { hideProgress() }
            .subscribe({t: LoginUserDetails? -> onUserDetailsReceive(t)}, {t -> onUserDetailsError(t) }))
    }

    private fun getUserDetailsObservable(): Single<LoginUserDetails> {
        return if (userDetails == null) {
            ProjectsRepository.getUserDetails()
                .doOnSuccess { t -> userDetails = t }
        } else {
            Single.create { emitter ->
                emitter.onSuccess(userDetails!!)
            }
        }
    }


    private fun onUserDetailsError(t: Throwable?) {

    }

    private fun onUserDetailsReceive(userDetails: LoginUserDetails?) {
        userDetails?.let { loginTitle.onNext(it) }
    }

    private fun showProgress() {
        isProgressSubject.onNext(true)
    }

    private fun hideProgress() {
        isProgressSubject.onNext(false)
    }


}
