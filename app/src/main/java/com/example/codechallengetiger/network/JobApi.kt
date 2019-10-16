package com.example.codechallengetiger.network

import androidx.lifecycle.LiveData
import com.example.codechallengetiger.model.Job
import retrofit2.http.GET

interface JobApi {

    @GET("jobs")
    suspend fun getJobList(): List<Job>
}