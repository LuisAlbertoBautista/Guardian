package com.example.pruebantt.di

import android.app.Application
import android.content.Context
import com.example.pruebantt.connectivity.api.ConnectionModel
import com.example.pruebatecnicaapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ConnectionModel {
        return retrofit.create(ConnectionModel::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .followRedirects(false)

        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }

        clientBuilder.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
        return clientBuilder.build()
    }



    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {}
        interceptors.add(loggingInterceptor)
        return interceptors
    }

}