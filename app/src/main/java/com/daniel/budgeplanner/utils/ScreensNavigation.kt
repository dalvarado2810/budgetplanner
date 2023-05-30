package com.daniel.budgeplanner.utils

sealed class ScreensNavigation(
    val routes: String
) {
    object GetStarted: ScreensNavigation("getStarted")
    object OnBoarding: ScreensNavigation("onBoarding")
}