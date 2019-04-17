package com.android.topbooks.data.injection.module

import com.android.topbooks.model.NewYorkTimesService
import com.android.topbooks.utility.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

// ****************************************************************************************************
// This annotation tells dagger that this is a module object that we want to use.
// ****************************************************************************************************
@Module
object NetworkModule {

    // ****************************************************************************************************
    // These annotations allow Dagger to be able to inject these methods and allow them to be used in our
    // LoadBooksRequest class.
    // ****************************************************************************************************
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideBookApi(retrofit: Retrofit): NewYorkTimesService {
        return retrofit.create(NewYorkTimesService::class.java)
    }

    // ****************************************************************************************************
    // These annotations allow Dagger to be able to inject these methods and allow them to be used in our
    // LoadBooksRequest class.
    // ****************************************************************************************************
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}