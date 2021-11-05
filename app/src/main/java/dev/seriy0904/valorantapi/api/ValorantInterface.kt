package dev.seriy0904.valorantapi.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ValorantInterface {
    @GET("/v1/agents")
    @Headers("Content-type: application/json")
    suspend fun getAgents(@Query("language") lang:String = "ru-RU"): ValorantModel

    @GET("/v1/maps")
    @Headers("Content-type: application/json")
    suspend fun getMaps(@Query("language") lang:String = "ru-RU"): ValorantMapModel
}