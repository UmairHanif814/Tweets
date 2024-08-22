package com.example.tweets.repository

import com.example.tweets.api.TweetApi
import com.example.tweets.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetApi: TweetApi) {
    private val _categories= MutableStateFlow<List<String>>(emptyList())
    val categories:StateFlow<List<String>> get() = _categories

    private val _tweets=MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets:StateFlow<List<TweetListItem>> get() = _tweets

    suspend fun getCategories(){
        val response=tweetApi.getCategories()
        if (response.isSuccessful && response.body()!=null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String){
        val response=tweetApi.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body()!=null){
            _tweets.emit(response.body()!!)
        }
    }
}