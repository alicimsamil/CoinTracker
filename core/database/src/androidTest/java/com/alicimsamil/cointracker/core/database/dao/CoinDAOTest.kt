package com.alicimsamil.cointracker.core.database.dao


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alicimsamil.cointracker.core.database.db.CoinTrackerDatabase
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CoinDAOTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var coinDatabase: CoinTrackerDatabase

    private lateinit var coinDAO: CoinDAO

    @Before
    fun setup() {
        hiltRule.inject()
        coinDAO = coinDatabase.coinDAO()
    }

    @Test
    fun note_should_be_insert_correctly() = runBlocking {
        val coins = listOf(
            CoinDBModel(
                "01coin", "01coin", "zoc", 1
            ),
            CoinDBModel(
                "bitcoin", "bitcoin", "btc", 2
            ),
            CoinDBModel(
                "ada", "ada coin", "ada", 3
            )
        )
        coinDAO.addCoin(coins)

        assertTrue(coins.size == coinDAO.getRowCount())
    }


    @After
    fun teardown() {
        coinDatabase.close()
    }

}
