package com.example.codechallengetiger.di

import com.example.codechallengetiger.ui.home.HomeActivity
import com.example.codechallengetiger.di.viewmodel.MainViewModelModules
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [MainViewModelModules::class]
    )
    internal abstract fun contributeMainActivity(): HomeActivity
}