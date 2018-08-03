package com.imei.mmhelp.network.responses

import com.google.gson.annotations.SerializedName
import com.imei.mmhelp.data.vos.JobsVO

class GetJobsResponse {

    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("apiVersion")
    private val apiVersion: String? = null

    @SerializedName("jobs")
    private val jobs: List<JobsVO>? = null

    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    fun getApiVersion(): String? {
        return apiVersion
    }

    fun getJobs():List<JobsVO> {
        return jobs ?: ArrayList()
    }

}