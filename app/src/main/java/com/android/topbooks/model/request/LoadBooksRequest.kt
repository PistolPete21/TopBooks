package com.android.topbooks.model.request

import com.android.topbooks.BuildConfig
import com.android.topbooks.data.injection.InjectionClass
import com.android.topbooks.model.NewYorkTimesService
import com.android.topbooks.model.response.Response
import com.android.topbooks.ui.activity.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoadBooksRequest(private val activity: MainActivity) : InjectionClass() {
    // ****************************************************************************************************
    // Here we add the @Inject annotation to take advantage of our methods that exist in the Network Module
    // and the NewYorkTimesService. We can now access the methods to any of the other modules that we need.
    // ****************************************************************************************************
    @Inject
    lateinit var newYorkTimesService: NewYorkTimesService

    private lateinit var disposable: Disposable

    // ****************************************************************************************************
    // This method uses Retrofit 2 to make the network call and the data comes back in four different
    // states: onRequestSuccess, on RequestStarted, onRequestFinished, and onRequestFailure. This network
    // call utilizes dependency injection and rx java to handle any scenario where the API call may have a
    // lot more load and you may want to observe changes from the activity or view model.
    // The line below represents what the network call would look like without dependency injection.
    //
    // NetworkModule.provideBookApi(NetworkModule.provideRetrofitInterface()).getBooks(BuildConfig.ApiKey)
    // ****************************************************************************************************

    fun loadBooksByCategories() {
        disposable = newYorkTimesService.getBooks(BuildConfig.ApiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRequestStarted() }
            .doOnTerminate { onRequestFinished() }
            .subscribe(
                { result -> onRequestSuccess(result) },
                { onRequestFailure() }
            )
    }

    private fun onRequestStarted() {
        activity.dataLoading()
    }

    private fun onRequestFinished() {
        activity.dataFinished()
    }

    // ****************************************************************************************************
    // Our network call was successful let's handle that data in the main activity.
    // ****************************************************************************************************
    private fun onRequestSuccess(response: Response) {
        activity.dataFinished()
        activity.updateAdapter(response)
    }

    // ****************************************************************************************************
    // Our network call has error'ed let's handle that in our main activity.
    // ****************************************************************************************************
    private fun onRequestFailure() {
        activity.dataError()
    }
}