package com.imei.mmhelp.data.vos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class RelevantVO {

    @SerializedName("relevancyPercentage")
    @Expose
    private val relevancyPercentage: Double? = null
    @SerializedName("seekerId")
    @Expose
    private val seekerId: Int? = null
    @SerializedName("seekerName")
    @Expose
    private val seekerName: String? = null
    @SerializedName("seekerProfilePicUrl")
    @Expose
    private val seekerProfilePicUrl: String? = null
    @SerializedName("seekerSkill")
    @Expose
    private val seekerSkill: List<SeekerSkillVO>? = null
    @SerializedName("whyRelevant")
    @Expose
    private val whyRelevant: String? = null

}