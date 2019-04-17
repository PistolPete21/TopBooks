package com.android.topbooks.data.injection

import com.android.topbooks.data.injection.component.BaseInjector
import com.android.topbooks.data.injection.component.DaggerBaseInjector
import com.android.topbooks.data.injection.module.NetworkModule
import com.android.topbooks.model.request.LoadBooksRequest

abstract class InjectionClass {
    // ****************************************************************************************************
    // The InjectionClass is where the core of the DI starts. First we set our BaseInjector to
    // DaggerBaseInjector.builder() to build up our BaseInjector, pass the networkModule along and finally
    // call build().
    // ****************************************************************************************************
    private val injector: BaseInjector = DaggerBaseInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    // ****************************************************************************************************
    // Init block is similar to constructor pattern in Java so just call the constructor anytime
    // InjectionClass is called.
    // ****************************************************************************************************
    init {
        inject()
    }

    // ****************************************************************************************************
    // Inject in the dependencies that are in our BaseInjector that we need for the project.
    // ****************************************************************************************************
    private fun inject() {
        injector.inject(this as LoadBooksRequest)
    }
}