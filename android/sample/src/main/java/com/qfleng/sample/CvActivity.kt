package com.qfleng.sample

import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.inputmethod.EditorInfo
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import com.qfleng.cvkit.CvHelper
import com.qfleng.cvkit.cv.Imgproc
import com.qfleng.cvkit.cv.Mat
import com.qfleng.sample.util.RxHelper
import com.qfleng.sample.util.RxObserver
import com.qfleng.sample.util.fullScreen
import kotlinx.android.synthetic.main.activity_cv.*

/**
 * OpenCV 功能
 */
class CvActivity : AppCompatActivity() {

    private val cameraShare = CameraViewModel.SHARE
    private var bitmap1: Bitmap? = null
    private var bitmap2: Bitmap? = null
    private var curDisplayMat1: Mat? = null
    private var curDisplayMat2: Mat? = null
    private val _lock = Object()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen()
        setContentView(R.layout.activity_cv)

        logView.movementMethod = ScrollingMovementMethod.getInstance()

        setDisplayMat1(cameraShare.image!!.clone())


        addFunBtn("黑白") {
            setDisplayMat1(cameraShare.image!!.clone())
            val mat = CvHelper.adaptiveThreshold(curDisplayMat1!!, 31, 10.0)
            setDisplayMat2(mat)
        }

        addFunBtn("灰度图") {
            setDisplayMat1(cameraShare.image!!.clone())
            val mat = Mat()
            CvHelper.cvtColor(curDisplayMat1!!, mat, Imgproc.COLOR_RGB2GRAY, 0)
            setDisplayMat2(mat)
        }


        addFunBtn("修补") {
            setDisplayMat1(cameraShare.image!!.clone())

            val threshEt = AppCompatEditText(this)
            threshEt.hint = "掩膜thresh值："
            threshEt.inputType = EditorInfo.TYPE_CLASS_NUMBER
            threshEt.setText("235")
            threshEt.requestFocus()
            showDialog(threshEt, "阈值") { dialog, view ->
                val thresh = threshEt.text.toString().toDouble()

                dialog.dismiss()

                showLog("修复开始")
                val startTime = System.currentTimeMillis()

                RxHelper.doIt({
                    val mask: Mat? = null//Mat()
                    val src = cameraShare.image!!.clone();
                    val sx = 600
                    val sy = 700
                    CvHelper.repair(
                        src, thresh, mask,
                        maskPositions = intArrayOf(sx, sy, sx + 500, sy + 600)
                    )
                    mask ?: src
                }, RxObserver(next = {
                    setDisplayMat2(it)
                    showLog("修复结束。耗时${(System.currentTimeMillis() - startTime) / 1000} s")
                }))

            }

        }
    }

    @Synchronized
    private fun setDisplayMat1(ori: Mat) {
        synchronized(_lock) {
            if (null != curDisplayMat1 && curDisplayMat1!!.nativeObjAddr != ori.nativeObjAddr) {
                curDisplayMat1?.release()
            }
            curDisplayMat1 = ori
            if (null != bitmap1 && !bitmap1!!.isRecycled)
                bitmap1!!.recycle()
            bitmap1 = Bitmap.createBitmap(
                curDisplayMat1!!.width(),
                curDisplayMat1!!.height(),
                Bitmap.Config.ARGB_8888
            )
            CvHelper.matToBitmap(curDisplayMat1, bitmap1)
            imageView1.setImageBitmap(bitmap1)
        }
    }

    @Synchronized
    private fun setDisplayMat2(ori: Mat) {
        synchronized(_lock) {
            if (null != curDisplayMat2 && curDisplayMat2!!.nativeObjAddr != ori.nativeObjAddr) {
                curDisplayMat2?.release()
            }
            curDisplayMat2 = ori
            if (null != bitmap2 && !bitmap2!!.isRecycled)
                bitmap2!!.recycle()
            bitmap2 = Bitmap.createBitmap(
                curDisplayMat1!!.width(),
                curDisplayMat1!!.height(),
                Bitmap.Config.ARGB_8888
            )
            CvHelper.matToBitmap(curDisplayMat2, bitmap2)
            imageView2.setImageBitmap(bitmap2)
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        CameraViewModel.SHARE.release()
    }


    private fun addFunBtn(name: String, clickListener: () -> Unit) {
        val button = Button(this)
        button.text = name
        button.click {
            clickListener()
        }
        cvFunBoardView.addView(button)
    }


    private fun showLog(message: String) {
        logView.append("${message}\n")
    }
}