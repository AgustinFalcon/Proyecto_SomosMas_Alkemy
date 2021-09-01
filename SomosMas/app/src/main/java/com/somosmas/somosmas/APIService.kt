package com.somosmas.somosmas

import com.somosmas.somosmas.homelastnews.LastNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getListSlider(@Url url:String): Response<SlideResponse>

    @GET
    fun getListLastNews(@Url url:String): Response<LastNewsResponse>

}