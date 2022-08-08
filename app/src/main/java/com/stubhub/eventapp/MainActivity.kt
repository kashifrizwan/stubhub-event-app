package com.stubhub.eventapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.stubhub.eventapp.adapter.EventsAdapter
import com.stubhub.eventapp.databinding.ActivityMainBinding
import com.stubhub.eventapp.model.Event
import com.stubhub.eventapp.model.Status.Success
import com.stubhub.eventapp.model.Status.Loading
import com.stubhub.eventapp.model.Status.Error
import com.stubhub.eventapp.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var eventsAdapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setOnClickListeners()

        viewModel.eventsLiveData.observe(this) { status ->
            when (status) {
                Loading -> updateStatus(getString(R.string.please_wait))
                Error -> updateStatus(getString(R.string.something_went_wrong))
                is Success -> if(status.events.isEmpty()) {
                    updateStatus(getString(R.string.found_no_data))
                } else {
                    updateEventsList(status.events)
                }
            }
        }
    }

    private fun updateEventsList(events: List<Event>) {
        eventsAdapter.submitList(events)
        binding.statusTextView.isVisible = false
        binding.eventsRecyclerView.isVisible = true
    }

    private fun updateStatus(status: String) {
        binding.statusTextView.text = status
        binding.statusTextView.isVisible = true
        binding.eventsRecyclerView.isVisible = false
    }

    private fun setupRecyclerView() {
        binding.eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = eventsAdapter
        }
    }

    private fun setOnClickListeners() {
        binding.searchBtn.setOnClickListener {
            hideKeyboard()
            viewModel.getEventsList(
                city = binding.citySearchEdittext.text.toString(),
                price = binding.priceSearchEdittext.text.toString().toIntOrNull() ?: Int.MAX_VALUE
            )
        }
    }

    private fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
