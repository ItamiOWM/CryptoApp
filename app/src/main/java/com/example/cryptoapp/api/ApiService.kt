package com.example.cryptoapp.api

import com.example.cryptoapp.pojo.CoinInfoListOfData
import com.example.cryptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TSYM) tsym: String = CURRENCY,
        ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "5e34ac43585c4fecee4cd15029ecb5e39ef573039d9f8b9b2d85d01228e2e447",
        @Query(QUERY_PARAM_FSYMS) fsyms: String,
        @Query(QUERY_PARAM_TSYMS) tsyms: String = CURRENCY,
        ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TSYM = "tsym"
        private const val QUERY_PARAM_TSYMS = "tsyms"
        private const val QUERY_PARAM_FSYMS = "fsyms"
        private const val QUERY_PARAM_API_KEY = "api_key"

        private const val CURRENCY = "USD"
    }

}