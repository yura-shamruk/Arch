package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel;
import androidx.databinding.ObservableField
import androidx.databinding.Bindable
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.model.LoginTitles
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable


class LoginViewModel : ViewModel() {
    var titleText = ObservableField<String>()

    private var subtitleText : String? = ""

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun start(){
        disposables.add(ProjectsRepository.getLoginTitles()
            .compose(RxUtil.ioSingle())
            .subscribe({titles ->  onTitlesReceive(titles)}, {t -> onTitlesError(t) }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun onTitlesError(t: Throwable?) {

    }

    private fun onTitlesReceive(titles: LoginTitles?) {
        titleText.set(titles?.title)
        subtitleText = titles?.subtitle
    }

}
