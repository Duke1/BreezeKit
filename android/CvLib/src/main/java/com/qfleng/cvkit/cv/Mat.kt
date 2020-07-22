package com.qfleng.cvkit.cv

import java.nio.ByteBuffer

// C++: class Mat
//javadoc: Mat
open class Mat {

    // javadoc:Mat::getNativeObjAddr()
    val nativeObjAddr: Long

    //
    // C++: bool Mat::isContinuous()
    //

    // javadoc: Mat::isContinuous()
    val isContinuous: Boolean
        get() = n_isContinuous(nativeObjAddr)

    //
    // C++: bool Mat::isSubmatrix()
    //

    // javadoc: Mat::isSubmatrix()
    val isSubmatrix: Boolean
        get() = n_isSubmatrix(nativeObjAddr)

    constructor(addr: Long) {
        if (addr == 0L)
            throw java.lang.UnsupportedOperationException("Native object address is NULL")
        nativeObjAddr = addr
    }

    //
    // C++: Mat::Mat()
    //

    // javadoc: Mat::Mat()
    constructor() {

        nativeObjAddr = n_Mat()

        return
    }

    //
    // C++: Mat::Mat(int rows, int cols, int type)
    //

    // javadoc: Mat::Mat(rows, cols, type)
    constructor(rows: Int, cols: Int, type: Int) {

        nativeObjAddr = n_Mat(rows, cols, type)

        return
    }

    //
    // C++: Mat::Mat(int rows, int cols, int type, void* data)
    //

    // javadoc: Mat::Mat(rows, cols, type, data)
    constructor(rows: Int, cols: Int, type: Int, data: ByteBuffer) {

        nativeObjAddr =
            n_Mat(rows, cols, type, data)

        return
    }

    //
    // C++: Mat::Mat(Size size, int type)
    //

    // javadoc: Mat::Mat(size, type)
    constructor(size: Size, type: Int) {

        nativeObjAddr = n_Mat(
            size.width,
            size.height,
            type
        )

        return
    }

    //
    // C++: Mat::Mat(int rows, int cols, int type, Scalar s)
    //

    // javadoc: Mat::Mat(rows, cols, type, s)
    constructor(rows: Int, cols: Int, type: Int, s: Scalar) {

        nativeObjAddr = n_Mat(
            rows,
            cols,
            type,
            s.`val`[0],
            s.`val`[1],
            s.`val`[2],
            s.`val`[3]
        )

        return
    }

    //
    // C++: Mat::Mat(Size size, int type, Scalar s)
    //

    // javadoc: Mat::Mat(size, type, s)
    constructor(size: Size, type: Int, s: Scalar) {

        nativeObjAddr = n_Mat(
            size.width,
            size.height,
            type,
            s.`val`[0],
            s.`val`[1],
            s.`val`[2],
            s.`val`[3]
        )

        return
    }

    //
    // C++: Mat::Mat(Mat m, Range rowRange, Range colRange = Range::all())
    //

    // javadoc: Mat::Mat(m, rowRange, colRange)
    constructor(m: Mat, rowRange: Range, colRange: Range) {

        nativeObjAddr = n_Mat(
            m.nativeObjAddr,
            rowRange.start,
            rowRange.end,
            colRange.start,
            colRange.end
        )

        return
    }

    // javadoc: Mat::Mat(m, rowRange)
    constructor(m: Mat, rowRange: Range) {

        nativeObjAddr = n_Mat(
            m.nativeObjAddr,
            rowRange.start,
            rowRange.end
        )

        return
    }

    //
    // C++: Mat::Mat(Mat m, Rect roi)
    //

    // javadoc: Mat::Mat(m, roi)
    constructor(m: Mat, roi: Rect) {

        nativeObjAddr = n_Mat(
            m.nativeObjAddr,
            roi.y,
            roi.y + roi.height,
            roi.x,
            roi.x + roi.width
        )

        return
    }

    //
    // C++: Mat Mat::adjustROI(int dtop, int dbottom, int dleft, int dright)
    //

    // javadoc: Mat::adjustROI(dtop, dbottom, dleft, dright)
    fun adjustROI(dtop: Int, dbottom: Int, dleft: Int, dright: Int): Mat {

        return Mat(
            n_adjustROI(
                nativeObjAddr,
                dtop,
                dbottom,
                dleft,
                dright
            )
        )
    }

    //
    // C++: void Mat::assignTo(Mat m, int type = -1)
    //

