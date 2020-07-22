package com.qfleng.cvkit.cv

//javadoc:Point_
class Point @JvmOverloads constructor(var x: Double = 0.0, var y: Double = 0.0) {

    constructor(vals: DoubleArray) : this() {
        set(vals)
    }

    fun set(vals: DoubleArray?) {
        if (vals != null) {
            x = if (vals.size > 0) vals[0] else 0.0
            y = if (vals.size > 1) vals[1] else 0.0
        } else {
            x = 0.0
            y = 0.0
        }
    }

    fun clone(): Point {
        return Point(x, y)
    }

    fun dot(p: Point): Double {
        return x * p.x + y * p.y
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(x)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(y)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Point) return false
        val it = obj as Point?
        return x == it!!.x && y == it.y
    }

    fun inside(r: Rect): Boolean {
        return r.contains(this)
    }

    override fun toString(): String {
        return "{$x, $y}"
    }
}
