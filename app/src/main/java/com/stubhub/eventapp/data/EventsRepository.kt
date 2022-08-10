package com.stubhub.eventapp.data

import com.stubhub.eventapp.model.Status
import com.stubhub.eventapp.model.Status.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val localSource: EventsLocalSource
) {
    suspend fun getEventsList(): Status = withContext(Dispatchers.IO) {
        localSource.getEvents()
    }

    suspend fun searchEvents(city: String, price: Int): Status = withContext(Dispatchers.IO) {
        when(val status = getEventsList()) {
            is Success -> Success(
                status.events.filter { it.city.contains(city) && it.price <= price }
            )
            else -> status
        }
    }
}
