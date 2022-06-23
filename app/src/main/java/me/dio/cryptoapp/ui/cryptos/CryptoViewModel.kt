package me.dio.cryptoapp.ui.cryptos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.dio.cryptoapp.base.Resource
import me.dio.cryptoapp.model.CryptoCurrency
import me.dio.cryptoapp.repository.CryptoRepository

class CryptoViewModel(
    private val cryptoRepository: CryptoRepository
) : ViewModel(){


    private val _cryptos = MutableLiveData<Resource<List<CryptoCurrency>>>()
    val cryptos : LiveData<Resource<List<CryptoCurrency>>> get() = _cryptos
    
    fun fetchCryptos(){
        viewModelScope.launch {
            _cryptos.value = Resource.Loading()
            _cryptos.value = cryptoRepository.getCryptos()
        }
    }
}