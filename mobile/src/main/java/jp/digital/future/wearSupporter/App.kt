package jp.digital.future.wearSupporter

import dagger.android.support.DaggerApplication
import jp.digital.future.wearSupporter.di.AppModule
import jp.digital.future.wearSupporter.di.DaggerAppComponent
import jp.digital.future.wearSupporter.di.applyAutoInjector
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()


    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()

        appLifecycleCallbacks.onCreate(this)
    }

    override fun onTerminate() {
        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }



}