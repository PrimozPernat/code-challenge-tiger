package com.example.codechallengetiger.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.exmaple.codechallengetiger.util.LiveDataTestUtil
import com.exmaple.codechallengetiger.util.getFakeJobList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JobDatabaseTest {
    val NUMBER_OF_ITEMS_INSERTED = 3

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var db: JobDatabase
    lateinit var dao: JobDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            JobDatabase::class.java
        ).build()

        dao = db.jobDao()
    }

    @Test
    fun insertToDB() = runBlocking {
        //arrange
        val items = getFakeJobList()

        //insert data to db
        dao.insertJobs(items)

        val result = LiveDataTestUtil.getValue(dao.getAllJobs())

        //assert
        assertThat(items, `is`(result))

    }

    @Test
    fun noItemInsertedToDB() = runBlocking {
        //arrange
        val items = getFakeJobList()

        val result = LiveDataTestUtil.getValue(dao.getAllJobs())

        //assert
        assertThat(items, not(result))

    }

    @After
    fun closeDB() {
        db.close()
    }
}