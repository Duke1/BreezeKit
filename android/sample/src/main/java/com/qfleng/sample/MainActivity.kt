package com.qfleng.sample

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import com.qfleng.cryptokit.CryptoHelper
import com.qfleng.cvkit.CvHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

fun AppCompatActivity.showDialog(
    inView: View,
    title: String,
    okClickListener: (DialogInterface, View) -> Unit?
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setIcon(android.R.drawable.ic_dialog_info)
        .setView(inView)
        .setPositiveButton(
            "OK"
        ) { dialog, which ->
            okClickListener(dialog, inView)
        }
        .setNegativeButton("CANCEL", null)
        .show()
}
fun <T : View> AppCompatActivity.bindView(@IdRes res: Int): Lazy<T> {
    return lazy { findViewById<T>(res) }
}


class MainActivity : AppCompatActivity() {
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sBuilder = StringBuilder()
        sBuilder.append("cv_:${CvHelper.getVersion()}")
        sBuilder.append("\ncrypto_:${CryptoHelper.sslVersion()}")
        txtView.text = sBuilder.toString()

        btnCv.setOnClickListener {

            val intent = Intent(this, CameraActivity::class.java)
            this.startActivity(intent)
        }
    }
}