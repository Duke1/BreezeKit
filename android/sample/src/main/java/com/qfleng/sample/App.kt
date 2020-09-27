package com.qfleng.sample

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

fun Context.showToast(content: String?) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun View.click(func: (View) -> Unit) {
    this.setOnClickListener {
        func(it)
    }
}

fun AppCompatActivity.checkUserPermission(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PackageManager.PERMISSION_GRANTED == this.checkSelfPermission(permission)
    } else
        true
}


class App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        DirConst.initDir(this)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
