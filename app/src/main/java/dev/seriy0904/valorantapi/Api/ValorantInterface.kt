package dev.seriy0904.valorantapi.Api

import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantInterface {
    @GET("/v1/agents")
    suspend fun getAgents(@Query("language") lang:String = "ru-RU"): ValorantModel
}