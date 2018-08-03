package com.imei.mmhelp.data.vos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequiredSkillVO {

    @SerializedName("skillId")
    @Expose
    private val skillId: Int? = null
    @SerializedName("skillName")
    @Expose
    private val skillName: String? = null

}