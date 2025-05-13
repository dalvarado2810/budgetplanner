package com.daniel.budgetplanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.daniel.base.Product
import com.daniel.base.presentation.model.Destination
import com.daniel.budgetplanner.onboarding.OnboardingProduct
import org.koin.core.context.loadKoinModules
import java.util.concurrent.atomic.AtomicBoolean

private val initLocker = AtomicBoolean(false)

private val budgetProducts = listOf<Product>(
    OnboardingProduct
)

@Composable
fun ProductsNavHost(
    modifier: Modifier,
    startDestination: Destination,
    navController: NavHostController
) {
    val context = LocalContext.current.applicationContext

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        if (initLocker.compareAndSet(false, true)) {
            val productModules = budgetProducts.flatMap { it.getModules() }

            loadKoinModules(productModules)

            budgetProducts.forEach { product ->
                product.onInitApplication(context)
            }
        }
        budgetProducts.forEach { product ->
            product.onInitNavigation(navController, this@NavHost)
        }
    }
}