package com.android.data.di


import android.content.Context
import com.android.data.BuildConfig
import com.android.data.source.remote.ApiService
import com.android.data.utils.Constants.BASE_URL
import com.android.data.utils.Constants.NETWORK_TIME_OUT
import com.android.data.utils.Constants.READ_TIME_OUT
import com.android.data.utils.Constants.WRITE_TIME_OUT
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideNetworkTimeOut() = NETWORK_TIME_OUT

    @Provides
    @Singleton
    fun provideWriteTimeOut() = WRITE_TIME_OUT

    @Provides
    @Singleton
    fun provideReadTimeOut() = READ_TIME_OUT

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        networkTimeout: Long,
        writeTimeout: Long,
        readTimeout: Long,
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()

        return if (BuildConfig.DEBUG) {

            interceptor.level = HttpLoggingInterceptor.Level.BODY
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS

            client
                .cache(mCache) // Make your app offline-friendly without a database!
                .connectTimeout(networkTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    /* If there is Internet, get the cache that was stored 5 seconds ago.
                    * If the cache is older than 5 seconds, then discard it,
                    * and indicate an error in fetching the response.
                    * The 'max-age' attribute is responsible for this behavior.
                    */
                    request = if (true) request.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 5).build()
                    /*If there is no Internet, get the cache that was stored 7 days ago.
                     * If the cache is older than 7 days, then discard it,
                     * and indicate an error in fetching the response.
                     * The 'max-stale' attribute is responsible for this behavior.
                     * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                     */
                    else request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                    chain.proceed(request)
                }
            return client.build()
        } else {
            client.addInterceptor(interceptor)
                .readTimeout(networkTimeout, TimeUnit.SECONDS)
                .build()
        }

    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}