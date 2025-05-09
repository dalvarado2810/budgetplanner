package com.daniel.budgetplanner

import android.app.Application
import com.daniel.base.di.baseModule
import com.daniel.budgetplanner.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BudgetPlannerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BudgetPlannerApplication)

            modules(listOf(
                appModule,
                baseModule
            ))
        }
    }
}

