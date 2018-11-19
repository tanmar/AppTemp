package com.tanmar.presentation.home

import com.tanmar.data.github.GithubRepository
import com.tanmar.presentation.base.BaseErrorHandler
import com.tanmar.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val githubRepository: GithubRepository,
    override val errorHandler: BaseErrorHandler<HomeContract.View>
) : BasePresenter<HomeContract.View>() {

    override fun onAttachView(view: HomeContract.View) {
        super.onAttachView(view)
        githubRepository.getGithubRepository("tanmar")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { list ->
                    view.showUserRepositories(list)
                },
                onError = { it.printStackTrace() }
            )

    }
}

