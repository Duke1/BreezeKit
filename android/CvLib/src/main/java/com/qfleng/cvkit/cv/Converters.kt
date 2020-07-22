package com.qfleng.cvkit.cv

import java.util.ArrayList

object Converters {
    fun Mat_to_vector_Mat(m: Mat, mats: MutableList<Mat>?) {
        if (mats == null)
            throw java.lang.IllegalArgumentException("mats == null")
        val count = m.rows()
        if (CvType.CV_32SC2 != m.type() || m.cols() != 1)
            throw java.lang.IllegalArgumentException(
                    "CvType.CV_32SC2 != m.type() ||  m.cols()!=1\n$m"
            )

        mats.clear()
        val buff = IntArray(count * 2)
        m.get(0, 0, buff)
        for (i in 0 until count) {
            val addr = buff[i * 2].toLong() shl 32 or (buff[i * 2 + 1].toLong() and 0xffffffffL)
            mats.add(Mat(addr))
        }
    }

    // vector_vector_Point2f
    fun Mat_to_vector_vector_Point2f(m: Mat?, pts: MutableList<MatOfPoint2f>?) {
        if (pts == null)
            throw IllegalArgumentException("Output List can't be null")

        if (m == null)
            throw IllegalArgumentException("Input Mat can't be null")

        val mats = ArrayList<Mat>(m.rows())
        Mat_to_vector_Mat(m, mats)
        for (mi in mats) {
            val pt = MatOfPoint2f(mi)
            pts.add(pt)
            mi.release()
        }
        mats.clear()
    }


    fun Mat_to_vector_Point(m: Mat, pts: MutableList<Point>?) {
        if (pts == null)
            throw java.lang.IllegalArgumentException("Output List can't be null")
        val count = m.rows()
        val type = m.type()
        if (m.cols() != 1)
            throw java.lang.IllegalArgumentException("Input Mat should have one column\n$m")

        pts.clear()
        if (type == CvType.CV_32SC2) {
            val buff = IntArray(2 * count)
            m.get(0, 0, buff)
            for (i in 0 until count) {
                pts.add(
                    Point(
                        buff[i * 2].toDouble(),
                        buff[i * 2 + 1].toDouble()
                    )
                )
            }
        } else if (type == CvType.CV_32FC2) {
            val buff = FloatArray(2 * count)
            m.get(0, 0, buff)
            for (i in 0 until count) {
                pts.add(
                    Point(
                        buff[i * 2].toDouble(),
                        buff[i * 2 + 1].toDouble()
                    )
                )
            }
        } else if (type == CvType.CV_64FC2) {
            val buff = DoubleArray(2 * count)
            m.get(0, 0, buff)
            for (i in 0 until count) {
                pts.add(Point(buff[i * 2], buff[i * 2 + 1]))
            }
        } else {
            throw java.lang.IllegalArgumentException(
                    "Input Mat should be of CV_32SC2, CV_32FC2 or CV_64FC2 type\n$m"
            )
        }
    }

    fun Mat_to_vector_Point2f(m: Mat, pts: MutableList<Point>) {
        Mat_to_vector_Point(m, pts)
    }
}
