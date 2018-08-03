package com.imei.mmhelp.data.models

import com.imei.mmhelp.data.vos.JobsVO
import com.imei.mmhelp.events.GetJobsSuccessEvent
import com.imei.mmhelp.network.retrofit.RetrofitDataAgent
import com.imei.mmhelp.utils.AppConstants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class JobsModel private constructor() {

    companion object {
        private var INSTANCE: JobsModel? = null

        fun getInstance(): JobsModel {
            if (INSTANCE == null)
                INSTANCE = JobsModel()
            val i = INSTANCE
            return i!!
        }
    }

    private var mHashMap: HashMap<String, JobsVO> = HashMap()
    private var mPage: Int = 1

    init {

        EventBus.getDefault().register(this)
    }

    fun loadNews() {
        RetrofitDataAgent.getInstance().loadJobs(AppConstants.ACCESS_TOKEN, mPage)
    }

    fun reloadNews() {
        mPage = 1
        mHashMap = HashMap()
        RetrofitDataAgent.getInstance().loadJobs(AppConstants.ACCESS_TOKEN, mPage)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public fun getJobs(jobsSuccessEvent: GetJobsSuccessEvent) {
        for (job: JobsVO in jobsSuccessEvent.getJobs()) {
            mHashMap[job.jobPostId.toString()] = job
        }
        mPage += 1
    }

    fun getJobById(id: Int): JobsVO? {
        return mHashMap[id.toString()]
    }

}