package com.qfleng.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.qfleng.breezekit.R
import com.qfleng.cryptokit.CryptoHelper
import com.qfleng.cvkit.CvHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sBuilder = StringBuilder()
        sBuilder.append("cv_:${CvHelper.getVersion()}")
        sBuilder.append("\ncrypto_:${CryptoHelper.sslVersion()}")
        txtView.text = sBuilder.toString()
    }
}