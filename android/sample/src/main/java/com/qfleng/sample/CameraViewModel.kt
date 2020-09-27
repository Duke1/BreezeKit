package com.qfleng.sample

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import androidx.lifecycle.AndroidViewModel
import com.qfleng.cvkit.CvHelper
import com.qfleng.cvkit.cv.Mat
import com.qfleng.sample.util.RxHelper
import com.qfleng.sample.util.RxObserver
import io.reactivex.functions.Consumer
import java.io.File


class Share {
    var image: Mat? = null

    private var reference: Int = 0

    @Synchronized
    fun addReference() {
        reference++
    }

    @Synchronized
    fun release() {
        reference++

        if (0 == reference) {
            image?.release()
            image = null

        }
    }

}

class CameraViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        @JvmStatic
        val SHARE: Share = Share()
    }

    fun cacheImage(src: Mat) {
        if (null != SHARE.image) {
            SHARE.image?.release()
            SHARE.image = null
        }
        SHARE.image = src

        SHARE.addReference()
    }


    fun takePicture(activity: AppCompatActivity, previewView: PreviewView) {
        createImage(previewView,
            File("${DirConst.BK_IMAGE_PATH}/bk_camera_${System.currentTimeMillis()}.jpg"),
            {
                toCvProcessor(activity, it)
            },
            {
                activity.showToast(it)
            }
        )
    }

    private fun createImage(
        previewView: PreviewView,
        file: File,
        onComplete: (File) -> Unit,
        onError: (String) -> Unit
    ) {
        val bitmap = previewView.bitmap
        RxHelper.doIt<File>(
            {
                val mat = Mat()
                CvHelper.bitmapToMat(bitmap, mat, false)

                CvHelper.saveImage(mat, file.absolutePath, 100)
                mat.release()
                file
            },
            RxObserver(
                next = {
                    if (null != bitmap && !bitmap.isRecycled)
                        bitmap.recycle()

                    onComplete(it)
                },
                onError = Consumer {
                    onError("Photo capture failed: ${it.message}")
                }
            )
        )

    }


    fun toCvProcessor(activity: AppCompatActivity, file: File) {
        if (!file.exists()) {
            activity.showToast("无效资源")
            return
        }
        cacheImage(CvHelper.createMat(file.absolutePath))

        val intent = Intent(activity, CvActivity::class.java)
        activity.startActivity(intent)
    }

}