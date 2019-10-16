package com.example.codechallengetiger.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallengetiger.R
import com.example.codechallengetiger.model.Job
import com.example.codechallengetiger.util.formatDate
import kotlinx.android.synthetic.main.item_job_view.view.*


class JobListAdapter(val clickListener: (Job) -> Unit) :
    ListAdapter<Job, JobViewHolder>(JOB_COMPERATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_job_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it, clickListener)
        }
    }


    companion object {
        private val JOB_COMPERATOR = object : DiffUtil.ItemCallback<Job>() {
            override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean =
                oldItem.customerName == newItem.customerName &&
                        oldItem.date == newItem.date &&
                        oldItem.orderDuration == newItem.orderDuration

            override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean =
                oldItem == newItem

        }
    }
}

class JobViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindData(item: Job, clickListener: (Job) -> Unit) {
        itemView.customer.text = item.customerName
        itemView.location.text = item.getLocation()
        itemView.date.text = item.getDateAndTime()
        itemView.duration.text =
            itemView.context.getString(
                R.string.display_hours_duration,
                item.orderDuration
            )
        itemView.card_view.setOnClickListener {
            clickListener(item)
        }
    }
}