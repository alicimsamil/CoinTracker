package com.alicimsamil.cointracker.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.alicimsamil.cointracker.core.network.BuildConfig
import com.alicimsamil.cointracker.core.network.service.CoinService


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (!BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.NONE
            } else {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val collector = ChuckerCollector(
            context = context,
            showNotification = true
        )
        return ChuckerInterceptor.Builder(context)
            .collector(collector)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .writeTimeout(timeout = WRITE_TIMEOUT, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = READ_TIMEOUT, unit = TimeUnit.SECONDS)
            .connectTimeout(timeout = CONNECT_TIMEOUT, unit = TimeUnit.SECONDS)
            .callTimeout(timeout = CALL_TIMEOUT, unit = TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BuildConfig.SERVICE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideHarmonyHubService(retrofit: Retrofit): CoinService {
        return retrofit.create(CoinService::class.java)
    }

    private const val WRITE_TIMEOUT = 60L
    private const val READ_TIMEOUT = 60L
    private const val CONNECT_TIMEOUT = 60L
    private const val CALL_TIMEOUT = 60L

}