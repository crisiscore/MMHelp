package com.imei.mmhelp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.imei.mmhelp.R
import com.imei.mmhelp.data.vos.JobsVO
import com.imei.mmhelp.delegates.JobDelegate
import com.imei.mmhelp.view.holders.JobViewHolder

class JobsAdapter(val deleage: JobDelegate) : RecyclerView.Adapter<JobViewHolder>() {

    private var mJobs: List<JobsVO>? = null

    init {
        mJobs = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_job, parent, false)
        return JobViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mJobs!!.size
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(mJobs!![position] , deleage)
    }

    fun setJobs(jobsVO: List<JobsVO>) {
        mJobs = jobsVO
        notifyDataSetChanged()
    }

    fun clearJobs() {
        mJobs = ArrayList()
        notifyDataSetChanged()
    }
}