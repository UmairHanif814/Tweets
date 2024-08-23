package com.example.tweets.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweets.viewmodels.DetailViewModel

@Composable
fun DetailScreen() {
    val detailsViewModel:DetailViewModel= hiltViewModel()
    val tweet=detailsViewModel.tweets.collectAsState()
    if (tweet.value.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center){
            Text(text = "Loading...")
        }
    }else{
        LazyColumn {
            items(tweet.value){
                TweetListItem(tweet = it.text)

            }
        }
    }
}


@Composable
fun TweetListItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, color = Color.Gray)
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}