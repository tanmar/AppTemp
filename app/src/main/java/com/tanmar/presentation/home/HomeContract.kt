package com.tanmar.presentation.home

import com.tanmar.data.github.RepoEntity
import com.tanmar.presentation.base.BaseView

interface HomeContract {
    interface View : BaseView {
        fun showUserRepositories(repositories: List<RepoEntity>)
    }
}