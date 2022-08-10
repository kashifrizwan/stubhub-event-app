package com.stubhub.eventapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stubhub.eventapp.model.Event
import com.stubhub.eventapp.model.EventsCategory
import com.stubhub.eventapp.model.Status
import com.stubhub.eventapp.model.Status.Error
import com.stubhub.eventapp.model.Status.Success
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

const val eventsFileName = "events.json"

class EventsLocalSource @Inject constructor(
    @ApplicationContext val context: Context
) {
    fun getEvents() : Status {
        return try {
            val eventsCategory: EventsCategory = Gson().fromJson(
                getEventsFromJson(),
                object: TypeToken<EventsCategory>() {}.type
            )
            Success(getFlatEvents(eventsCategory))
        } catch (exception: Exception) {
            Error
        }
    }

    private fun getEventsFromJson(): String? {
        return try {
            context.assets.open(eventsFileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            null
        }
    }

    private fun getFlatEvents(
        eventsCategory: EventsCategory,
        flatEvents: MutableList<Event> = mutableListOf()
    ): List<Event> {
        flatEvents.addAll(eventsCategory.events)
        eventsCategory.children.forEach { children ->
            getFlatEvents(children, flatEvents)
        }
        return flatEvents
    }
}
