package com.imei.mmhelp.events

class ErrorApiEvent(val message: String?) {

    fun getMsg(): String {
        return message ?: "Null throwable"
    }
}