package com.android.topbooks.data.injection.component

import com.android.topbooks.data.injection.module.NetworkModule
import com.android.topbooks.model.request.LoadBooksRequest
import dagger.Component
import javax.inject.Singleton

// ****************************************************************************************************
// @Singleton - Identifies a type that the injector only instantiates once. Not inherited.
// put view model functions here or whatever functions you want to inject.
// @Component - Annotates an interface or abstract class for which a fully-formed, dependency-injected
// implementation is to be generated from a set of modules. The generated class will have the name of
// the type annotated with @Component prepended with Dagger.
// ****************************************************************************************************
@Singleton
@Component(modules = [(NetworkModule::class)])
interface BaseInjector {
    // ****************************************************************************************************
    // This just creates a function that our injection class can use to inject our request. You can also
    // put view model functions here or whatever functions you want to inject.
    // ****************************************************************************************************
    fun inject(loadBooksRequest: LoadBooksRequest)

    @Component.Builder
    interface Builder {
        // ****************************************************************************************************
        // Here is where we create our functions that our Injection Class might need. They are build() and
        // networkModule(networkModule: NetworkModule).
        // ****************************************************************************************************
        fun build(): BaseInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}