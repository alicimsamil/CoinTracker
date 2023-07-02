package com.alicimsamil.cointracker.feature.listing.usecase

import androidx.paging.map
import com.alicimsamil.cointracker.core.network.model.CoinMarketData
import com.alicimsamil.cointracker.core.network.model.Sparkline
import com.alicimsamil.cointracker.feature.listing.domain.usecase.GetCoinsFromRemoteUseCase
import com.alicimsamil.cointracker.feature.listing.repository.FakeCoinsRepositoryImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.map
import org.junit.Before
import org.junit.Test

class GetCoinsFromRemoteUseCaseTest {

    private lateinit var repository: FakeCoinsRepositoryImpl
    private lateinit var useCase: GetCoinsFromRemoteUseCase
    private lateinit var coinModel: CoinMarketData

    @Before
    fun setup() {
        repository = FakeCoinsRepositoryImpl()
        useCase = GetCoinsFromRemoteUseCase(repository)
        coinModel = CoinMarketData(
            ath = 1000.0,
            athChangePercentage = 10.0,
            athDate = "10-05-2000",
            atl = 1.0,
            atlChangePercentage = -10.0,
            atlDate = "10-05-2000",
            circulatingSupply = 1.0,
            currentPrice = 55.0,
            fullyDilutedValuation = 902183L,
            high24h = 56.0,
            id = "bitcoin",
            image = "www.google.com",
            lastUpdated = "10-05-2120",
            low24h = 54.0,
            marketCap = 8923759723958L,
            marketCapChange24h = 1240.124,
            marketCapChangePercentage24h = 0.124,
            marketCapRank = 1,
            maxSupply = 100.0,
            name = "bitcoin",
            priceChange24h = 28937.0,
            priceChangePercentage24h = 0.8979,
            priceChangePercentage24hInCurrency = 0.9238042,
            sparklineIn7d = Sparkline(listOf(0.0, 0.5, 0.10)),
            symbol = "btc",
            totalSupply = 28937589273957.0,
            totalVolume = 128549801285L
        )
    }

    @Test
    fun invoke_test(){
        useCase.invoke().map { it.map { assertEquals(coinModel.name, it.name) } }
    }


    @Test
    fun get_4h_by_7day_data_should_return_4_hour_data() {
        val input = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val expectedData = listOf(1.0, 5.0)
        assertEquals(expectedData, useCase.get4hBy7DayData(input))

        val expectedOutput = emptyList<Double>()
        assertEquals(expectedOutput, useCase.get4hBy7DayData(null))
    }
}