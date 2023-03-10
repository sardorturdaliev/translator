package com.sardordev.translator.data.api

import com.sardordev.translator.data.model.BodyTranslate
import com.sardordev.translator.data.model.ResultTranslated
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransApi {

    @POST("translates")
    @Headers(
        "X-RapidAPI-Key: 4fbd24ac9emsh13ea8202bfd021bp1ba7c7jsn78d076cf4bb6",
        "X-RapidAPI-Host: ai-translate.p.rapidapi.com"
    )
    suspend fun translateWords(@Body bodyTranslate: BodyTranslate): Response<List<ResultTranslated>>


}