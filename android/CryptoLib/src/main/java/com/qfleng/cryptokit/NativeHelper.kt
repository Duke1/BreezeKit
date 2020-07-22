package com.qfleng.cryptokit

import android.content.res.AssetManager


object NativeHelper {
    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("au_crypto_kit")
    }


    external fun sslVersion(): String

    external fun strMd5(inputStr: String): String

    external fun fileMd5(fileName: String): String


    external fun strSha1(inputStr: String): String

    external fun fileSha1(fileName: String): String

    external fun unZip(fileName: String, outDir: String): Boolean

    external fun unZip(assetManager: AssetManager, fileName: String, outDir: String): Boolean
}
