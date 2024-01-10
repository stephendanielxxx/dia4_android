package com.dia.androidlearndia.di

import android.content.Context
import androidx.room.Room
import com.dia.androidlearndia.datastore.DataStoreHelper
import com.dia.androidlearndia.login.LoginRepository
import com.dia.androidlearndia.retrofit.ApiService
import com.dia.androidlearndia.retrofit.RetrofitHelper
import com.dia.androidlearndia.room.AppDatabase
import com.dia.androidlearndia.sharedpref.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiaModule {
    @Provides
    @Singleton
    //@Bean
    fun provideApiService(): ApiService{
        return RetrofitHelper.apiService
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiService: ApiService): LoginRepository{
        return LoginRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSharedPrefHelper(
        @ApplicationContext context: Context)
    : SharedPrefHelper{
        return SharedPrefHelper(context)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "pokemon-db")
            .build()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreHelper{
        return DataStoreHelper(context)
    }
}