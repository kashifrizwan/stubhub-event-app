package com.stubhub.eventapp.model

sealed class Status {
    object Loading : Status()
    object Error : Status()
    data class Success(val events: List<Event>) : Status()
}
