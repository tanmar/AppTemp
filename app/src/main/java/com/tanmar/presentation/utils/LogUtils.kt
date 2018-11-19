package com.tanmar.presentation.utils

import android.os.Looper
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.tanmar.BuildConfig
import timber.log.Timber
import java.util.regex.Pattern


object LogUtils {
    //source: Timber lib
    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

    private val tag = "SwitchDXB"

    private val tagFromStackTrace: String?
        get() {
            val stackTrace = Throwable().stackTrace
            return if (stackTrace.isNotEmpty()) createStackElementTag(stackTrace[0]) else null
        }

    private val methodNameFromStackTrace: String?
        get() {
            val stackTrace = Throwable().stackTrace
            return if (stackTrace.isNotEmpty()) createStackElementMethod(stackTrace[0]) else null
        }

    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    fun LOGD(message: String, vararg args: Any) {
        val tag = tagFromStackTrace
        if (tag != null) {
            Timber.tag(tag).d(message, args)
        } else {
            Timber.d(message, args)
        }

    }

    fun LOGD(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }

    fun LOGD(tag: String, message: String, cause: Throwable) {
        Timber.tag(tag).d(cause, message)

    }

    fun LOGV(tag: String, message: String) {
        Timber.tag(tag).v(message)
    }

    fun LOGV(tag: String, message: String, cause: Throwable) {
        Timber.tag(tag).v(cause, message)
    }


    fun LOGI(message: String) {
        Timber.tag(tagFromStackTrace).i(message)
    }

    fun LOGI(tag: String, message: String) {
        Timber.tag(tag).v(message)
    }

    fun LOGI(tag: String, message: String, cause: Throwable) {
        Timber.tag(tag).i(cause, message)
    }

    fun LOGW(tag: String, message: String) {
        Timber.tag(tag).w(message)
    }

    fun LOGW(message: String) {
        Timber.tag(tagFromStackTrace).w(message)
    }

    fun LOGW(tag: String, message: String, cause: Throwable) {
        Timber.tag(tag).w(cause, message)
    }

    fun LOGE(e: Throwable) {
        Timber.e(e)
    }

    fun LOGE(message: String, vararg args: Any) {
        Timber.e(message, args)
    }

    fun LOGE(tag: String, message: String) {
        Timber.tag(tag).e(message)
    }

    fun LOGE(throwable: Throwable, message: String) {
        Timber.e(throwable, message)
    }

    fun LOGE(tag: String, message: String, cause: Throwable) {
        Timber.tag(tag).e(cause, message)
    }

    fun <T> RxLOGD(t: T) {
        val stackTrace = Throwable().stackTrace
        var tag: String? = null
        if (stackTrace != null) {
            tag = createStackElementTag(stackTrace[1]) //ignore LogUtils class as possible TAG

        }
        if (tag != null) {
            Timber.tag(tag).d(t.toString())
        } else {
            Timber.d(t.toString())
        }

    }

    fun LOGthread() {
        val isMainThread = Looper.myLooper() == Looper.getMainLooper()
        val methodName = methodNameFromStackTrace
        val isMainThreadText = "--> " + methodName + ": Thread is " + if (isMainThread) "MAIN" else "BACKGROUND"
        LOGD(isMainThreadText)
    }

    /**
     * Extract the tag which should be used for the message from the `element`. By default
     * this will use the class name without any anonymous class suffixes (e.g., `Foo$1`
     * becomes `Foo`).
     *
     *
     * Note: This will not be called if a manual tag was specified.
     */
    private fun createStackElementTag(element: StackTraceElement): String {
        var tag = element.className
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        return tag.substring(tag.lastIndexOf('.') + 1)
    }

    private fun createStackElementMethod(element: StackTraceElement): String {
        var methodName = element.methodName
        val m = ANONYMOUS_CLASS.matcher(methodName)
        if (m.find()) {
            methodName = m.replaceAll("")
        }
        return methodName.substring(methodName.lastIndexOf('.') + 1)
    }


    private class CrashReportingTree : Timber.Tree() {

        private val CRASHLYTICS_KEY_PRIORITY = "priority"
        private val CRASHLYTICS_KEY_TAG = "tag"
        private val CRASHLYTICS_KEY_MESSAGE = "message"

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.ERROR) {
                Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority)
                Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag)
                Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message)
                if(t==null) {
                    Crashlytics.logException(Exception(message))
                } else {
                    Crashlytics.logException(t)
                }
            }
        }
    }
}
