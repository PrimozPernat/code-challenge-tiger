package com.example.codechallengetiger.ui.home

import androidx.lifecycle.*
import com.example.codechallengetiger.model.Job
import com.example.codechallengetiger.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModel @Inject constructor(repo: IHomeRepository) : ViewModel() {
    private val resultData = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(repo.getJobList())
    }


    val jobLis: LiveData<List<Job>> = Transformations.switchMap(resultData) {
        it.data
    }

    val error: LiveData<String> = Transformations.switchMap(resultData) {
        it.networkErrors
    }

}

