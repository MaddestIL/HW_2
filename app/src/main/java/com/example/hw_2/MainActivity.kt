package com.example.hw_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hw_2.presentation.nvgraph.NavGraph
import com.example.hw_2.presentation.oboarding.OnBoardingScreen
import com.example.hw_2.presentation.oboarding.OnBoardingViewModel
import com.example.hw_2.ui.theme.HW_2Theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply{
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }

        setContent {
            HW_2Theme {

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemControll = rememberSystemUiController()

                SideEffect{
                    systemControll.setNavigationBarColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )

                }
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {

                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination )
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        event = viewModel::onEvent
                    )
                }
            }


        }

    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PhotoScreen(modifier: Modifier = Modifier.fillMaxSize(1f)) {
    var selected by remember {
        mutableStateOf(-1)
    }
    val scope = rememberCoroutineScope()
    val viewModel: PhotoViewModel = viewModel()
    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.getAllPhotos()
        }
    }

    val photos by viewModel.photoUiState.collectAsState()
    var filteredPhotos by remember {
        mutableStateOf(photos)
    }
    var filterText by remember {
        mutableStateOf("")
    }
    if (selected != -1) {
        photos.find { it.id == selected }
            ?.let {
                PhotoCard(
                    photo = it,
                    backHandle = { selected = -1 },
                    isFull = true
                )
            }
    } else {
        Column {
            //Добавим заглушку, если не нашли нужную карточку
            LazyColumn {
                items(filteredPhotos.ifEmpty {
                    if (filterText.isEmpty()) photos else listOf(
                        Photo(
                            -1,
                            "Without beer",
                            "No url"
                        )
                    )
                }) {
                    PhotoCard(photo = it, Modifier.clickable { selected = it.id })

                }
                item {
                    Button(
                        onClick = { viewModel.loadNewPhotos() }
                    ) {
                        Text("Add", fontSize = 25.sp)
                    }
                }

            }

        }
    }
}

@Composable
fun PhotoCard(
    photo: Photo,
    modifier: Modifier = Modifier,
    backHandle: () -> Unit = {},
    isFull: Boolean = false
) {
    var isFavorite by remember { mutableStateOf(false) }
    BackHandler {
        backHandle()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 200.dp,
                ambientColor = Color(0x14000000)
            )
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
    ) {
        AsyncImage(
            model = photo.image_url,
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 12.dp)
                .width(328.dp)
                .height(301.dp)
                .clip(RoundedCornerShape(30.dp))
        )
        Text(
            text = photo.name,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                letterSpacing = 0.2.sp,
            ),
            modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
        )
        if (isFull) {
            Text(
                text = "id = ${photo.id}",
                style = TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF818C99),
                    letterSpacing = 0.3.sp,
                ),
            )

        }
        IconToggleButton(
            checked = false,
            onCheckedChange = {
                isFavorite =!isFavorite
            }
            ){
                Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "" )

             }
    }
}
