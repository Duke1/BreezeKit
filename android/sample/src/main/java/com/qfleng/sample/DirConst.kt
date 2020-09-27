package com.qfleng.sample

import android.content.Context


import java.io.File

object DirConst {
    const val ROOT_DIR_NAME = "bk_data"


    var BK_ROOT_PATH = ""
    var BK_IMAGE_PATH = ""


    fun initDir(ctx: Context) {
        if (BK_ROOT_PATH.isEmpty()) {
            BK_ROOT_PATH = "${ctx.getExternalFilesDir(null)!!.absolutePath}/${ROOT_DIR_NAME}"
            BK_IMAGE_PATH = "${BK_ROOT_PATH}/image"
        }

        mkdir(BK_ROOT_PATH)
        mkdir(BK_IMAGE_PATH)
    }


    fun mkdir(str: String) {
        val filePath = File(str)
        if (filePath.exists() && filePath.isDirectory)
            return

        if (!filePath.isDirectory) {
            filePath.mkdir()
        }
    }
}
