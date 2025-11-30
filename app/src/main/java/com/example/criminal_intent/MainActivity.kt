package com.example.criminal_intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "crime_list"
                    ) {
                        composable("crime_list") {
                            CrimeListScreen(
                                onCrimeClick = { crimeId ->
                                    navController.navigate("crime_detail/${crimeId}")
                                }
                            )
                        }

                        composable(
                            route = "crime_detail/{crimeId}",
                            arguments = listOf(
                                navArgument("crimeId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val crimeIdString = backStackEntry.arguments?.getString("crimeId")
                            val crimeId = UUID.fromString(crimeIdString)
                            CrimeDetailScreen(
                                crimeId = crimeId,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}