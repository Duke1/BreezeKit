package com.qfleng.cvkit.cv

//javadoc:Size_
class Size {

    var width: Double = 0.0
    var height: Double = 0.0

    @JvmOverloads
    constructor(width: Double = 0.0, height: Double = 0.0) {
        this.width = width
        this.height = height
    }

    constructor(p: Point) {
        width = p.x
        height = p.y
    }

    constructor(vals: DoubleArray) {
        set(vals)
    }

    fun set(vals: DoubleArray?) {
        if (vals != null) {
            width = if (vals.size > 0) vals[0] else 0.0
            height = if (vals.size > 1) vals[1] else 0.0
        } else {
            width = 0.0
            height = 0.0
        }
    }

    fun area(): Double {
        return width * height
    }

    fun empty(): Boolean {
        return width <= 0 || height <= 0
    }

    fun clone(): Size {
        return Size(width, height)
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(height)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(width)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Size) return false
        val it = obj as Size?
        return width == it!!.width && height == it.height
    }

    override fun toString(): String {
        return width.toInt().toString() + "x" + height.toInt()
    }

}
