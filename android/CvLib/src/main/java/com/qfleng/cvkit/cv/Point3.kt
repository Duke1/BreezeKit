package com.qfleng.cvkit.cv

//javadoc:Point3_
class Point3 {

    var x: Double = 0.0
    var y: Double = 0.0
    var z: Double = 0.0

    @JvmOverloads
    constructor(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(p: Point) {
        x = p.x
        y = p.y
        z = 0.0
    }

    constructor(vals: DoubleArray) : this() {
        set(vals)
    }

    fun set(vals: DoubleArray?) {
        if (vals != null) {
            x = if (vals.size > 0) vals[0] else 0.0
            y = if (vals.size > 1) vals[1] else 0.0
            z = if (vals.size > 2) vals[2] else 0.0
        } else {
            x = 0.0
            y = 0.0
            z = 0.0
        }
    }

    fun clone(): Point3 {
        return Point3(x, y, z)
    }

    fun dot(p: Point3): Double {
        return x * p.x + y * p.y + z * p.z
    }

    fun cross(p: Point3): Point3 {
        return Point3(
            y * p.z - z * p.y,
            z * p.x - x * p.z,
            x * p.y - y * p.x
        )
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(x)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(y)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(z)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Point3) return false
        val it = obj as Point3?
        return x == it!!.x && y == it.y && z == it.z
    }

    override fun toString(): String {
        return "{$x, $y, $z}"
    }
}
