package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var tvPrice: TextView
    private lateinit var tvMax: TextView
    private lateinit var tvMin: TextView
    private lateinit var tvLastDeal: TextView
    private lateinit var tvTimeUpdated: TextView
    private lateinit var tvTSyms: TextView
    private lateinit var tvFSyms: TextView
    private lateinit var ivLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        tvPrice = findViewById(R.id.tvPrice)
        tvLastDeal = findViewById(R.id.tvLastDeal)
        tvMax = findViewById(R.id.tvMax)
        tvMin = findViewById(R.id.tvMin)
        tvTimeUpdated = findViewById(R.id.tvTimeUpdated)
        tvFSyms = findViewById(R.id.tvFSyms)
        tvTSyms = findViewById(R.id.tvTSyms)
        ivLogo = findViewById(R.id.ivLogoCoin)
        val fsym = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(CoinViewModel::class.java)
        viewModel.getDetailInfo(fsym.toString()).observe(this, Observer {
            tvPrice.text = it.price.toString()
            tvLastDeal.text = it.lastmarket
            tvMax.text = it.highday.toString()
            tvMin.text = it.lowday.toString()
            tvTimeUpdated.text = it.getFormattedTime()
            tvTSyms.text = it.tosymbol
            tvFSyms.text = it.fromsymbol
            Picasso.get().load(it.getFullImageUrl()).into(ivLogo)
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fsym"

        fun newIntent(context: Context, fsym: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fsym)
            return intent
        }
    }
}