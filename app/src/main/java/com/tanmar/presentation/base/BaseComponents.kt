package com.tanmar.presentation.base

import android.content.Context
import android.widget.Toast
import androidx.annotation.CallSuper
import dagger.android.support.DaggerFragment

abstract class BasePresenter<V : BaseView> {

    abstract val errorHandler: ErrorHandler<V>

    protected var view: V? = null

    @CallSuper
    open fun onAttachView(view: V) {
        this.view = view
        errorHandler.attachView(view)
    }

    @CallSuper
    open fun onDetachView() {
        this.view = null
        errorHandler.detachView()
    }
}

interface ErrorHandler<T : BaseView> {

    fun handle(throwable: Throwable)

    fun attachView(view: T)

    fun detachView()
}

interface BaseView {

    fun showError(msg: String)

    fun showGenericError()
}

interface ActivityController {

    fun hideKeyboard()

    fun navigateUp()
}

abstract class BaseFragment : DaggerFragment(), BaseView {

    private var errorToast: Toast? = null

    protected var activityController: ActivityController? = null

    open fun onBackPressed(): Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val parentActivity = activity
        if (parentActivity is ActivityController) {
            activityController = parentActivity
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityController = null
    }

    override fun showError(msg: String) {
        this.view?.let {
            if (!isErrorToastShown()) {
                errorToast = Toast.makeText(it.context, msg, Toast.LENGTH_SHORT)
                errorToast?.show()
            }
        }
    }

    override fun showGenericError() {
        this.view?.let {
            if (!isErrorToastShown()) {
                errorToast = Toast.makeText(it.context, R.string.something_went_wrong, Toast.LENGTH_LONG)
                errorToast?.show()
            }

        }
    }

    private fun isErrorToastShown(): Boolean {
        val toast = errorToast
        return toast != null && toast.view.isShown
    }

}