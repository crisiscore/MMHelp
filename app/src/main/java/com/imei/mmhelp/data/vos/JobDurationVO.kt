package com.imei.mmhelp.data.vos

import com.google.gson.annotations.SerializedName

class JobDurationVO {

    @SerializedName("jobEndDate")
    var jobEndDate: String? = null

    @SerializedName("jobStartDate")
    var jobStartDate: String? = null

    @SerializedName("totalWorkingDays")
    var totalWorkingDays: Int? = null

    @SerializedName("workingDaysPerWeek")
    var workingDaysPerWeek: Int? = null

    @SerializedName("workingHoursPerDay")
    var workingHoursPerDay: Double? = null

}