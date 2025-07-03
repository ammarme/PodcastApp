package com.ammar.thmanyah.core.network

import com.ammar.thmanyah.core.model.HomeSectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://api-v2-b2sit6oh3a-uc.a.run.app/home_sections")
    suspend fun getHomeSections(@Query("page") page: Int): HomeSectionResponse


    @GET("https://mock.apidog.com/m1/735111-711675-default/search")
    suspend fun search(@Query("search") searchWord: String): HomeSectionResponse


}