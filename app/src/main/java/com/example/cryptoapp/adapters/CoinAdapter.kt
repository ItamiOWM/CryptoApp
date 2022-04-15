package com.example.cryptoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.pojo.CoinInfo
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvUpdateTime: TextView = itemView.findViewById(R.id.tvUpdateTime)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_coin_info, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            tvSymbols.text = coin.fromsymbol + " / " + coin.tosymbol
            tvPrice.text = coin.price.toString()
            tvUpdateTime.text = coin.getFormattedTime()
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoin)
            itemView.setOnClickListener { onCoinClickListener?.onCoinClick(coin) }
        }
    }

    override fun getItemCount(): Int = coinInfoList.size

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}