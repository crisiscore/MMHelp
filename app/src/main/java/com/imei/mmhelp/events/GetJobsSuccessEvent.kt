package com.imei.mmhelp.events

import com.imei.mmhelp.data.vos.JobsVO

class GetJobsSuccessEvent(private val jobsList: List<JobsVO>) {

    fun getJobs():List<JobsVO>{
        return jobsList
    }
}