package com.minjin.musinsa.route

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MusinsaList : Route
}