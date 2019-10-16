package com.example.codechallengetiger.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.codechallengetiger.model.Job
import com.example.codechallengetiger.repository.IHomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(repo: IHomeRepository) : ViewModel() {
    private val resultData = liveData {
        emit(repo.getJobList())
    }


    val jobLis: LiveData<List<Job>> = Transformations.switchMap(resultData) {
        it.data
    }

    val error: LiveData<String> = Transformations.switchMap(resultData) {
        it.networkErrors
    }

}

