package me.dio.cryptoapp.ui.cryptos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.dio.cryptoapp.R
import me.dio.cryptoapp.base.Resource
import me.dio.cryptoapp.ui.cryptos.adapter.CryptoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class CryptoFragment : Fragment() {

    private val viewModel : CryptoViewModel by viewModel()

    private lateinit var rvCryptos : RecyclerView
    private lateinit var progressBar : ProgressBar
    private val cryptoAdapter = CryptoAdapter(){
        Toast.makeText(context,it.name,Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCryptos = view.findViewById(R.id.rvCryptos)
        progressBar = view.findViewById(R.id.progressBar)

        rvCryptos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cryptoAdapter
        }

        viewModel.fetchCryptos()
        viewModel.cryptos.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error ->{
                    progressBar.isVisible = false
                    Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> progressBar.isVisible = true
                is Resource.Success ->{
                    progressBar.isVisible = false
                    it.data?.let { cryptos -> cryptoAdapter.items = cryptos }
                }
            }
        }
    }
}