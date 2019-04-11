package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.model.UserDetails
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


class LoginViewModel : BaseViewModel() {
    var nameText = MutableLiveData<String>()

    val lastNameText = MutableLiveData<String>()

    val avatarUrl = MutableLiveData<String>()

    val isProgressVisible = MutableLiveData<Boolean>()

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadUrl(view: ImageView, imageUrl: String?) {
            Log.d("loadUrl", "avatarUrl: $imageUrl")
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }

    override fun start(){
        showProgress()
        disposables.add(ProjectsRepository.getLoginTitles()
            .compose(RxUtil.ioSingle())
            .doFinally { hideProgress() }
            .subscribe({titles ->  onUserDetailsReceive(titles)}, { t -> onUserDetailsError(t) }))
    }


    private fun onUserDetailsError(t: Throwable?) {

    }

    private fun onUserDetailsReceive(titles: UserDetails?) {
        nameText.value = titles?.name
        lastNameText.value = titles?.lastName
        avatarUrl.value = titles?.avatarUrl
    }

    private fun showProgress(){
        isProgressVisible.value = true
    }

    private fun hideProgress(){
        isProgressVisible.value = false
    }
}
