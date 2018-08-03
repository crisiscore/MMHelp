package com.imei.mmhelp.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.imei.mmhelp.R
import com.imei.mmhelp.adapters.JobsAdapter
import com.imei.mmhelp.data.models.JobsModel
import com.imei.mmhelp.delegates.AccountDelegate
import com.imei.mmhelp.delegates.JobDelegate
import com.imei.mmhelp.events.EmptyDataEvent
import com.imei.mmhelp.events.ErrorApiEvent
import com.imei.mmhelp.events.GetJobsSuccessEvent
import com.imei.mmhelp.view.pods.BeforeLoginViewPod
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.mmtextview.MMFontUtils

class MainActivity : BaseActivity(), JobDelegate , AccountDelegate{

    private var adapter: JobsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

        MMFontUtils.initMMTextView(this)

        adapter = JobsAdapter(this)
        rvJobsList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvJobsList.adapter = adapter

        loadJobs()

        swipeRefreshLayout.isRefreshing = true

        swipeRefreshLayout.setOnRefreshListener {
            adapter!!.clearJobs()
            reloadJobs()
        }

        rvJobsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var isListEndReached = false

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = recyclerView!!.layoutManager.childCount
                val totalItemCount = recyclerView.layoutManager.itemCount
                val pastvisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (visibleItemCount + pastvisibleItems < totalItemCount)
                    isListEndReached = false
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        (recyclerView!!.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == recyclerView.adapter.itemCount - 1
                        && !isListEndReached) {
                    JobsModel.getInstance().loadNews()
                }
            }
        })

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    Snackbar.make(navigationView, "Tapped Home", Snackbar.LENGTH_SHORT).show()
                }

                R.id.nav_fav -> {
                    Snackbar.make(navigationView, "Tap Favorite", Snackbar.LENGTH_SHORT).show()
                }

                R.id.nav_jobs -> {
                    Snackbar.make(navigationView, "Tap Jobs", Snackbar.LENGTH_SHORT).show()
                }
            }
            for (menuItemIndex in 0 until navigationView.menu.size()) {
                navigationView.menu.getItem(menuItemIndex).isChecked = false
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            it.isChecked = true
            return@setNavigationItemSelectedListener true
        }

        val vpBeforeLogin = navigationView.getHeaderView(0) as BeforeLoginViewPod
        vpBeforeLogin.setDelegate(this)

    }

    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.END)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadJobs() {
        JobsModel.getInstance().loadNews()
    }

    private fun reloadJobs() {
        JobsModel.getInstance().reloadNews()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessJobs(event: GetJobsSuccessEvent) {
        swipeRefreshLayout.isRefreshing = false
        adapter!!.setJobs(event.getJobs())
        vpEmpty.visibility = View.GONE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onError(event: ErrorApiEvent) {
        swipeRefreshLayout.isRefreshing = false
        vpEmpty.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEmpty(event: EmptyDataEvent) {
        swipeRefreshLayout.isRefreshing = false
        vpEmpty.visibility = View.VISIBLE
    }

    override fun onClick(jobId: Int) {
        val intent = Intent(applicationContext, JobDetailsActivity::class.java)
        intent.putExtra("job_id", jobId)
        startActivity(intent)
    }

    override fun onLogin() {
        val intent = Intent(applicationContext , AccountActivity::class.java)
        intent.putExtra(AccountActivity.ACTION_TYPE , AccountActivity.ACTION_TYPE_LOGIN)
        startActivity(intent)
    }

    override fun onRegister() {
        val intent = Intent(applicationContext , AccountActivity::class.java)
        intent.putExtra(AccountActivity.ACTION_TYPE , AccountActivity.ACTION_TYPE_REGISTER)
        startActivity(intent)
    }

}
