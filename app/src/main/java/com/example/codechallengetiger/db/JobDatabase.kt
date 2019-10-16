package com.example.codechallengetiger.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codechallengetiger.model.Job

@Database(
    entities = [Job::class],
    version = 1,
    exportSchema = false
)
abstract class JobDatabase : RoomDatabase() {
    companion object {
        const val JOB_DATABASE_NAME = "job_database"
    }

    abstract fun jobDao(): JobDao
}