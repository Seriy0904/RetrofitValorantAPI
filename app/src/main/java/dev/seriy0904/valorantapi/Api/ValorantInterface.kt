package dev.seriy0904.valorantapi.Api

import retrofit2.http.GET

interface ValorantInterface {
    @GET("/v1/agents")
    suspend fun getAgents(): ValorantModel
}