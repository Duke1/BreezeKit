package com.qfleng.cvkit.cv

//javadoc:Scalar_
class Scalar {

    var `val`: DoubleArray

    val isReal: Boolean
        get() = `val`[1] == 0.0 && `val`[2] == 0.0 && `val`[3] == 0.0

    constructor(v0: Double, v1: Double, v2: Double, v3: Double) {
        `val` = doubleArrayOf(v0, v1, v2, v3)
    }

    constructor(v0: Double, v1: Double, v2: Double) {
        `val` = doubleArrayOf(v0, v1, v2, 0.0)
    }

    constructor(v0: Double, v1: Double) {
        `val` = doubleArrayOf(v0, v1, 0.0, 0.0)
    }

    constructor(v0: Double) {
        `val` = doubleArrayOf(v0, 0.0, 0.0, 0.0)
    }

    constructor(vals: DoubleArray?) {
        if (vals != null && vals.size == 4)
            `val` = vals.clone()
        else {
            `val` = DoubleArray(4)
            set(vals)
        }
    }

    fun set(vals: DoubleArray?) {
        if (vals != null) {
            `val`[0] = if (vals.size > 0) vals[0] else 0.0
            `val`[1] = if (vals.size > 1) vals[1] else 0.0
            `val`[2] = if (vals.size > 2) vals[2] else 0.0
            `val`[3] = if (vals.size > 3) vals[3] else 0.0
        } else {
            `val`[3] = 0.0
            `val`[2] = `val`[3]
            `val`[1] = `val`[2]
            `val`[0] = `val`[1]
        }
    }

    fun clone(): Scalar {
        return Scalar(`val`)
    }

    @JvmOverloads
    fun mul(it: Scalar, scale: Double = 1.0): Scalar {
        return Scalar(
            `val`[0] * it.`val`[0] * scale, `val`[1] * it.`val`[1] * scale,
            `val`[2] * it.`val`[2] * scale, `val`[3] * it.`val`[3] * scale
        )
    }

    fun conj(): Scalar {
        return Scalar(
            `val`[0],
            -`val`[1],
            -`val`[2],
            -`val`[3]
        )
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + java.util.Arrays.hashCode(`val`)
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Scalar) return false
        val it = obj as Scalar?
        return if (!java.util.Arrays.equals(`val`, it!!.`val`)) false else true
    }

    override fun toString(): String {
        return "[" + `val`[0] + ", " + `val`[1] + ", " + `val`[2] + ", " + `val`[3] + "]"
    }

    companion object {

        fun all(v: Double): Scalar {
            return Scalar(v, v, v, v)
        }
    }

}
