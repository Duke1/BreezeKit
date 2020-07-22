package com.qfleng.cvkit.cv

object CvType {

    // type depth constants
    const val CV_8U = 0
    const val CV_8S = 1
    const val CV_16U = 2
    const val CV_16S = 3
    const val CV_32S = 4
    const val CV_32F = 5
    const val CV_64F = 6
    const val CV_USRTYPE1 = 7

    // predefined type constants
    val CV_8UC1 = CV_8UC(1)
    val CV_8UC2 = CV_8UC(2)
    val CV_8UC3 = CV_8UC(3)
    val CV_8UC4 = CV_8UC(4)
    val CV_8SC1 = CV_8SC(1)
    val CV_8SC2 = CV_8SC(2)
    val CV_8SC3 = CV_8SC(3)
    val CV_8SC4 = CV_8SC(4)
    val CV_16UC1 = CV_16UC(1)
    val CV_16UC2 = CV_16UC(2)
    val CV_16UC3 = CV_16UC(3)
    val CV_16UC4 = CV_16UC(4)
    val CV_16SC1 = CV_16SC(1)
    val CV_16SC2 = CV_16SC(2)
    val CV_16SC3 = CV_16SC(3)
    val CV_16SC4 = CV_16SC(4)
    val CV_32SC1 = CV_32SC(1)
    val CV_32SC2 = CV_32SC(2)
    val CV_32SC3 = CV_32SC(3)
    val CV_32SC4 = CV_32SC(4)
    val CV_32FC1 = CV_32FC(1)
    val CV_32FC2 = CV_32FC(2)
    val CV_32FC3 = CV_32FC(3)
    val CV_32FC4 = CV_32FC(4)
    val CV_64FC1 = CV_64FC(1)
    val CV_64FC2 = CV_64FC(2)
    val CV_64FC3 = CV_64FC(3)
    val CV_64FC4 = CV_64FC(4)

    private val CV_CN_MAX = 512
    private val CV_CN_SHIFT = 3
    private val CV_DEPTH_MAX = 1 shl CV_CN_SHIFT

    fun makeType(depth: Int, channels: Int): Int {
        if (channels <= 0 || channels >= CV_CN_MAX) {
            throw java.lang.UnsupportedOperationException(
                "Channels count should be 1.." + (CV_CN_MAX - 1)
            )
        }
        if (depth < 0 || depth >= CV_DEPTH_MAX) {
            throw java.lang.UnsupportedOperationException(
                "Data type depth should be 0.." + (CV_DEPTH_MAX - 1)
            )
        }
        return (depth and CV_DEPTH_MAX - 1) + (channels - 1 shl CV_CN_SHIFT)
    }

    fun CV_8UC(ch: Int): Int {
        return makeType(
            CV_8U,
            ch
        )
    }

    fun CV_8SC(ch: Int): Int {
        return makeType(
            CV_8S,
            ch
        )
    }

    fun CV_16UC(ch: Int): Int {
        return makeType(
            CV_16U,
            ch
        )
    }

    fun CV_16SC(ch: Int): Int {
        return makeType(
            CV_16S,
            ch
        )
    }

    fun CV_32SC(ch: Int): Int {
        return makeType(
            CV_32S,
            ch
        )
    }

    fun CV_32FC(ch: Int): Int {
        return makeType(
            CV_32F,
            ch
        )
    }

    fun CV_64FC(ch: Int): Int {
        return makeType(
            CV_64F,
            ch
        )
    }

    fun channels(type: Int): Int {
        return (type shr CV_CN_SHIFT) + 1
    }

    fun depth(type: Int): Int {
        return type and CV_DEPTH_MAX - 1
    }

    fun isInteger(type: Int): Boolean {
        return depth(type) < CV_32F
    }

    fun ELEM_SIZE(type: Int): Int {
        when (depth(type)) {
            CV_8U, CV_8S -> return channels(
                type
            )
            CV_16U, CV_16S -> return 2 * channels(
                type
            )
            CV_32S, CV_32F -> return 4 * channels(
                type
            )
            CV_64F -> return 8 * channels(
                type
            )
            else -> throw java.lang.UnsupportedOperationException(
                "Unsupported CvType value: $type"
            )
        }
    }

    fun typeToString(type: Int): String {
        val s: String
        when (depth(type)) {
            CV_8U -> s = "CV_8U"
            CV_8S -> s = "CV_8S"
            CV_16U -> s = "CV_16U"
            CV_16S -> s = "CV_16S"
            CV_32S -> s = "CV_32S"
            CV_32F -> s = "CV_32F"
            CV_64F -> s = "CV_64F"
            CV_USRTYPE1 -> s = "CV_USRTYPE1"
            else -> throw java.lang.UnsupportedOperationException(
                "Unsupported CvType value: $type"
            )
        }

        val ch = channels(type)
        return if (ch <= 4)
            s + "C" + ch
        else
            s + "C(" + ch + ")"
    }

}
