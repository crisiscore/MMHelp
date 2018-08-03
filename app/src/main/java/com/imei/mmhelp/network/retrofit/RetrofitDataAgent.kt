package com.imei.mmhelp.network.retrofit

import com.google.gson.Gson
import com.imei.mmhelp.events.EmptyDataEvent
import com.imei.mmhelp.events.ErrorApiEvent
import com.imei.mmhelp.events.GetJobsSuccessEvent
import com.imei.mmhelp.network.responses.GetJobsResponse
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgent
private constructor() {

    companion object {
        private var INSTANCE: RetrofitDataAgent? = null

        fun getInstance(): RetrofitDataAgent {
            if (INSTANCE == null) INSTANCE = RetrofitDataAgent()
            val i = INSTANCE
            return i!!
        }
    }

    private val mApi: GetJobsApi

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/final-projects/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        mApi = retrofit.create(GetJobsApi::class.java)
    }

    fun loadJobs(accessToken: String, page: Int) {
        val call = mApi.loadMMJobs(accessToken, page)
        call.enqueue(object : Callback<GetJobsResponse> {
            override fun onFailure(call: Call<GetJobsResponse>?, t: Throwable?) {
                EventBus.getDefault().post(ErrorApiEvent(t!!.message))
            }

            override fun onResponse(call: Call<GetJobsResponse>?, response: Response<GetJobsResponse>) {
                val jobsResponse: GetJobsResponse? = response.body()
                if (jobsResponse != null && jobsResponse.getCode() == 200 && jobsResponse.getJobs().isNotEmpty()) {
                    EventBus.getDefault().post(GetJobsSuccessEvent(jobsResponse.getJobs()))
                }else {
                    if (jobsResponse != null){
                        EventBus.getDefault().post(EmptyDataEvent(jobsResponse.getMessage()))
                    }else{
                        EventBus.getDefault().post(EmptyDataEvent())
                    }
                }
            }
        })
    }
}