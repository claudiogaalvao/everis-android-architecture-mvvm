package br.com.everis.androidmvvm.infrastructure.network.model

data class ApiResponse(
    val message: String,
    val number: Int,
    val people : List<People>

)