    // javadoc: Mat::assignTo(m, type)
    fun assignTo(m: Mat, type: Int) {

        n_assignTo(
            nativeObjAddr,
            m.nativeObjAddr,
            type
        )

        return
    }

    // javadoc: Mat::assignTo(m)
    fun assignTo(m: Mat) {

        n_assignTo(
            nativeObjAddr,
            m.nativeObjAddr
        )

        return
    }

    //
    // C++: int Mat::channels()
    //

    // javadoc: Mat::channels()
    fun channels(): Int {

        return n_channels(nativeObjAddr)
    }

    //
    // C++: int Mat::checkVector(int elemChannels, int depth = -1, bool
    // requireContinuous = true)
    //

    // javadoc: Mat::checkVector(elemChannels, depth, requireContinuous)
    fun checkVector(elemChannels: Int, depth: Int, requireContinuous: Boolean): Int {

        return n_checkVector(
            nativeObjAddr,
            elemChannels,
            depth,
            requireContinuous
        )
    }

    // javadoc: Mat::checkVector(elemChannels, depth)
    fun checkVector(elemChannels: Int, depth: Int): Int {

        return n_checkVector(
            nativeObjAddr,
            elemChannels,
            depth
        )
    }

    // javadoc: Mat::checkVector(elemChannels)
    fun checkVector(elemChannels: Int): Int {

        return n_checkVector(
            nativeObjAddr,
            elemChannels
        )
    }

    //
    // C++: Mat Mat::clone()
    //

    // javadoc: Mat::clone()
    fun clone(): Mat {

        return Mat(
            n_clone(
                nativeObjAddr
            )
        )
    }

    //
    // C++: Mat Mat::col(int x)
    //

    // javadoc: Mat::col(x)
    fun col(x: Int): Mat {

        return Mat(
            n_col(
                nativeObjAddr,
                x
            )
        )
    }

    //
    // C++: Mat Mat::colRange(int startcol, int endcol)
    //

    // javadoc: Mat::colRange(startcol, endcol)
    fun colRange(startcol: Int, endcol: Int): Mat {

        return Mat(
            n_colRange(
                nativeObjAddr,
                startcol,
                endcol
            )
        )
    }

    //
    // C++: Mat Mat::colRange(Range r)
    //

    // javadoc: Mat::colRange(r)
    fun colRange(r: Range): Mat {

        return Mat(
            n_colRange(
                nativeObjAddr,
                r.start,
                r.end
            )
        )
    }

    //
    // C++: int Mat::dims()
    //

    // javadoc: Mat::dims()
    fun dims(): Int {

        return n_dims(nativeObjAddr)
    }

    //
    // C++: int Mat::cols()
    //

    // javadoc: Mat::cols()
    fun cols(): Int {

        return n_cols(nativeObjAddr)
    }

    //
    // C++: void Mat::convertTo(Mat& m, int rtype, double alpha = 1, double beta
    // = 0)
    //

    // javadoc: Mat::convertTo(m, rtype, alpha, beta)
    fun convertTo(m: Mat, rtype: Int, alpha: Double, beta: Double) {

        n_convertTo(
            nativeObjAddr,
            m.nativeObjAddr,
            rtype,
            alpha,
            beta
        )

        return
    }

    // javadoc: Mat::convertTo(m, rtype, alpha)
    fun convertTo(m: Mat, rtype: Int, alpha: Double) {

        n_convertTo(
            nativeObjAddr,
            m.nativeObjAddr,
            rtype,
            alpha
        )

        return
    }

    // javadoc: Mat::convertTo(m, rtype)
    fun convertTo(m: Mat, rtype: Int) {

        n_convertTo(
            nativeObjAddr,
            m.nativeObjAddr,
            rtype
        )

        return
    }

    //
    // C++: void Mat::copyTo(Mat& m)
    //

    // javadoc: Mat::copyTo(m)
    fun copyTo(m: Mat) {

        n_copyTo(
            nativeObjAddr,
            m.nativeObjAddr
        )

        return
    }

    //
    // C++: void Mat::copyTo(Mat& m, Mat mask)
    //

    // javadoc: Mat::copyTo(m, mask)
    fun copyTo(m: Mat, mask: Mat) {

        n_copyTo(
            nativeObjAddr,
            m.nativeObjAddr,
            mask.nativeObjAddr
        )

        return
    }

    //
    // C++: void Mat::create(int rows, int cols, int type)
    //

    // javadoc: Mat::create(rows, cols, type)
    fun create(rows: Int, cols: Int, type: Int) {

        n_create(
            nativeObjAddr,
            rows,
            cols,
            type
        )

        return
    }

