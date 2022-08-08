package com.stubhub.eventapp.data

import com.stubhub.eventapp.model.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val localSource: EventsLocalSource
) {
    suspend fun getEventsList(city: String, price: Int): Status = withContext(Dispatchers.IO) {
        localSource.getEvents(city, price)
    }
}
