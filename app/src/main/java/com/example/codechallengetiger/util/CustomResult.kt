package com.example.codechallengetiger.util

import androidx.lifecycle.LiveData

data class CustomResult<T>(
    val data: LiveData<T>?,
    val networkErrors: LiveData<String>?
)