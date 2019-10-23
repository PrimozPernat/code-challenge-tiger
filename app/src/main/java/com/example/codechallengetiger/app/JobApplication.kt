package com.example.codechallengetiger.app

import android.app.Application
import com.example.codechallengetiger.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class JobApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}