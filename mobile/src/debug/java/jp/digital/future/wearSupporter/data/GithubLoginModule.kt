package jp.digital.future.wearSupporter.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import jp.digital.future.wearSupporter.data.api.GithubLoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class GithubLoginModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()


    @Singleton
    @Provides
    fun provideGithubLoginRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(oktHttpClient)
        .baseUrl("https://github.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideGitHubLoginService(retrofit: Retrofit): GithubLoginService =
        retrofit.create(GithubLoginService::class.java)

//    @Singleton
//    @Provides
//    fun provideGitHubService(retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)
}