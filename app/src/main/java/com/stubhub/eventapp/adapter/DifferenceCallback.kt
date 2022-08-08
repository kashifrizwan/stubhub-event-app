package com.stubhub.eventapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stubhub.eventapp.model.Event

class DifferenceCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}
