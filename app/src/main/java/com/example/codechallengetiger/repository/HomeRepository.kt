package com.example.codechallengetiger.repository

import androidx.lifecycle.MutableLiveData
import com.example.codechallengetiger.db.JobDatabase
import com.example.codechallengetiger.model.Job
import com.example.codechallengetiger.network.JobApi
import com.example.codechallengetiger.util.CustomResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: JobApi, private val db: JobDatabase) :
    IHomeRepository {

    private val _error = MutableLiveData<String>()

    override
    suspend fun getJobList(): CustomResult<List<Job>> {
        try {
            val result = api.getJobList()
            insertJobs(result)

        } catch (e: Exception) {
            _error.value = e.localizedMessage
        }

        return CustomResult(db.jobDao().getAllJobs(), _error)
    }

    override suspend fun insertJobs(list: List<Job>) {
        db.jobDao().insertJobs(list)
    }
}

interface IHomeRepository {
    suspend fun getJobList(): CustomResult<List<Job>>
    suspend fun insertJobs(list: List<Job>)
}