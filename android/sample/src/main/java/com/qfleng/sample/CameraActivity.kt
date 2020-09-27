package com.qfleng.sample

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.Display
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider

import com.qfleng.sample.util.FileSelector
import com.qfleng.sample.util.OrientationHelper
import com.qfleng.sample.util.fullScreen
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File


/**
 *CameraX 相机
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/camerax-getting-started"> Google示例 </a>
 */
class CameraActivity : AppCompatActivity() {


    private val viewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProvider(this).get(CameraViewModel::class.java)
    }

    private val fileSelector = FileSelector()
    private val dMetrics by lazy { resources.displayMetrics }

    private val display: Display
        get() = windowManager.defaultDisplay


    @Volatile
    private var torchStatus: Boolean = false

    private var camera: Camera? = null

    private fun hasPermissions(): Boolean {
        return checkUserPermission(Manifest.permission.CAMERA) &&
                checkUserPermission(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                checkUserPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fullScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if (hasPermissions()) {
            cameraPreviewView.post {
                startCamera()
            }
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                ), 0
            )
        }

        lifecycle.addObserver(OrientationHelper(this) {
            infoBoardView.text = "角度:${it}"
        })


        btnGallery.click {
            fileSelector.openFileSelector(this, 121) { reqCode, filePath ->
                viewModel.toCvProcessor(this, File(filePath))
            }
        }

        btnLight.click {
            synchronized(torchStatus) {
                if (null != camera && camera!!.cameraInfo.hasFlashUnit()) {
                    torchStatus = !torchStatus
                    camera!!.cameraControl.enableTorch(torchStatus)
                }
            }
        }

        btnTakePicture.click {
            viewModel.takePicture(this, cameraPreviewView)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (121 == requestCode) return

        if (checkUserPermission(Manifest.permission.CAMERA)) {
            cameraPreviewView.post {
                startCamera()
            }
        } else {
            showToast("未授权访问相机")
            finish()
        }
    }


    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .setTargetResolution(Size(dMetrics.widthPixels, dMetrics.heightPixels))
                .setTargetRotation(display.rotation)
                .build()
                .also {
                    it.setSurfaceProvider(cameraPreviewView.surfaceProvider)
                }


            try {
                cameraProvider.unbindAll()

                // Bind use cases to camera
                camera = cameraProvider.bindToLifecycle(
                    this,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview
                )

            } catch (exc: Exception) {
                Log.e("CameraX", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        fileSelector.onActivityResult(this, requestCode, resultCode, data)

    }

}