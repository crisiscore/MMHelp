package com.imei.mmhelp.network.retrofit

import com.imei.mmhelp.network.responses.GetJobsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GetJobsApi {

    @FormUrlEncoded
    @POST("one-time-jobs/getJobs.php")
    fun loadMMJobs(@Field("access_token") accessToken : String ,
               @Field("page")page: Int) : Call<GetJobsResponse>

}