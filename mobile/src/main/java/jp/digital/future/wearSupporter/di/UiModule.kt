package jp.digital.future.wearSupporter.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.digital.future.wearSupporter.GithubActivity
import jp.digital.future.wearSupporter.di.modules.GithubDispatcherModule
import jp.digital.future.wearSupporter.di.modules.GithubModule

@Module
internal abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): StoreProvider

    @PerActivityScope
    @ContributesAndroidInjector(modules = [GithubModule::class, GithubDispatcherModule::class])
    internal abstract fun contributeGithubActivity(): GithubActivity

}