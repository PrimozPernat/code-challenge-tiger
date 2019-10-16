package com.example.codechallengetiger.di

import android.app.Application
import androidx.room.Room
import com.example.codechallengetiger.db.JobDatabase
import com.example.codechallengetiger.network.JobApi
import com.example.codechallengetiger.repository.HomeRepository
import com.example.codechallengetiger.repository.IHomeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule.Declarations::class])
object AppModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideRoomDatabase(application: Application): JobDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            JobDatabase::class.java, JobDatabase.JOB_DATABASE_NAME
        ).build()

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofitInstance(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://private-14c693-rentapanda.apiary-mock.com/")
        .build()


    @Provides
    @Singleton
    @JvmStatic
    fun provideMoshiCutomAdatper() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    @Provides
    @Singleton
    @JvmStatic
    fun provideJobApi(retrofit: Retrofit) = retrofit.create(JobApi::class.java)

    @Module
    interface Declarations {
        @Binds
        fun provideHomeRepository(impl: HomeRepository): IHomeRepository
    }
}