package com.example.tweets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweets.ui.screens.CategoryScreen
import com.example.tweets.ui.screens.DetailScreen
import com.example.tweets.ui.theme.TweetsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categories") {
        composable(route = "categories") {
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category"){
                type=NavType.StringType
            }
        )) {
            DetailScreen()
        }
    }
}

@Composable
fun RegistrationScreen(onClick:(Pair<String,Int>)->Unit) {
    Text(
        text = "Registration Screen",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .clickable {
                onClick(Pair("umairhanif277", 814))
            }
            .padding(50.dp))
}

@Composable
fun LoginScreen() {
    Text(text = "Login Screen", style = MaterialTheme.typography.headlineLarge)
}

@Composable
fun MainScreen(argument: String?, password: Int?) {
    Text(text = "Main Screen $argument $password", style = MaterialTheme.typography.headlineLarge)
}