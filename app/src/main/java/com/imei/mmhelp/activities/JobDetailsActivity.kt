package com.imei.mmhelp.activities

import android.os.Bundle
import android.view.MenuItem
import com.imei.mmhelp.R
import com.imei.mmhelp.data.models.JobsModel
import com.imei.mmhelp.data.vos.JobsVO
import kotlinx.android.synthetic.main.activity_job_details.*

class JobDetailsActivity : BaseActivity() {

    companion object {
        const val JOB_ID = "job_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)
        setSupportActionBar(toolBar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val jobId = intent.getIntExtra(JOB_ID, -1)

        if (jobId != -1) {
            val job = JobsModel.getInstance().getJobById(jobId)
            bindJob(job)
        }
    }

    private fun bindJob(job: JobsVO?) {
        tvTitle.text = job!!.shortDesc
        tvDesc.text = job.fullDesc
        tvFare.text = job.offerAmount!!.amount.toString().plus(job.offerAmount!!.duration)
        tvLocation.text = job.location
        tvPhone.text = job.phoneNo
        tvEmail.text = job.email
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}