package com.imei.mmhelp.data.vos

import com.google.gson.annotations.SerializedName

class Applicant {

    @SerializedName("appliedDate")
    private val appliedDate: String? = null

    @SerializedName("canLowerOfferAmount")
    private val canLowerOfferAmount: Boolean = false

    @SerializedName("seekerId")
    private val seekerId: Int = 0

    @SerializedName("seekerName")
    private val seekerName: String? = null

    @SerializedName("seekerProfilePicUrl")
    private val seekerProfilePicUrl: String? = null

    @SerializedName("seekerSkill")
    private val seekerSkill: List<SeekerSkillVO>? = null

    @SerializedName("whyShouldHire")
    private val whyShouldHire: List<WhyShouldHireVO>? = null

}