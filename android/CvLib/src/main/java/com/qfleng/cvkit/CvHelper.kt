package com.qfleng.cvkit

import android.graphics.Bitmap
import com.qfleng.cvkit.cv.Imgcodecs
import com.qfleng.cvkit.cv.Mat


object CvHelper {
    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("au_cv_kit")
    }


    fun getVersion(): String {
        return nCvVersion()
    }

    @JvmOverloads
    fun bitmapToMat(bmp: Bitmap?, src: Mat?, unPremultiplyAlpha: Boolean = false) {
        requireNotNull(bmp) { "bmp == null" }
        requireNotNull(src) { "src mat == null" }
        nBitmapToMat2(
            bmp,
            src.nativeObjAddr,
            unPremultiplyAlpha
        )
    }

    @JvmOverloads
    fun matToBitmap(src: Mat?, bmp: Bitmap?, premultiplyAlpha: Boolean = false) {
        requireNotNull(src) { "src mat == null" }
        requireNotNull(bmp) { "bmp == null" }
        nMatToBitmap2(
            src.nativeObjAddr,
            bmp,
            premultiplyAlpha
        )
    }

    @JvmOverloads
    fun cvtColor(src: Mat?, dst: Mat?, code: Int, dstCn: Int) {
        requireNotNull(src) { "src mat == null" }
        requireNotNull(dst) { "dst mat == null" }
        nCvtColor(
            src.nativeObjAddr,
            dst.nativeObjAddr,
            code,
            dstCn
        )
    }

    @JvmOverloads
    fun flip(src: Mat?, flipCode: Int) {
        requireNotNull(src) { "src mat == null" }
        nFlip(src.nativeObjAddr, flipCode)
    }

    @JvmOverloads
    fun saveImage(
        src: Mat?,
        path: String,
        quality: Int = 100,
        imwriteFlags: Int = Imgcodecs.IMWRITE_JPEG_QUALITY
    ) {
        requireNotNull(src) { "src mat == null" }
        nSaveImage(
            src.nativeObjAddr,
            path,
            quality,
            imwriteFlags
        )
    }

    @JvmOverloads
    fun createMat(filePath: String): Mat {
        return Mat(
            nCreateMat(
                filePath
            )
        )
    }

    @JvmOverloads
    fun threshold(src: Mat?, thresh: Int): Mat {
        requireNotNull(src) { "src mat == null" }

        return Mat(
            nThreshold(
                src.nativeObjAddr,
                thresh
            )
        )
    }

    @JvmOverloads
    fun adaptiveThreshold(src: Mat?, blockSize: Int, C: Double): Mat {
        requireNotNull(src) { "src mat == null" }

        return Mat(
            nAdaptiveThreshold(
                src.nativeObjAddr,
                blockSize,
                C
            )
        )
    }

    @JvmOverloads
    fun rotation(src: Mat?, angle: Int): Mat {
        requireNotNull(src) { "src mat == null" }

        return Mat(
            nRotation(
                src.nativeObjAddr,
                angle
            )
        )
    }

    @JvmOverloads
    fun repair(
        src: Mat?,
        thresh: Double = 235.0,
        mask: Mat? = null,
        maskPositions: IntArray = intArrayOf()//[startX,startY,endX,endY]
    ) {
        requireNotNull(src) { "src mat == null" }

        nRepair(
            src.nativeObjAddr,
            thresh,
            mask?.nativeObjAddr ?: 0,
            maskPositions
        )

    }

    private external fun nCvVersion(): String

    private external fun nBitmapToMat2(b: Bitmap, m_addr: Long, unPremultiplyAlpha: Boolean)

    private external fun nMatToBitmap2(m_addr: Long, b: Bitmap, premultiplyAlpha: Boolean)

    private external fun nCvtColor(src_addr: Long, dst_addr: Long, code: Int, dstCn: Int)

    private external fun nFlip(src_addr: Long, flipCode: Int)

    private external fun nSaveImage(src_addr: Long, path: String, quality: Int, imwriteFlags: Int)

    private external fun nCreateMat(filePath: String): Long

    private external fun nThreshold(src_addr: Long, thresh: Int): Long

    private external fun nAdaptiveThreshold(src_addr: Long, blockSize: Int, C: Double): Long

    private external fun nRotation(src_addr: Long, angle: Int): Long

    private external fun nRepair(
        src_addr: Long,
        thresh: Double,
        mask_addr: Long,
        maskPositions: IntArray
    )


}