    //
    // C++: void Mat::create(Size size, int type)
    //

    // javadoc: Mat::create(size, type)
    fun create(size: Size, type: Int) {

        n_create(
            nativeObjAddr,
            size.width,
            size.height,
            type
        )

        return
    }

    //
    // C++: Mat Mat::cross(Mat m)
    //

    // javadoc: Mat::cross(m)
    fun cross(m: Mat): Mat {

        return Mat(
            n_cross(
                nativeObjAddr,
                m.nativeObjAddr
            )
        )
    }

    //
    // C++: long Mat::dataAddr()
    //

    // javadoc: Mat::dataAddr()
    fun dataAddr(): Long {

        return n_dataAddr(nativeObjAddr)
    }

    //
    // C++: int Mat::depth()
    //

    // javadoc: Mat::depth()
    fun depth(): Int {

        return n_depth(nativeObjAddr)
    }

    //
    // C++: Mat Mat::diag(int d = 0)
    //

    // javadoc: Mat::diag(d)
    fun diag(d: Int): Mat {

        return Mat(
            n_diag(
                nativeObjAddr,
                d
            )
        )
    }

    // javadoc: Mat::diag()
    fun diag(): Mat {

        return Mat(
            n_diag(
                nativeObjAddr,
                0
            )
        )
    }

    //
    // C++: double Mat::dot(Mat m)
    //

    // javadoc: Mat::dot(m)
    fun dot(m: Mat): Double {

        return n_dot(
            nativeObjAddr,
            m.nativeObjAddr
        )
    }

    //
    // C++: size_t Mat::elemSize()
    //

    // javadoc: Mat::elemSize()
    fun elemSize(): Long {

        return n_elemSize(nativeObjAddr)
    }

    //
    // C++: size_t Mat::elemSize1()
    //

    // javadoc: Mat::elemSize1()
    fun elemSize1(): Long {

        return n_elemSize1(nativeObjAddr)
    }

    //
    // C++: bool Mat::empty()
    //

    // javadoc: Mat::empty()
    fun empty(): Boolean {

        return n_empty(nativeObjAddr)
    }

    //
    // C++: Mat Mat::inv(int method = DECOMP_LU)
    //

    // javadoc: Mat::inv(method)
    fun inv(method: Int): Mat {

        return Mat(
            n_inv(
                nativeObjAddr,
                method
            )
        )
    }

    // javadoc: Mat::inv()
    fun inv(): Mat {

        return Mat(
            n_inv(
                nativeObjAddr
            )
        )
    }

    //
    // C++: void Mat::locateROI(Size wholeSize, Point ofs)
    //

    // javadoc: Mat::locateROI(wholeSize, ofs)
    fun locateROI(wholeSize: Size?, ofs: Point?) {
        val wholeSize_out = DoubleArray(2)
        val ofs_out = DoubleArray(2)
        locateROI_0(
            nativeObjAddr,
            wholeSize_out,
            ofs_out
        )
        if (wholeSize != null) {
            wholeSize.width = wholeSize_out[0]
            wholeSize.height = wholeSize_out[1]
        }
        if (ofs != null) {
            ofs.x = ofs_out[0]
            ofs.y = ofs_out[1]
        }
        return
    }

    //
    // C++: Mat Mat::mul(Mat m, double scale = 1)
    //

    // javadoc: Mat::mul(m, scale)
    fun mul(m: Mat, scale: Double): Mat {

        return Mat(
            n_mul(
                nativeObjAddr,
                m.nativeObjAddr,
                scale
            )
        )
    }

    // javadoc: Mat::mul(m)
    fun mul(m: Mat): Mat {

        return Mat(
            n_mul(
                nativeObjAddr,
                m.nativeObjAddr
            )
        )
    }

    //
    // C++: void Mat::push_back(Mat m)
    //

    // javadoc: Mat::push_back(m)
    fun push_back(m: Mat) {

        n_push_back(
            nativeObjAddr,
            m.nativeObjAddr
        )

        return
    }

    //
    // C++: void Mat::release()
    //

    // javadoc: Mat::release()
    fun release() {

        n_release(nativeObjAddr)

        return
    }

    //
    // C++: Mat Mat::reshape(int cn, int rows = 0)
    //

    // javadoc: Mat::reshape(cn, rows)
    fun reshape(cn: Int, rows: Int): Mat {

        return Mat(
            n_reshape(
                nativeObjAddr,
                cn,
                rows
            )
        )
    }

