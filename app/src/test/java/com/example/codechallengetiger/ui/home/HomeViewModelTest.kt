package com.example.codechallengetiger.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.codechallengetiger.model.Job
import com.example.codechallengetiger.repository.IHomeRepository
import com.example.codechallengetiger.util.CustomResult
import com.exmaple.codechallengetiger.util.LiveDataTestUtil
import com.exmaple.codechallengetiger.util.getFakeJobList
import com.exmaple.codechallengetiger.util.getFakeStaleJobList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private val ERROR_MESSAGE = "error_message"

class HomeViewModelTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val fakeHomeRepository = FakeHomeRepository()
    lateinit var SUT: HomeViewModel

    @Before
    fun `set up test`() {
        SUT = HomeViewModel(fakeHomeRepository)
    }


    @Test
    fun `check perfect flow`() = runBlocking {
        //arrange
        val apiReturn = getFakeJobList()

        //make the call
        val repoResponse = fakeHomeRepository.getJobList()

        val resultData = LiveDataTestUtil.getValue(repoResponse.data!!)


        val resultError = if (repoResponse.networkErrors == null) {
            null
        } else {
            LiveDataTestUtil.getValue(repoResponse.networkErrors!!)
        }

        //assert
        assertThat(resultData, `is`(apiReturn))
        assertThat(fakeHomeRepository.insertCall, `is`(1))
        assertNull(resultError)
    }

    @Test
    fun `check network error first fetch`() = runBlocking {
        //arrange - negative
        fakeHomeRepository.isFailure = true
        fakeHomeRepository.isFirstCall = true

        //make the call
        val repoResponse = fakeHomeRepository.getJobList()

        val resultError = LiveDataTestUtil.getValue(repoResponse.networkErrors!!)


        val resultData = if (repoResponse.data == null) {
            null
        } else {
            LiveDataTestUtil.getValue(repoResponse.data!!)
        }

        //assert
        assertThat(resultError, `is`(ERROR_MESSAGE))
        assertThat(fakeHomeRepository.insertCall, `is`(0))
        assertNull(resultData)
    }


    @Test
    fun `error flow - check network error and data after frist fech`() = runBlocking {
        //arrange - negative
        fakeHomeRepository.isFailure = true
        fakeHomeRepository.isFirstCall = false
        val resultStaleData = getFakeStaleJobList()

        //make the call
        val repoResponse = fakeHomeRepository.getJobList()

        val resultError = LiveDataTestUtil.getValue(repoResponse.networkErrors!!)


        val resultData = LiveDataTestUtil.getValue(repoResponse.data!!)


        //assert
        assertThat(resultError, `is`(ERROR_MESSAGE))
        assertThat(resultData, `is`(resultStaleData))
        assertThat(fakeHomeRepository.insertCall, `is`(0))
    }
}

class FakeHomeRepository : IHomeRepository {
    var isFirstCall: Boolean = false
    var insertCall = 0

    var isFailure = false

    override suspend fun getJobList(): CustomResult<List<Job>> {
        val data = MutableLiveData<List<Job>>()
        val error = MutableLiveData<String>()

        if (isFailure) {
            error.value = ERROR_MESSAGE

            if (!isFirstCall) {
                //Simulate return from the DB
                data.value = getFakeStaleJobList()
            }

        } else {
            data.value = getFakeJobList()
            insertJobs(data.value!!)
        }

        return CustomResult(data, error)
    }

    override suspend fun insertJobs(list: List<Job>) {
        insertCall++
    }
}