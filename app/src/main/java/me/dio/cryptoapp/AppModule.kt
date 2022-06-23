package me.dio.cryptoapp

import me.dio.cryptoapp.repository.CryptoRepository
import me.dio.cryptoapp.ui.cryptos.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val dependenciesModule = module {
        viewModel<CryptoViewModel>{ CryptoViewModel(get()) }
        single { CryptoRepository(get()) }
    }
}