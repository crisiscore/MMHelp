package com.imei.mmhelp.data.vos

import com.google.gson.annotations.SerializedName

class InterestedVO {

    @SerializedName("interestedTimeStamp")
    private val interestedTimeStamp: String? = null

    @SerializedName("seekerId")
    private val seekerId: Int? = null

    @SerializedName("seekerName")
    private val seekerName: String? = null

    @SerializedName("seekerProfilePicUrl")
    private val seekerProfilePicUrl: String? = null

    @SerializedName("seekerSkill")
    private val seekerSkill: List<SeekerSkillVO>? = null

}