package me.dio.cryptoapp

import me.dio.cryptoapp.api.CryptoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val dependenciesModule = module {
        single { provideRetrofit(get()) }
        single { provideOkHttpClient() }
        single { provideApi(get()) }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return okHttpClient.build()
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.coinstats.app/public/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    private fun provideApi(retrofit: Retrofit) : CryptoApi{
        return retrofit.create(CryptoApi::class.java)
    }
}