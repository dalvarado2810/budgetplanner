package com.daniel.budgeplanner.di

import android.content.Context
import androidx.room.Room
import com.daniel.budgeplanner.data.database.DbMovements
import com.daniel.budgeplanner.data.room.MovementsDao
import com.daniel.budgeplanner.data.sharedpreferences.AppPreference
import com.daniel.budgeplanner.data.sharedpreferences.SharedPreferencesImpl
import com.daniel.budgeplanner.repositories.MovementRepository
import com.daniel.budgeplanner.repositories.impl.MovementRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

const val DB_MOVEMENTS = "DB_MOVEMENTS"

@Module
@InstallIn(SingletonComponent::class)
class BudgetModule {

    @Provides
    fun provideMovementsDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DbMovements::class.java,
        DB_MOVEMENTS
    ).build()

    @Provides
    fun provideMovementsDao(db: DbMovements) = db.movementsDao

    @Provides
    fun provideMovementsRepository(
        movementsDao: MovementsDao
    ): MovementRepository = MovementRepositoryImpl(
        movementsDao = movementsDao
    )

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): AppPreference {
        return SharedPreferencesImpl(context)
    }
}
