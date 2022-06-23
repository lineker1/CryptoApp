package me.dio.cryptoapp.model

import com.google.gson.annotations.SerializedName

data class RequestCrytos(
    val coins : List<CryptoCurrency>
)

data class CryptoCurrency(
    val id : String,
    val icon: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val priceBt : Int,
    val totalSupply: Double,
    @SerializedName("priceChange1h") val changeLastHour: Double,
    @SerializedName("priceChange1d") val changeLastDay : Double,


)