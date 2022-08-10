package com.stubhub.eventapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stubhub.eventapp.data.EventsRepository
import com.stubhub.eventapp.model.Status
import com.stubhub.eventapp.model.Status.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    val eventsLiveData: MutableLiveData<Status> = MutableLiveData()

    init {
        getEventsList()
    }

    private fun getEventsList() = viewModelScope.launch {
        eventsLiveData.postValue(Loading)
        eventsLiveData.postValue(eventsRepository.getEventsList())
    }

    fun searchEvents(city: String, price: Int) = viewModelScope.launch {
        eventsLiveData.postValue(Loading)
        eventsLiveData.postValue(eventsRepository.searchEvents(city, price))
    }
}