    // javadoc: Mat::reshape(cn)
    fun reshape(cn: Int): Mat {

        return Mat(
            n_reshape(
                nativeObjAddr,
                cn
            )
        )
    }

    //
    // C++: Mat Mat::row(int y)
    //

    // javadoc: Mat::row(y)
    fun row(y: Int): Mat {

        return Mat(
            n_row(
                nativeObjAddr,
                y
            )
        )
    }

    //
    // C++: Mat Mat::rowRange(int startrow, int endrow)
    //

    // javadoc: Mat::rowRange(startrow, endrow)
    fun rowRange(startrow: Int, endrow: Int): Mat {

        return Mat(
            n_rowRange(
                nativeObjAddr,
                startrow,
                endrow
            )
        )
    }

    //
    // C++: Mat Mat::rowRange(Range r)
    //

    // javadoc: Mat::rowRange(r)
    fun rowRange(r: Range): Mat {

        return Mat(
            n_rowRange(
                nativeObjAddr,
                r.start,
                r.end
            )
        )
    }

    //
    // C++: int Mat::rows()
    //

    // javadoc: Mat::rows()
    fun rows(): Int {

        return n_rows(nativeObjAddr)
    }

    //
    // C++: Mat Mat::operator =(Scalar s)
    //

    // javadoc: Mat::operator =(s)
    fun setTo(s: Scalar): Mat {

        return Mat(
            n_setTo(
                nativeObjAddr,
                s.`val`[0],
                s.`val`[1],
                s.`val`[2],
                s.`val`[3]
            )
        )
    }

    //
    // C++: Mat Mat::setTo(Scalar value, Mat mask = Mat())
    //

    // javadoc: Mat::setTo(value, mask)
    fun setTo(value: Scalar, mask: Mat): Mat {

        return Mat(
            n_setTo(
                nativeObjAddr,
                value.`val`[0],
                value.`val`[1],
                value.`val`[2],
                value.`val`[3],
                mask.nativeObjAddr
            )
        )
    }

    //
    // C++: Mat Mat::setTo(Mat value, Mat mask = Mat())
    //

    // javadoc: Mat::setTo(value, mask)
    fun setTo(value: Mat, mask: Mat): Mat {

        return Mat(
            n_setTo(
                nativeObjAddr,
                value.nativeObjAddr,
                mask.nativeObjAddr
            )
        )
    }

    // javadoc: Mat::setTo(value)
    fun setTo(value: Mat): Mat {

        return Mat(
            n_setTo(
                nativeObjAddr,
                value.nativeObjAddr
            )
        )
    }

    //
    // C++: Size Mat::size()
    //

    // javadoc: Mat::size()
    fun size(): Size {

        return Size(
            n_size(
                nativeObjAddr
            )
        )
    }

    //
    // C++: size_t Mat::step1(int i = 0)
    //

    // javadoc: Mat::step1(i)
    fun step1(i: Int): Long {

        return n_step1(nativeObjAddr, i)
    }

    // javadoc: Mat::step1()
    fun step1(): Long {

        return n_step1(nativeObjAddr)
    }

    //
    // C++: Mat Mat::operator()(int rowStart, int rowEnd, int colStart, int
    // colEnd)
    //

    // javadoc: Mat::operator()(rowStart, rowEnd, colStart, colEnd)
    fun submat(rowStart: Int, rowEnd: Int, colStart: Int, colEnd: Int): Mat {

        return Mat(
            n_submat_rr(
                nativeObjAddr,
                rowStart,
                rowEnd,
                colStart,
                colEnd
            )
        )
    }

    //
    // C++: Mat Mat::operator()(Range rowRange, Range colRange)
    //

    // javadoc: Mat::operator()(rowRange, colRange)
    fun submat(rowRange: Range, colRange: Range): Mat {

        return Mat(
            n_submat_rr(
                nativeObjAddr,
                rowRange.start,
                rowRange.end,
                colRange.start,
                colRange.end
            )
        )
    }

    //
    // C++: Mat Mat::operator()(Rect roi)
    //

    // javadoc: Mat::operator()(roi)
    fun submat(roi: Rect): Mat {

        return Mat(
            n_submat(
                nativeObjAddr,
                roi.x,
                roi.y,
                roi.width,
                roi.height
            )
        )
    }

    //
    // C++: Mat Mat::t()
    //

    // javadoc: Mat::t()
    fun t(): Mat {

        return Mat(
            n_t(
                nativeObjAddr
            )
        )
    }

    //
    // C++: size_t Mat::total()
    //

    // javadoc: Mat::total()
    fun total(): Long {

        return n_total(nativeObjAddr)
    }

