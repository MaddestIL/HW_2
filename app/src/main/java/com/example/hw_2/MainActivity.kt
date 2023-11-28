package com.example.hw_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            GiphGrid( )

        }
       /* val exeptionHandler = CoroutineExeptionHandler{_,e ->
            MainScope().launch {  }
        }*/
    }
}




@Composable
@Preview
fun GiphGrid() {
    val giphVM: GiphVM = viewModel()
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            giphVM.getAllGiphs()
        }
    }
    val giphs by giphVM.giphState.collectAsState()

    GiphCard(
        giph = giphs
    )

}

@Composable
fun GiphCard(
    giph: List<POJO.Data>,
    modifier: Modifier = Modifier,
){
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(giph) { giph ->
                AsyncImage(
                    model = giph,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

