package com.imei.mmhelp.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.imei.mmhelp.delegates.AccountDelegate
import kotlinx.android.synthetic.main.view_pod_before_login.view.*

class BeforeLoginViewPod : RelativeLayout {

    private lateinit var mDelegate: AccountDelegate

    fun setDelegate(delegate: AccountDelegate) {
        mDelegate = delegate
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        btnLogin.setOnClickListener {
            mDelegate.onLogin()
        }

        btnRegister.setOnClickListener {
            mDelegate.onRegister()
        }
    }

}