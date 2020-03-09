package jp.digital.future.wearSupporter

import android.app.Application

interface AppLifecycleCallbacks {

    fun onCreate(application: Application)

    fun onTerminate(application: Application)
}