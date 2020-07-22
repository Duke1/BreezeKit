package com.qfleng.cvkit.cv

//javadoc:Range
class Range {

    var start: Int = 0
    var end: Int = 0

    @JvmOverloads
    constructor(s: Int = 0, e: Int = 0) {
        this.start = s
        this.end = e
    }

    constructor(vals: DoubleArray) {
        set(vals)
    }

    fun set(vals: DoubleArray?) {
        if (vals != null) {
            start = if (vals.size > 0) vals[0].toInt() else 0
            end = if (vals.size > 1) vals[1].toInt() else 0
        } else {
            start = 0
            end = 0
        }

    }

    fun size(): Int {
        return if (empty()) 0 else end - start
    }

    fun empty(): Boolean {
        return end <= start
    }

    fun intersection(r1: Range): Range {
        val r = Range(
            Math.max(r1.start, this.start),
            Math.min(r1.end, this.end)
        )
        r.end = Math.max(r.end, r.start)
        return r
    }

    fun shift(delta: Int): Range {
        return Range(start + delta, end + delta)
    }

    fun clone(): Range {
        return Range(start, end)
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(start.toDouble())
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(end.toDouble())
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Range) return false
        val it = obj as Range?
        return start == it!!.start && end == it.end
    }

    override fun toString(): String {
        return "[$start, $end)"
    }

    companion object {

        fun all(): Range {
            return Range(
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
            )
        }
    }
}
