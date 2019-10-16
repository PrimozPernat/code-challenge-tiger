package com.example.codechallengetiger.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallengetiger.model.Job

@Dao
interface JobDao{

    @Query("Select * FROM job")
    fun getAllJobs(): LiveData<List<Job>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobs(jobList: List<Job>)
}