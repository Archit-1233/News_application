package com.example.geekyhub.newsapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingSource
import com.example.geekyhub.newsapplication.NewsViewModel
import com.example.geekyhub.newsapplication.R
import com.example.geekyhub.newsapplication.adapters.NewsPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.view.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()
    private val newsPagingAdapter=NewsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.list.observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(lifecycle,it)
        }

        newsPagingAdapter.addLoadStateListener {state->
            when(state.refresh){
                is LoadState.Loading->{
                    view.news_progress.visibility=View.VISIBLE
                }
                is LoadState.NotLoading->{
                    view.news_progress.visibility=View.GONE
                }
                is LoadState.Error->{
                    view.news_progress.visibility=View.GONE
                    Toast.makeText(requireContext(),"Error occured",Toast.LENGTH_SHORT).show()
                }

            }
        }
        view.news_recycler.adapter=newsPagingAdapter
    }

    companion object {

            }
    }
