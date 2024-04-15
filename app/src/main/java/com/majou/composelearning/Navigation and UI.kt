package com.majou.composelearning
//
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.CutCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.LocalContentColor
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarColors
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import coil.compose.rememberAsyncImagePainter
//import com.majou.composelearning.entity.UserProfile
//import com.majou.composelearning.entity.userProfileList
//import com.majou.composelearning.ui.theme.ComposeLearningTheme
//import com.majou.composelearning.ui.theme.lightgreen
//
//val namesArrayList = arrayListOf("John", "Muhammed", "Ali", "Jouhar")
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeLearningTheme {
//                // A surface container using the 'background' color from the theme
//                UserApplication()
//            }
//        }
//    }
//}
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainUI(
//    userProfiles: List<UserProfile> = userProfileList,
//    navController: NavController? = null
//) {
//    Scaffold(topBar = { AppBar("Users Screen", icon = Icons.Default.Home) {} }) { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
//                LazyColumn {
//                    items(userProfiles) { userProfile ->
//                        ProfileCard(userProfile = userProfile) {
//                            navController?.navigate(
//                                "user_details/${userProfile.id}"
//                            )
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AppBar(title: String, icon: ImageVector, clickAction: () -> Unit) {
//    TopAppBar(
//        navigationIcon = {
//            androidx.compose.material3.Icon(
//                imageVector = icon,
//                modifier = Modifier
//                    .padding(horizontal = 12.dp)
//                    .clickable { clickAction() },
//                contentDescription = "Content"
//            )
//        },
//        title = { Text(text = title) },
//        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan)
//    )
//}
//
//@Composable
//fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .padding(top = 16.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
//            .fillMaxWidth()
//            .wrapContentHeight(align = Alignment.Top)
//            .clickable { clickAction() },
//        elevation = CardDefaults.cardElevation(8.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        shape = CutCornerShape(topEnd = 24.dp)
//
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start
//        ) {
//            ProfilePicture(userProfile.pictureUrl, userProfile.status)
//            ProfileContent(userProfile.name, userProfile.status)
//        }
//    }
//}
//
//@Composable
//fun ProfilePicture(drawableId: Any, status: Boolean, imageSize: Dp = 72.dp) {
//    Card(
//        shape = CircleShape,
//        border = BorderStroke(
//            width = 2.dp,
//            color = if (status) MaterialTheme.colorScheme.lightgreen else Color.Red
//        ),
//        modifier = Modifier.padding(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
////        Image(
////            painter = painterResource(id = drawableId),
////            contentDescription = "",
////            modifier = Modifier.size(72.dp)
////        )
//        val painter = rememberAsyncImagePainter(drawableId)
//        Image(painter = painter, contentDescription = null, modifier = Modifier.size(imageSize))
//    }
//}
//
//@Composable
//fun ProfileContent(
//    username: String,
//    status: Boolean,
//    alignment: Alignment.Horizontal = Alignment.Start
//) {
//    Column(
//        modifier = Modifier
//            .padding(8.dp),
//        horizontalAlignment = alignment
//    ) {
//        Text(username, style = MaterialTheme.typography.headlineSmall)
//        CompositionLocalProvider(
//            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
//                alpha = 0.38f
//            )
//        ) {
//            Text(
//                when (status) {
//                    true -> "Active Now"
//                    else -> "Offline"
//                }, style = MaterialTheme.typography.bodySmall
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeLearningTheme { MainUI() }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun userProfilesPrevie() {
//    ComposeLearningTheme { UserProfileDetailsUI() }
//
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UserProfileDetailsUI(
//    userProfile: UserProfile = userProfileList[0],
//    navController: NavController?=null
//) {
//    Scaffold(topBar = {
//        AppBar("User Profile Details", Icons.Default.ArrowBack) {
//            navController?.navigateUp()
//        }
//    }) { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            Surface(modifier = Modifier.fillMaxSize()) {
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    ProfilePicture(userProfile.pictureUrl, userProfile.status, imageSize = 240.dp)
//                    ProfileContent(
//                        userProfile.name,
//                        userProfile.status,
//                        Alignment.CenterHorizontally
//                    )
//                }
//            }
//        }
//
//    }
//}
//
//@Composable
//fun UserApplication(userProfiles: List<UserProfile> = userProfileList) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "user_list", builder = {
//        composable("user_list") {
//            MainUI(userProfiles = userProfiles, navController)
//        }
//        composable("user_details/{userId}", arguments = listOf(navArgument("userId") {
//            type = NavType.IntType
//        })) { navBackStackEntry ->
//            UserProfileDetailsUI(userProfileList.find {
//                it.id == navBackStackEntry.arguments!!.getInt(
//                    "userId"
//                )
//            }!!,navController)
//        }
//    })
//}