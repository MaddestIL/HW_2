package com.example.hw_2.presentation.nvgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen: Route(route = "onBoardingScreen")
    object HomeScreen: Route(route = "homeScreen")
    object SearchScreen: Route(route = "searchScreen")
    object BookmarkScreen: Route(route = "bookmarkScreen")
    object DetailScreen: Route(route = "DetailScreen")
    object AppStartNavigation: Route(route= "appStartNavigation")
    object NewsNavigation: Route(route= "newsNavigation")
    object NewsNavigationScreen: Route(route="newsNavigationScreen")

}