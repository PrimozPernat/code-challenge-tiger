package com.example.codechallengetiger.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codechallengetiger.di.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity

inline fun <reified T : ViewModel> DaggerAppCompatActivity.getViewModel(
    factory: ViewModelProviderFactory
) = ViewModelProvider(this, factory)
    .get(T::class.java)