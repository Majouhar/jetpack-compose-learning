package com.majou.composelearning.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberAsyncImagePainter
import coil.transform.CircleCropTransformation
import com.majou.model.responses.MealResponse
import java.lang.Float.min


@Composable
fun MealsDetailsScreen(meal: MealResponse) {
    val scrollState = rememberLazyListState()
    val offset = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    val profilePictureState = remember { mutableStateOf(MealProfilePictureState.Normal) }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    val imageSize = transition.animateDp(targetValueByState = { it.value.size }, label = "")
    val color = transition.animateColor(label = "") {
        it.value.color
    }
    val size = animateDpAsState(targetValue = max(100.dp, 140.dp * offset))
    val width = transition.animateDp(targetValueByState = { it.value.borderWidth }, label = "")
    Surface() {
        Column() {
            Surface() {
              Column() {
                  Row() {
                      Card(
                          shape = CircleShape,
                          border = BorderStroke(width = width.value, color = color.value)
                      ) {
                          Image(
                              painter = rememberAsyncImagePainter(model = meal.imageURL),
                              contentDescription = null,
                              modifier = Modifier.size(size.value)

                          )

                      }
                      Text(text = meal.name)
                  }
                  Button(onClick = {
                      profilePictureState.value =
                          if (profilePictureState.value == MealProfilePictureState.Normal)
                              MealProfilePictureState.Expanded
                          else
                              MealProfilePictureState.Normal
                  }) {
                      Text("Change State of Profile Picture")
                  }
              }
            }
            val dummyContent = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyContent) {
                    Text(text = it, modifier = Modifier.padding(24.dp))
                }
            }
        }

    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Cyan, 200.dp, 24.dp)
}