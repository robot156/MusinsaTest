package com.minjin.musinsa.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.minjin.musinsa.route.Route

fun NavGraphBuilder.listNavigation() {
    composable<Route.MusinsaList> {
        ListScreenRoute()
    }
}

fun NavController.navigateToList(navOptions: NavOptions? = null) {
    this.navigate(Route.MusinsaList, navOptions)
}