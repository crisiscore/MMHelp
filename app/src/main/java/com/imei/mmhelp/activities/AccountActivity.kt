package com.imei.mmhelp.activities

import android.os.Bundle
import com.imei.mmhelp.R
import com.imei.mmhelp.fragments.LoginFragment
import com.imei.mmhelp.fragments.RegisterFragment

class AccountActivity : BaseActivity() {

    companion object {
        const val ACTION_TYPE = "action_type"
        const val ACTION_TYPE_LOGIN = 111
        const val ACTION_TYPE_REGISTER = 222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionType = intent.extras.getInt(ACTION_TYPE)

        if (actionType == ACTION_TYPE_LOGIN) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment())
                    .commit()
        } else if (actionType == ACTION_TYPE_REGISTER) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegisterFragment())
                    .commit()
        }
    }

}