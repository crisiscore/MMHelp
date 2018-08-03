package com.imei.mmhelp.view.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.imei.mmhelp.data.vos.JobsVO
import com.imei.mmhelp.delegates.JobDelegate
import kotlinx.android.synthetic.main.view_holder_job.view.*

class JobViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private var mDelegate: JobDelegate? = null
    private var mJob: JobsVO? = null

    init {
        itemView!!.setOnClickListener {
            mDelegate!!.onClick(mJob!!.jobPostId!!)
        }
    }

    fun bind(jobVO: JobsVO, deleage: JobDelegate) {
        mDelegate = deleage
        mJob = jobVO
        itemView.tvJobTitle.text = jobVO.shortDesc
        itemView.tvJobFare.text = jobVO.offerAmount!!.amount.toString()
        itemView.tvsDescription.text = jobVO.fullDesc
        itemView.tvJobLocation.text = jobVO.location
        itemView.tvJobApplied.text = jobVO.postedDate
    }


}