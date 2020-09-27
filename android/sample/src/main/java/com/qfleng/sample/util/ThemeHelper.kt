package com.qfleng.sample.util

import android.app.Activity
import android.content.Context
import android.view.Window
import android.view.WindowManager

/**
 * App主题相关
 */
fun Activity.fullScreen() {
    this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}


fun Context.getStatusBarHeight(): Int {
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) this.resources.getDimensionPixelSize(resourceId) else 0
}
