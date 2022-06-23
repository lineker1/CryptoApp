package me.dio.cryptoapp.api

import me.dio.cryptoapp.model.CryptoCurrency
import me.dio.cryptoapp.model.RequestCrytos
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins")
    suspend fun fetchCryptos(
        @Query("skip") skip: String = "0",
        @Query("limit") limit: String = "10",
        @Query("currency") currency: String = "BRL"
    ): RequestCrytos
}