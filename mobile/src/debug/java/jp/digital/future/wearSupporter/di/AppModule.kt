package jp.digital.future.wearSupporter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import jp.digital.future.wearSupporter.App
import jp.digital.future.wearSupporter.AppLifecycleCallbacks
import jp.digital.future.wearSupporter.data.DataModule
import jp.digital.future.wearSupporter.data.GithubLoginModule
import javax.inject.Singleton


@Module(includes = [GithubLoginModule::class, DataModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks =
        DebugAppLifecycleCallbacks()


    @Singleton
    @Provides
    fun provideSharedPreference(app:App): SharedPreferences = app.getSharedPreferences("Default", Context.MODE_PRIVATE)


}