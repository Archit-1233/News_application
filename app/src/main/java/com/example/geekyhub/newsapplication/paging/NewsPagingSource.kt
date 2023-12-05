package com.example.geekyhub.newsapplication.paging

import android.net.http.HttpException
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.geekyhub.newsapplication.Constants
import com.example.geekyhub.newsapplication.retrofit.Article
import com.example.geekyhub.newsapplication.retrofit.NewsInterface
import java.io.IOException

const val STARTING_INDEX = 1
class NewsPagingSource(val newsInterface: NewsInterface):PagingSource<Int,Article>() {
    @RequiresApi(34)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
       val postition=params.key?: STARTING_INDEX
        return try{
            val data=newsInterface.getAllNews("in","business", Constants.API_KEY,postition,params.loadSize)
            LoadResult.Page(
                data=data.articles,
                prevKey = if (params.key== STARTING_INDEX) null else postition-1,
                nextKey = if (data.articles.isEmpty())null else postition+1
            )
        } catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }

}