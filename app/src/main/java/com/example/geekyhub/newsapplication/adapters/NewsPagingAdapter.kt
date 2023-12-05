package com.example.geekyhub.newsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geekyhub.newsapplication.BR
import com.example.geekyhub.newsapplication.databinding.ListItemBinding
import com.example.geekyhub.newsapplication.paging.NewsPagingSource
import com.example.geekyhub.newsapplication.retrofit.Article
import kotlinx.android.synthetic.main.list_item.view.*

class NewsPagingAdapter:PagingDataAdapter<Article,NewsPagingAdapter.MyViewHolder>(DIFF_UTIL) {

     companion object {
         val DIFF_UTIL= object:DiffUtil.ItemCallback<Article>(){
             override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
              return oldItem==newItem
             }

             override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                 return oldItem==newItem
             }

         }
     }

     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val item=getItem(position)
         holder.viewDataBinding.setVariable(BR.article,item)
         Glide.with(holder.viewDataBinding.root).load(item?.urlToImage).into(holder.viewDataBinding.root.image_list_item)
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val binding=ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return MyViewHolder(binding)
     }
    inner class MyViewHolder (val viewDataBinding: ViewDataBinding):RecyclerView.ViewHolder(viewDataBinding.root){

     }

 }
