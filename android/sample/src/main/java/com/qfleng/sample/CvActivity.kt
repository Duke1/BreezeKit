package com.qfleng.sample

import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.method.ScrollingMovementMethod
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

import com.qfleng.cvkit.CvHelper
import com.qfleng.cvkit.cv.Imgproc
import com.qfleng.cvkit.cv.Mat
import com.qfleng.sample.util.RxHelper
import com.qfleng.sample.util.RxObserver
import com.qfleng.sample.util.fullScreen
import com.xw.repo.BubbleSeekBar
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

    val viewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProvider(this).get(CameraViewModel::class.java)
    }

    var lightnessAddtion = 0
    val contrastBarView by bindView<BubbleSeekBar>(R.id.contrastBarView)
    val lightnessBarView by bindView<BubbleSeekBar>(R.id.lightnessBarView)


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

        addFunBtn("模糊") {
            setDisplayMat1(cameraShare.image!!.clone())

            val threshEt = AppCompatEditText(this)
            threshEt.hint = "模糊次数："
            threshEt.inputType = EditorInfo.TYPE_CLASS_NUMBER
            threshEt.setText("1")
            threshEt.requestFocus()
            showDialog(threshEt, "阈值") { dialog, view ->
                val thresh = threshEt.text.toString().toInt()
                dialog.dismiss()

                RxHelper.doIt({
                    val mat = curDisplayMat1!!.clone()

                    for (i in 0 until thresh)
                        CvHelper.blur(mat, 25)

                    mat
                }, RxObserver(next = {
                    setDisplayMat2(it)
                }))

            }
        }


        lightnessBarView.setProgress(0f)
        lightnessBarView.onProgressChangedListener =
            object : BubbleSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(
                    bubbleSeekBar: BubbleSeekBar,
                    progress: Int,
                    progressFloat: Float,
                    fromUser: Boolean
                ) {
                    lightnessAddtion = progress

                    val modifyMat = suspend {
                        val src = cameraShare.image!!.clone()
                        CvHelper.modifyLightness(src, lightnessAddtion)
                        src
                    }

                    viewModel.viewModelScope.launch(Dispatchers.Main) {

                        val mat = withContext(Dispatchers.IO) {
                            modifyMat()
                        }

                        setDisplayMat2(mat)
                    }
                }

                override fun getProgressOnActionUp(
                    bubbleSeekBar: BubbleSeekBar,
                    progress: Int,
                    progressFloat: Float
                ) {
                }

                override fun getProgressOnFinally(
                    bubbleSeekBar: BubbleSeekBar,
                    progress: Int,
                    progressFloat: Float,
                    fromUser: Boolean
                ) {
                }
            }
        contrastBarView.setProgress(0f)
        contrastBarView.onProgressChangedListener = object :
            BubbleSeekBar.OnProgressChangedListener {
            override fun onProgressChanged(
                bubbleSeekBar: BubbleSeekBar,
                progress: Int,
                progressFloat: Float,
                fromUser: Boolean
            ) {
            }

            override fun getProgressOnActionUp(
                bubbleSeekBar: BubbleSeekBar,
                progress: Int,
                progressFloat: Float
            ) {
            }

            override fun getProgressOnFinally(
                bubbleSeekBar: BubbleSeekBar,
                progress: Int,
                progressFloat: Float,
                fromUser: Boolean
            ) {
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