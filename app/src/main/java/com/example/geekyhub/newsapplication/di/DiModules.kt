package com.example.geekyhub.newsapplication.di

import com.example.geekyhub.newsapplication.NewsRepository
import com.example.geekyhub.newsapplication.retrofit.NewsInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModules {
    // Your Dagger module code goes here
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Singleton
    @Provides
    fun providesNewsInterface(retrofit: Retrofit):NewsInterface{
        return retrofit.create(NewsInterface::class.java)


    }
    @Singleton
    @Provides
    fun provideRepository(newsInterface: NewsInterface):NewsRepository{
        return NewsRepository(newsInterface)
    }
}
