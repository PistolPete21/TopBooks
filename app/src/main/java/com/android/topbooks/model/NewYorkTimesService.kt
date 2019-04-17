package com.android.topbooks.model

import com.android.topbooks.model.response.Response
import com.android.topbooks.utility.POPULAR_BOOK_URL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewYorkTimesService {
    @GET("$POPULAR_BOOK_URL.json")
    fun getBooks(@Query("api-key",encoded = true)apiKey: String): Observable<Response>
}