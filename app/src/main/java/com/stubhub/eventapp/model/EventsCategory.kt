package com.stubhub.eventapp.model

data class EventsCategory(
    val id: Long = 0,
    val name: String = "",
    val events: List<Event> = emptyList(),
    val children: List<EventsCategory> = emptyList()
)

data class Event(
    val id: Long = 0,
    val name: String = "",
    val venueName: String = "",
    val city: String = "",
    val price: Int = 0,
    val distanceFromVenue: Double = 0.0,
    val date: String = ""
)
