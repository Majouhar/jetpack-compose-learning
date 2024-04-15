package com.majou.composelearning.ui

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.majou.composelearning.ui.details.MealDetailsViewModel
import com.majou.composelearning.ui.details.MealsDetailsScreen
import com.majou.composelearning.ui.meals.MealsCategoryViewModel
import com.majou.composelearning.ui.theme.ComposeLearningTheme
import com.majou.model.responses.MealResponse


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                // A surface container using the 'background' color from the theme

              FoodiezApp()

            }
        }
    }
}

@Composable
fun MealsCategoriesScreen(navigationCallBack:(String) -> Unit = {}) {
    val viewModel: MealsCategoryViewModel = viewModel()
    val meals = viewModel.mealState.value
//    val rememberedMeals = remember {
//        mutableStateOf<List<MealResponse>>(emptyList())
//    }
//    val coroutineScope = rememberCoroutineScope()
//    LaunchedEffect(key1 = "GET_MEALS" ){
//        coroutineScope.launch(Dispatchers.IO) {
//            rememberedMeals.value = viewModel.getMeals()
//        }
//    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(meals) {
            MealCategory(meal = it,navigationCallBack)
        }
    }
}


@Composable
fun MealCategory(meal: MealResponse,navigationCallBack: (String) -> Unit = {})  {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { navigationCallBack(meal.id) }
    ) {
        val isExpanded = remember { mutableStateOf(false) }
        Row(modifier = Modifier.animateContentSize()) {

            Image(
                painter = rememberAsyncImagePainter(meal.imageURL),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text = meal.name, style = MaterialTheme.typography.headlineSmall)
                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.38f
                    )
                ) {
                    Text(
                        text = meal.description,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded.value) 10 else 5,
                        textAlign = TextAlign.Start
                    )
                }

            }
            Icon(
                imageVector = if (isExpanded.value)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand Row Icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { isExpanded.value = !isExpanded.value }
            )
        }
    }
}

@Composable
private fun FoodiezApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            MealsCategoriesScreen(){
                id ->
                navController.navigate("details/${id}")
                Log.e("TEST",id.toString())
            }
        }
        composable(
            route = "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val viewModel:MealDetailsViewModel = viewModel()
            MealsDetailsScreen(meal = viewModel.mealState.value!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLearningTheme {
        MealsCategoriesScreen()
    }
}

