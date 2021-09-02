package com.somosmas.somosmas

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getListSlider(@Url url:String):Response<SlideResponse>
    @GET
    suspend fun getListSliderTestimony(@Url url:String):Response<TestimonyResponse>
}