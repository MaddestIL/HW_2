package com.example.hw_2.presentation.oboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hw_2.PhotoScreen
import com.example.hw_2.presentation.Dimens.MediumPadding2
import com.example.hw_2.presentation.Dimens.PageIndicatorWidth
import com.example.hw_2.presentation.common.HWButton
import com.example.hw_2.presentation.common.HWTextButton
import com.example.hw_2.presentation.common.PageIndicator
import com.example.hw_2.presentation.oboarding.components.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(){
    Column(modifier = Modifier.fillMaxSize()){
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }
        val buttonState = remember{
            derivedStateOf{
                when(pagerState.currentPage){
                    0 -> listOf("","Next")
                    1 -> listOf("Back","Next")
                    2 -> listOf("Back","Get started")
                    else -> listOf("","")
                }
            }
        }
        HorizontalPager(state = pagerState) {index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier
                    .width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    HWTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                HWButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 3) {

                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )

            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}