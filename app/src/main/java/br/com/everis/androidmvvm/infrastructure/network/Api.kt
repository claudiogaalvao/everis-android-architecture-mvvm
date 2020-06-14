package br.com.everis.androidmvvm.infrastructure.network

import br.com.everis.androidmvvm.infrastructure.network.model.ApiResponse
import retrofit2.http.GET

interface Api {
    /**
     * Retorna a quantidade de pessoas no espaço
     */
    @GET("astros.json")
    suspend fun fetchPeopleInSpace() : ApiResponse
}