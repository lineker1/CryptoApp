package me.dio.cryptoapp.ui.cryptos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.dio.cryptoapp.R
import me.dio.cryptoapp.model.CryptoCurrency

class CryptoAdapter(private val onClick: (CryptoCurrency) -> Unit) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    var items: List<CryptoCurrency> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CryptoViewHolder(itemView: View, private val onClick: (CryptoCurrency) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val textName: TextView = itemView.findViewById(R.id.crypto_name)
        private val textPrice: TextView = itemView.findViewById(R.id.crypto_price)
        private val textPriceChange: TextView = itemView.findViewById(R.id.crypto_price_change)
        private val textIcon: ImageView = itemView.findViewById(R.id.crypto_image)


        fun bind(crypto: CryptoCurrency) {
            textName.text = crypto.name
            textPrice.text = crypto.price.toString()
            textPriceChange.text = crypto.changeLastDay.toString()
            Glide.with(itemView.context).load(crypto.icon).into(textIcon)

            itemView.setOnClickListener{ onClick.invoke(crypto) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_crypto_item, parent, false)
        return CryptoViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}