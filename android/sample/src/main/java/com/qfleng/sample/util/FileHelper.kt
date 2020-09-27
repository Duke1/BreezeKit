package com.qfleng.sample.util

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception


/**
 * 文件辅助类
 */
class FileHelper {

    fun save(file: File, inputStream: InputStream) {
        try {
            val fos = FileOutputStream(file)

            inputStream.use { inStream ->
                fos.use { outStream ->
                    val buffer = ByteArray(1024)
                    var len = 0
                    while (true) {
                        len = inStream.read(buffer)
                        if (len == -1) {
                            break
                        }
                        outStream.write(buffer, 0, len)
                        outStream.flush()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}