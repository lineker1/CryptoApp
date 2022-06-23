package me.dio.cryptoapp.repository

import me.dio.cryptoapp.api.CryptoApi
import me.dio.cryptoapp.base.Resource
import me.dio.cryptoapp.model.CryptoCurrency
import java.lang.Exception

class CryptoRepository (private val cryptoApi: CryptoApi){

    suspend fun getCryptos() : Resource<List<CryptoCurrency>>{
        return try {
            val response = cryptoApi.fetchCryptos()
            Resource.Success(response.coins)
        } catch (ex: Exception){
            Resource.Error(ex)
        }


    }

}