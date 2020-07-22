package com.qfleng.cvkit.cv

//javadoc:Rect_
class Rect {

    var x: Int = 0
    var y: Int = 0
    var width: Int = 0
    var height: Int = 0

    @JvmOverloads
    constructor(x: Int = 0, y: Int = 0, width: Int = 0, height: Int = 0) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    constructor(p1: Point, p2: Point) {
        x = (if (p1.x < p2.x) p1.x else p2.x).toInt()
        y = (if (p1.y < p2.y) p1.y else p2.y).toInt()
        width = (if (p1.x > p2.x) p1.x else p2.x).toInt() - x
        height = (if (p1.y > p2.y) p1.y else p2.y).toInt() - y
    }

    constructor(p: Point, s: Size) : this(p.x.toInt(), p.y.toInt(), s.width.toInt(), s.height.toInt()) {}

    constructor(vals: DoubleArray) {
        set(vals)
    }

    fun set(vals: DoubleArray?) {
        if (vals != null) {
            x = if (vals.size > 0) vals[0].toInt() else 0
            y = if (vals.size > 1) vals[1].toInt() else 0
            width = if (vals.size > 2) vals[2].toInt() else 0
            height = if (vals.size > 3) vals[3].toInt() else 0
        } else {
            x = 0
            y = 0
            width = 0
            height = 0
        }
    }

    fun clone(): Rect {
        return Rect(x, y, width, height)
    }

    fun tl(): Point {
        return Point(x.toDouble(), y.toDouble())
    }

    fun br(): Point {
        return Point(
            (x + width).toDouble(),
            (y + height).toDouble()
        )
    }

    fun size(): Size {
        return Size(width.toDouble(), height.toDouble())
    }

    fun area(): Double {
        return (width * height).toDouble()
    }

    fun empty(): Boolean {
        return width <= 0 || height <= 0
    }

    operator fun contains(p: Point): Boolean {
        return x <= p.x && p.x < x + width && y <= p.y && p.y < y + height
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(height.toDouble())
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(width.toDouble())
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(x.toDouble())
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(y.toDouble())
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Rect) return false
        val it = obj as Rect?
        return x == it!!.x && y == it.y && width == it.width && height == it.height
    }

    override fun toString(): String {
        return "{" + x + ", " + y + ", " + width + "x" + height + "}"
    }
}
