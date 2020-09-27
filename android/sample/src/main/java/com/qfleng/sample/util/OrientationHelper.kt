package com.qfleng.sample.util

import android.content.Context
import android.view.OrientationEventListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 屏幕旋转角度辅助
 */
class OrientationHelper(val ctx: Context, val orientationListener: (angle: Int) -> Unit) :
    OrientationEventListener(ctx), LifecycleObserver {

    private val _lock = Object()


    override fun onOrientationChanged(orientation: Int) {
        if (orientation == ORIENTATION_UNKNOWN) {
            return  //平放时，角度无效，直接忽略
        }

        orientationListener(
            when {
                orientation > 350 || orientation < 20 -> { //0度  90 正竖屏
                    0
                }
                orientation in 71..109 -> { //90度 右横屏
                    90
                }
                orientation in 161..199 -> { //180度 倒竖屏
                    180
                }
                orientation in 251..289 -> { //270度 左横屏
                    270
                }
                else ->
                    0
            }
        )
    }

    fun open() {
        synchronized(_lock) {
            if (canDetectOrientation()) {
                enable()
            } else {
                disable()
            }
        }
    }

    fun close() {
        synchronized(_lock) {
            disable()
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        close()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        open()
    }

}