package com.qfleng.cvkit.cv

object Imgcodecs {
    // C++: enum ImwriteFlags
    const val IMWRITE_JPEG_QUALITY = 1
    const val IMWRITE_JPEG_PROGRESSIVE = 2
    const val IMWRITE_JPEG_OPTIMIZE = 3
    const val IMWRITE_JPEG_RST_INTERVAL = 4
    const val IMWRITE_JPEG_LUMA_QUALITY = 5
    const val IMWRITE_JPEG_CHROMA_QUALITY = 6
    const val IMWRITE_PNG_COMPRESSION = 16
    const val IMWRITE_PNG_STRATEGY = 17
    const val IMWRITE_PNG_BILEVEL = 18
    const val IMWRITE_PXM_BINARY = 32
    const val IMWRITE_EXR_TYPE = (3 shl 4) + 0
    const val IMWRITE_WEBP_QUALITY = 64
    const val IMWRITE_PAM_TUPLETYPE = 128
    const val IMWRITE_TIFF_RESUNIT = 256
    const val IMWRITE_TIFF_XDPI = 257
    const val IMWRITE_TIFF_YDPI = 258
    const val IMWRITE_TIFF_COMPRESSION = 259
    const val IMWRITE_JPEG2000_COMPRESSION_X1000 = 272

    // C++: enum ImreadModes
    const val IMREAD_UNCHANGED = -1
    const val IMREAD_GRAYSCALE = 0
    const val IMREAD_COLOR = 1
    const val IMREAD_ANYDEPTH = 2
    const val IMREAD_ANYCOLOR = 4
    const val IMREAD_LOAD_GDAL = 8
    const val IMREAD_REDUCED_GRAYSCALE_2 = 16
    const val IMREAD_REDUCED_COLOR_2 = 17
    const val IMREAD_REDUCED_GRAYSCALE_4 = 32
    const val IMREAD_REDUCED_COLOR_4 = 33
    const val IMREAD_REDUCED_GRAYSCALE_8 = 64
    const val IMREAD_REDUCED_COLOR_8 = 65
    const val IMREAD_IGNORE_ORIENTATION = 128

    // C++: enum ImwritePAMFlags
    const val IMWRITE_PAM_FORMAT_NULL = 0
    const val IMWRITE_PAM_FORMAT_BLACKANDWHITE = 1
    const val IMWRITE_PAM_FORMAT_GRAYSCALE = 2
    const val IMWRITE_PAM_FORMAT_GRAYSCALE_ALPHA = 3
    const val IMWRITE_PAM_FORMAT_RGB = 4
    const val IMWRITE_PAM_FORMAT_RGB_ALPHA = 5

    // C++: enum ImwriteEXRTypeFlags
    const val IMWRITE_EXR_TYPE_HALF = 1
    const val IMWRITE_EXR_TYPE_FLOAT = 2

    // C++: enum ImwritePNGFlags
    const val IMWRITE_PNG_STRATEGY_DEFAULT = 0
    const val IMWRITE_PNG_STRATEGY_FILTERED = 1
    const val IMWRITE_PNG_STRATEGY_HUFFMAN_ONLY = 2
    const val IMWRITE_PNG_STRATEGY_RLE = 3
    const val IMWRITE_PNG_STRATEGY_FIXED = 4
}