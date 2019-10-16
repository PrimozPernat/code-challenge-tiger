package com.example.codechallengetiger.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codechallengetiger.di.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import java.text.SimpleDateFormat

inline fun <reified T : ViewModel> DaggerAppCompatActivity.getViewModel(
    factory: ViewModelProviderFactory
) = ViewModelProvider(this, factory)
    .get(T::class.java)

fun String.formatDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    return formatter.format(parser.parse(this))
}