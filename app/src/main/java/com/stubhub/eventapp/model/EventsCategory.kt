package com.stubhub.eventapp.model

data class EventsCategory(
    val id: Long,
    val name: String,
    val events: List<Event>,
    val children: List<EventsCategory>
)

data class Event(
    val id: Long,
    val name: String,
    val venueName: String,
    val city: String,
    val price: Int,
    val distanceFromVenue: Double,
    val date: String
)
