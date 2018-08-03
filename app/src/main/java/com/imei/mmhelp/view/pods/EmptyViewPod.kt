package com.imei.mmhelp.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class EmptyViewPod : RelativeLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

}