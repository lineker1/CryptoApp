package me.dio.cryptoapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@CryptoApplication)
            modules(listOf(
                AppModule.dependenciesModule,
                NetworkModule.dependenciesModule
            ))
        }
    }
}