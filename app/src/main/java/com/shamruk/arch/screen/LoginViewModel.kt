package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel;
import androidx.databinding.ObservableField
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.model.LoginTitles
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable
import android.R
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


class LoginViewModel : ViewModel() {
    var titleText = ObservableField<String>()

    private val _subtitleText = MutableLiveData<String>()
    val subtitleText: LiveData<String>
        get() = _subtitleText

    private val _avatarUrl = MutableLiveData<String>()
    val avatarUrl: LiveData<String>
        get() = _avatarUrl

    private val _isProgressVisible = MutableLiveData<Boolean>()
    val isProgressVisible: LiveData<Boolean>
        get() = _isProgressVisible

    private val disposables: CompositeDisposable = CompositeDisposable()

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadUrl(view: ImageView, imageUrl: String?) {
            Log.d("loadUrl", "url: $imageUrl")
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }

    fun start(){
        showProgress()
        disposables.add(ProjectsRepository.getLoginTitles()
            .compose(RxUtil.ioSingle())
            .doFinally { hideProgress() }
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
        _subtitleText.value = titles?.subtitle
        _avatarUrl.value = titles?.url
    }

    private fun showProgress(){
        _isProgressVisible.value = true
    }

    private fun hideProgress(){
        _isProgressVisible.value = false
    }
}
