package com.qfleng.cvkit.cv

import java.util.Arrays

class MatOfPoint2f : Mat {

    constructor() : super() {}

    protected constructor(addr: Long) : super(addr) {
        if (!empty() && checkVector(
                _channels,
                _depth
            ) < 0)
            throw IllegalArgumentException("Incompatible Mat")
        //FIXME: do we need release() here?
    }

    constructor(m: Mat) : super(m,
        Range.all()
    ) {
        if (!empty() && checkVector(
                _channels,
                _depth
            ) < 0)
            throw IllegalArgumentException("Incompatible Mat")
        //FIXME: do we need release() here?
    }

    constructor(vararg a: Point) : super() {
        fromArray(*a)
    }

    fun alloc(elemNumber: Int) {
        if (elemNumber > 0)
            super.create(elemNumber, 1,
                CvType.makeType(
                    _depth,
                    _channels
                )
            )
    }

    fun fromArray(vararg a: Point) {
        if (a == null || a.size == 0)
            return
        val num = a.size
        alloc(num)
        val buff = FloatArray(num * _channels)
        for (i in 0 until num) {
            val p = a[i]
            buff[_channels * i + 0] = p.x.toFloat()
            buff[_channels * i + 1] = p.y.toFloat()
        }
        put(0, 0, buff) //TODO: check ret val!
    }

    fun toArray(): Array<Point>? {
        val num = total().toInt()
        val ap = Array(num) { Point(0.0, 0.0) }
        if (num == 0)
            return ap
        val buff = FloatArray(num * _channels)
        get(0, 0, buff) //TODO: check ret val!
        for (i in 0 until num)
            ap[i] = Point(
                buff[i * _channels].toDouble(),
                buff[i * _channels + 1].toDouble()
            )
        return ap
    }

    fun fromList(lp: List<Point>) {
        val ap = lp.toTypedArray()
        fromArray(*ap)
    }

    fun toList(): List<Point>? {
        val ap = toArray()
        return if (null == ap) null else Arrays.asList(*ap)
    }

    companion object {
        // 32FC2
        private val _depth = CvType.CV_32F
        private val _channels = 2

        fun fromNativeAddr(addr: Long): MatOfPoint2f {
            return MatOfPoint2f(addr)
        }
    }
}
