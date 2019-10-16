package com.example.codechallengetiger.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codechallengetiger.R
import com.example.codechallengetiger.model.Job
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_job_view.*
import kotlinx.android.synthetic.main.activity_details.duration as durationDetails
import kotlinx.android.synthetic.main.activity_details.location as locationDetails

class DetailsActivity : AppCompatActivity() {
    companion object {
        val JOB_KEY = "job_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val job = intent.extras?.getParcelable<Job>(JOB_KEY)

        setUpLayout(job)
    }

    private fun setUpLayout(job: Job?) {
        job?.let {
            name.text = it.customerName
            date_time.text = it.getDateAndTime()
            locationDetails.text = it.getLocation()
            durationDetails.text = getString(
                R.string.display_hours_duration,
                it.orderDuration
            )
            previous_job.text = it.recurrency.toString()
            price.text = getString(
                R.string.euro_price,
                it.price
            )
        }
    }
}
