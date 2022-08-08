package com.stubhub.eventapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stubhub.eventapp.R
import com.stubhub.eventapp.databinding.ItemEventsListBinding
import com.stubhub.eventapp.model.Event

class EventsViewHolder(
    private val viewBinding: ItemEventsListBinding
): RecyclerView.ViewHolder(viewBinding.root) {

    val context = viewBinding.root.context

    fun onBind(event: Event) {
        viewBinding.artist.text = event.name
        viewBinding.city.text = context.getString(R.string.city_label, event.city)
        viewBinding.price.text = context.getString(R.string.price_label, event.price)
    }
}