    //
    // C++: int Mat::type()
    //

    // javadoc: Mat::type()
    fun type(): Int {

        return n_type(nativeObjAddr)
    }

    @Throws(Throwable::class)
    protected fun finalize() {
        n_delete(nativeObjAddr)
    }

    // javadoc:Mat::toString()
    override fun toString(): String {
        return "Mat [ " +
                rows() + "*" + cols() + "*" + CvType.typeToString(
            type()
        ) +
                ", isCont=" + isContinuous + ", isSubmat=" + isSubmatrix +
                ", nativeObj=0x" + java.lang.Long.toHexString(nativeObjAddr) +
                ", dataAddr=0x" + java.lang.Long.toHexString(dataAddr()) +
                " ]"
    }

    // javadoc:Mat::dump()
    fun dump(): String {
        return nDump(nativeObjAddr)
    }

    // javadoc:Mat::put(row,col,data)
    fun put(row: Int, col: Int, vararg data: Double): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        return nPutD(
            nativeObjAddr,
            row,
            col,
            data.size,
            data
        )
    }

    // javadoc:Mat::put(row,col,data)
    fun put(row: Int, col: Int, data: FloatArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_32F) {
            return nPutF(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::put(row,col,data)
    fun put(row: Int, col: Int, data: IntArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_32S) {
            return nPutI(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::put(row,col,data)
    fun put(row: Int, col: Int, data: ShortArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_16U || CvType.depth(
                t
            ) == CvType.CV_16S
        ) {
            return nPutS(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::put(row,col,data)
    fun put(row: Int, col: Int, data: ByteArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_8U || CvType.depth(
                t
            ) == CvType.CV_8S
        ) {
            return nPutB(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::put(row,col,data,offset,length)
    fun put(row: Int, col: Int, data: ByteArray?, offset: Int, length: Int): Int {
        val t = type()
        if (data == null || length % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_8U || CvType.depth(
                t
            ) == CvType.CV_8S
        ) {
            return nPutBwOffset(
                nativeObjAddr,
                row,
                col,
                length,
                offset,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::get(row,col,data)
    operator fun get(row: Int, col: Int, data: ByteArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_8U || CvType.depth(
                t
            ) == CvType.CV_8S
        ) {
            return nGetB(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::get(row,col,data)
    operator fun get(row: Int, col: Int, data: ShortArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_16U || CvType.depth(
                t
            ) == CvType.CV_16S
        ) {
            return nGetS(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::get(row,col,data)
    operator fun get(row: Int, col: Int, data: IntArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_32S) {
            return nGetI(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::get(row,col,data)
    operator fun get(row: Int, col: Int, data: FloatArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_32F) {
            return nGetF(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::get(row,col,data)
    operator fun get(row: Int, col: Int, data: DoubleArray?): Int {
        val t = type()
        if (data == null || data.size % CvType.channels(t) != 0)
            throw java.lang.UnsupportedOperationException(
                "Provided data element number (" +
                        (data?.size ?: 0) +
                        ") should be multiple of the Mat channels count (" +
                        CvType.channels(t) + ")"
            )
        if (CvType.depth(t) == CvType.CV_64F) {
            return nGetD(
                nativeObjAddr,
                row,
                col,
                data.size,
                data
            )
        }
        throw java.lang.UnsupportedOperationException("Mat data type is not compatible: $t")
    }

    // javadoc:Mat::get(row,col)
    operator fun get(row: Int, col: Int): DoubleArray {
        return nGet(nativeObjAddr, row, col)
    }

    // javadoc:Mat::height()
    fun height(): Int {
        return rows()
    }

    // javadoc:Mat::width()
    fun width(): Int {
        return cols()
    }

    companion object {

        //
        // C++: static Mat Mat::diag(Mat d)
        //

        // javadoc: Mat::diag(d)
        fun diag(d: Mat): Mat {

            return Mat(
                n_diag(
                    d.nativeObjAddr
                )
            )
        }

        //
        // C++: static Mat Mat::eye(int rows, int cols, int type)
        //

        // javadoc: Mat::eye(rows, cols, type)
        fun eye(rows: Int, cols: Int, type: Int): Mat {

            return Mat(
                n_eye(
                    rows,
                    cols,
                    type
                )
            )
        }

        //
        // C++: static Mat Mat::eye(Size size, int type)
        //

        // javadoc: Mat::eye(size, type)
        fun eye(size: Size, type: Int): Mat {

            return Mat(
                n_eye(
                    size.width,
                    size.height,
                    type
                )
            )
        }

        //
        // C++: static Mat Mat::ones(int rows, int cols, int type)
        //

        // javadoc: Mat::ones(rows, cols, type)
        fun ones(rows: Int, cols: Int, type: Int): Mat {

            return Mat(
                n_ones(
                    rows,
                    cols,
                    type
                )
            )
        }

        //
        // C++: static Mat Mat::ones(Size size, int type)
        //

        // javadoc: Mat::ones(size, type)
        fun ones(size: Size, type: Int): Mat {

            return Mat(
                n_ones(
                    size.width,
                    size.height,
                    type
                )
            )
        }

        //
        // C++: static Mat Mat::zeros(int rows, int cols, int type)
        //

        // javadoc: Mat::zeros(rows, cols, type)
        fun zeros(rows: Int, cols: Int, type: Int): Mat {

            return Mat(
                n_zeros(
                    rows,
                    cols,
                    type
                )
            )
        }

        //
        // C++: static Mat Mat::zeros(Size size, int type)
        //

        // javadoc: Mat::zeros(size, type)
        fun zeros(size: Size, type: Int): Mat {

            return Mat(
                n_zeros(
                    size.width,
                    size.height,
                    type
                )
            )
        }







        // C++: Mat::Mat()
        @JvmStatic private external fun n_Mat(): Long

        // C++: Mat::Mat(int rows, int cols, int type)
        @JvmStatic private external fun n_Mat(rows: Int, cols: Int, type: Int): Long

        // C++: Mat::Mat(int rows, int cols, int type, void* data)
        @JvmStatic private external fun n_Mat(rows: Int, cols: Int, type: Int, data: ByteBuffer): Long

        // C++: Mat::Mat(Size size, int type)
        @JvmStatic private external fun n_Mat(size_width: Double, size_height: Double, type: Int): Long

        // C++: Mat::Mat(int rows, int cols, int type, Scalar s)
        @JvmStatic private external fun n_Mat(
            rows: Int,
            cols: Int,
            type: Int,
            s_val0: Double,
            s_val1: Double,
            s_val2: Double,
            s_val3: Double
        ): Long

        // C++: Mat::Mat(Size size, int type, Scalar s)
        @JvmStatic private external fun n_Mat(
            size_width: Double,
            size_height: Double,
            type: Int,
            s_val0: Double,
            s_val1: Double,
            s_val2: Double,
            s_val3: Double
        ): Long

        // C++: Mat::Mat(Mat m, Range rowRange, Range colRange = Range::all())
        @JvmStatic private external fun n_Mat(
            m_nativeObj: Long,
            rowRange_start: Int,
            rowRange_end: Int,
            colRange_start: Int,
            colRange_end: Int
        ): Long

        @JvmStatic private external fun n_Mat(m_nativeObj: Long, rowRange_start: Int, rowRange_end: Int): Long

        // C++: Mat Mat::adjustROI(int dtop, int dbottom, int dleft, int dright)
        @JvmStatic private external fun n_adjustROI(nativeObj: Long, dtop: Int, dbottom: Int, dleft: Int, dright: Int): Long

        // C++: void Mat::assignTo(Mat m, int type = -1)
        @JvmStatic private external fun n_assignTo(nativeObj: Long, m_nativeObj: Long, type: Int)

        @JvmStatic private external fun n_assignTo(nativeObj: Long, m_nativeObj: Long)

        // C++: int Mat::channels()
        @JvmStatic private external fun n_channels(nativeObj: Long): Int

        // C++: int Mat::checkVector(int elemChannels, int depth = -1, bool
        // requireContinuous = true)
        @JvmStatic private external fun n_checkVector(
            nativeObj: Long,
            elemChannels: Int,
            depth: Int,
            requireContinuous: Boolean
        ): Int

        @JvmStatic private external fun n_checkVector(nativeObj: Long, elemChannels: Int, depth: Int): Int

        @JvmStatic private external fun n_checkVector(nativeObj: Long, elemChannels: Int): Int

        // C++: Mat Mat::clone()
        @JvmStatic private external fun n_clone(nativeObj: Long): Long

        // C++: Mat Mat::col(int x)
        @JvmStatic private external fun n_col(nativeObj: Long, x: Int): Long

        // C++: Mat Mat::colRange(int startcol, int endcol)
        @JvmStatic private external fun n_colRange(nativeObj: Long, startcol: Int, endcol: Int): Long

        // C++: int Mat::dims()
        @JvmStatic private external fun n_dims(nativeObj: Long): Int

        // C++: int Mat::cols()
        @JvmStatic private external fun n_cols(nativeObj: Long): Int

        // C++: void Mat::convertTo(Mat& m, int rtype, double alpha = 1, double beta
        // = 0)
        @JvmStatic private external fun n_convertTo(nativeObj: Long, m_nativeObj: Long, rtype: Int, alpha: Double, beta: Double)

        @JvmStatic private external fun n_convertTo(nativeObj: Long, m_nativeObj: Long, rtype: Int, alpha: Double)

        @JvmStatic private external fun n_convertTo(nativeObj: Long, m_nativeObj: Long, rtype: Int)

        // C++: void Mat::copyTo(Mat& m)
        @JvmStatic private external fun n_copyTo(nativeObj: Long, m_nativeObj: Long)

        // C++: void Mat::copyTo(Mat& m, Mat mask)
        @JvmStatic private external fun n_copyTo(nativeObj: Long, m_nativeObj: Long, mask_nativeObj: Long)

        // C++: void Mat::create(int rows, int cols, int type)
        @JvmStatic private external fun n_create(nativeObj: Long, rows: Int, cols: Int, type: Int)

        // C++: void Mat::create(Size size, int type)
        @JvmStatic private external fun n_create(nativeObj: Long, size_width: Double, size_height: Double, type: Int)

        // C++: Mat Mat::cross(Mat m)
        @JvmStatic private external fun n_cross(nativeObj: Long, m_nativeObj: Long): Long

        // C++: long Mat::dataAddr()
        @JvmStatic private external fun n_dataAddr(nativeObj: Long): Long

        // C++: int Mat::depth()
        @JvmStatic private external fun n_depth(nativeObj: Long): Int

        // C++: Mat Mat::diag(int d = 0)
        @JvmStatic private external fun n_diag(nativeObj: Long, d: Int): Long

        // C++: static Mat Mat::diag(Mat d)
        @JvmStatic private external fun n_diag(d_nativeObj: Long): Long

        // C++: double Mat::dot(Mat m)
        @JvmStatic private external fun n_dot(nativeObj: Long, m_nativeObj: Long): Double

        // C++: size_t Mat::elemSize()
        @JvmStatic private external fun n_elemSize(nativeObj: Long): Long

        // C++: size_t Mat::elemSize1()
        @JvmStatic private external fun n_elemSize1(nativeObj: Long): Long

        // C++: bool Mat::empty()
        @JvmStatic private external fun n_empty(nativeObj: Long): Boolean

        // C++: static Mat Mat::eye(int rows, int cols, int type)
        @JvmStatic private external fun n_eye(rows: Int, cols: Int, type: Int): Long

        // C++: static Mat Mat::eye(Size size, int type)
        @JvmStatic private external fun n_eye(size_width: Double, size_height: Double, type: Int): Long

        // C++: Mat Mat::inv(int method = DECOMP_LU)
        @JvmStatic private external fun n_inv(nativeObj: Long, method: Int): Long

        @JvmStatic private external fun n_inv(nativeObj: Long): Long

        // C++: bool Mat::isContinuous()
        @JvmStatic private external fun n_isContinuous(nativeObj: Long): Boolean

        // C++: bool Mat::isSubmatrix()
        @JvmStatic private external fun n_isSubmatrix(nativeObj: Long): Boolean

        // C++: void Mat::locateROI(Size wholeSize, Point ofs)
        @JvmStatic private external fun locateROI_0(nativeObj: Long, wholeSize_out: DoubleArray, ofs_out: DoubleArray)

        // C++: Mat Mat::mul(Mat m, double scale = 1)
        @JvmStatic private external fun n_mul(nativeObj: Long, m_nativeObj: Long, scale: Double): Long

        @JvmStatic private external fun n_mul(nativeObj: Long, m_nativeObj: Long): Long

        // C++: static Mat Mat::ones(int rows, int cols, int type)
        @JvmStatic private external fun n_ones(rows: Int, cols: Int, type: Int): Long

        // C++: static Mat Mat::ones(Size size, int type)
        @JvmStatic private external fun n_ones(size_width: Double, size_height: Double, type: Int): Long

        // C++: void Mat::push_back(Mat m)
        @JvmStatic private external fun n_push_back(nativeObj: Long, m_nativeObj: Long)

        // C++: void Mat::release()
        @JvmStatic private external fun n_release(nativeObj: Long)

        // C++: Mat Mat::reshape(int cn, int rows = 0)
        @JvmStatic private external fun n_reshape(nativeObj: Long, cn: Int, rows: Int): Long

        @JvmStatic private external fun n_reshape(nativeObj: Long, cn: Int): Long

        // C++: Mat Mat::row(int y)
        @JvmStatic private external fun n_row(nativeObj: Long, y: Int): Long

        // C++: Mat Mat::rowRange(int startrow, int endrow)
        @JvmStatic private external fun n_rowRange(nativeObj: Long, startrow: Int, endrow: Int): Long

        // C++: int Mat::rows()
        @JvmStatic private external fun n_rows(nativeObj: Long): Int

        // C++: Mat Mat::operator =(Scalar s)
        @JvmStatic private external fun n_setTo(
            nativeObj: Long,
            s_val0: Double,
            s_val1: Double,
            s_val2: Double,
            s_val3: Double
        ): Long

        // C++: Mat Mat::setTo(Scalar value, Mat mask = Mat())
        @JvmStatic private external fun n_setTo(
            nativeObj: Long,
            s_val0: Double,
            s_val1: Double,
            s_val2: Double,
            s_val3: Double,
            mask_nativeObj: Long
        ): Long

        // C++: Mat Mat::setTo(Mat value, Mat mask = Mat())
        @JvmStatic private external fun n_setTo(nativeObj: Long, value_nativeObj: Long, mask_nativeObj: Long): Long

        @JvmStatic private external fun n_setTo(nativeObj: Long, value_nativeObj: Long): Long

        // C++: Size Mat::size()
        @JvmStatic private external fun n_size(nativeObj: Long): DoubleArray

        // C++: size_t Mat::step1(int i = 0)
        @JvmStatic private external fun n_step1(nativeObj: Long, i: Int): Long

        @JvmStatic private external fun n_step1(nativeObj: Long): Long

        // C++: Mat Mat::operator()(Range rowRange, Range colRange)
        @JvmStatic private external fun n_submat_rr(
            nativeObj: Long,
            rowRange_start: Int,
            rowRange_end: Int,
            colRange_start: Int,
            colRange_end: Int
        ): Long

        // C++: Mat Mat::operator()(Rect roi)
        @JvmStatic private external fun n_submat(nativeObj: Long, roi_x: Int, roi_y: Int, roi_width: Int, roi_height: Int): Long

        // C++: Mat Mat::t()
        @JvmStatic private external fun n_t(nativeObj: Long): Long

        // C++: size_t Mat::total()
        @JvmStatic private external fun n_total(nativeObj: Long): Long

        // C++: int Mat::type()
        @JvmStatic private external fun n_type(nativeObj: Long): Int

        // C++: static Mat Mat::zeros(int rows, int cols, int type)
        @JvmStatic private external fun n_zeros(rows: Int, cols: Int, type: Int): Long

        // C++: static Mat Mat::zeros(Size size, int type)
        @JvmStatic private external fun n_zeros(size_width: Double, size_height: Double, type: Int): Long

        // native support for java finalize()
        @JvmStatic private external fun n_delete(nativeObj: Long)

        @JvmStatic private external fun nPutD(self: Long, row: Int, col: Int, count: Int, data: DoubleArray): Int

        @JvmStatic private external fun nPutF(self: Long, row: Int, col: Int, count: Int, data: FloatArray): Int

        @JvmStatic private external fun nPutI(self: Long, row: Int, col: Int, count: Int, data: IntArray): Int

        @JvmStatic private external fun nPutS(self: Long, row: Int, col: Int, count: Int, data: ShortArray): Int

        @JvmStatic private external fun nPutB(self: Long, row: Int, col: Int, count: Int, data: ByteArray): Int

        @JvmStatic private external fun nPutBwOffset(self: Long, row: Int, col: Int, count: Int, offset: Int, data: ByteArray): Int

        @JvmStatic private external fun nGetB(self: Long, row: Int, col: Int, count: Int, vals: ByteArray): Int

        @JvmStatic private external fun nGetS(self: Long, row: Int, col: Int, count: Int, vals: ShortArray): Int

        @JvmStatic private external fun nGetI(self: Long, row: Int, col: Int, count: Int, vals: IntArray): Int

        @JvmStatic private external fun nGetF(self: Long, row: Int, col: Int, count: Int, vals: FloatArray): Int

        @JvmStatic private external fun nGetD(self: Long, row: Int, col: Int, count: Int, vals: DoubleArray): Int

        @JvmStatic private external fun nGet(self: Long, row: Int, col: Int): DoubleArray

        @JvmStatic private external fun nDump(self: Long): String
    }







}
