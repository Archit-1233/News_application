package com.example.geekyhub.newsapplication

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.geekyhub.newsapplication.paging.NewsPagingSource
import com.example.geekyhub.newsapplication.retrofit.Article
import com.example.geekyhub.newsapplication.retrofit.NewsInterface

class NewsRepository(val newsInterface:NewsInterface) {

    fun getAllNewsStream():LiveData<PagingData<Article>> = Pager(
        config = PagingConfig(
            20,5, enablePlaceholders = false
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsInterface)
        }
    ).liveData
}