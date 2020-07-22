package com.qfleng.cvkit.cv

class CvException(msg: String) : RuntimeException(msg) {

    override fun toString(): String {
        return "CvException [" + super.toString() + "]"
    }

    companion object {

        private val serialVersionUID = 1L
    }
}
