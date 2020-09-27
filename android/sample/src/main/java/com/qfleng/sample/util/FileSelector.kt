package com.qfleng.sample.util

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.qfleng.sample.*
import java.io.File
import java.io.InputStream


class FileSelector {

    private var reqCode: Int = 0
    private var optListener: ((reqCode: Int, filePath: String?) -> Unit)? = null

    fun openFileSelector(
        activity: AppCompatActivity,
        reqCode: Int,
        optListener: (reqCode: Int, filePath: String?) -> Unit
    ) {
        if (!activity.checkUserPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            activity.requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), reqCode)
            return
        }

        this.reqCode = reqCode
        this.optListener = optListener

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            activity.startActivityForResult(Intent.createChooser(intent, "选择器"), reqCode)
        } catch (ex: android.content.ActivityNotFoundException) {
            ex.printStackTrace()
        }

    }


    fun onActivityResult(
        activity: AppCompatActivity,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        if (requestCode == reqCode && null != data && null != data.data) {

            RxHelper.doIt(
                {
                    val uri = data.data ?: return@doIt ""

                    return@doIt saveSelectImage(
                        activity,
                        uri
                    )
                }, RxObserver(
                    next = { filePath ->
                        if (!TextUtils.isEmpty(filePath)) {
                            optListener?.let { it(requestCode, filePath) }
                        } else
                            activity.showToast("Url is empty.")
                    })
            )
        }
    }

    companion object {
        fun saveSelectImage(
            activity: AppCompatActivity,
            uri: Uri?,
            isSync: Boolean = true,
            complete: ((String) -> Unit)? = null
        ): String {
            if (null == uri) return ""

            val func = fun(): String {
                val filePath = FilePathUtil.getPath(activity, uri) ?: return ""

                val dstName = "bk_${System.currentTimeMillis()}_${File(filePath).extension}"
                val dstFile = File("${DirConst.BK_IMAGE_PATH}/${dstName}")

                if (dstFile.exists()) dstFile.delete()

                val inStream: InputStream? = activity.contentResolver.openInputStream(uri)

                if (null != inStream) FileHelper().save(dstFile, inStream)

                return dstFile.absolutePath
            }

            return if (isSync) {
                func()
            } else {
                RxHelper.doIt(
                    { func() },
                    observer = RxObserver(next = {
                        complete?.let { it1 -> it1(it) }
                    })
                )
                ""
            }
        }

    }
}
