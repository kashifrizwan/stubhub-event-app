package com.stubhub.eventapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stubhub.eventapp.databinding.ItemEventsListBinding
import com.stubhub.eventapp.model.Event
import javax.inject.Inject

class EventsAdapter @Inject constructor() : ListAdapter<Event, EventsViewHolder>(DifferenceCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val viewBinding = ItemEventsListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EventsViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) =
        holder.onBind(getItem(position))
}
