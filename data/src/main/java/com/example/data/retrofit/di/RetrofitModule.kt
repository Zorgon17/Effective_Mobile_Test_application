package com.example.data.retrofit.di

import com.example.data.retrofit.remoteApi.ApiService
import com.example.data.retrofit.remoteApi.ApiServiceRepositoryImpl
import com.example.domain.repository.Repository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    // Предоставление OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    // Предоставление Retrofit
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/u/0/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()

    // Предоставление интерфейса ServerApiService
    @Provides
    @Singleton
    fun provideServerApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    // Предоставление ServerRepository
    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
    ): Repository = ApiServiceRepositoryImpl(apiService)
}