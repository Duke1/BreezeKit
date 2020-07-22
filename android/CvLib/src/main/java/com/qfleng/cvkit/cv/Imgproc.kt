//
// This file is auto-generated. Please don't modify it!
//
package com.qfleng.cvkit.cv

// C++: class Imgproc
//javadoc: Imgproc
object Imgproc {
    private const val IPL_BORDER_CONSTANT = 0
    private const val IPL_BORDER_REPLICATE = 1
    private const val IPL_BORDER_REFLECT = 2
    private const val IPL_BORDER_WRAP = 3
    private const val IPL_BORDER_REFLECT_101 = 4
    private const val IPL_BORDER_TRANSPARENT = 5
    private const val CV_INTER_NN = 0
    private const val CV_INTER_LINEAR = 1
    private const val CV_INTER_CUBIC = 2
    private const val CV_INTER_AREA = 3
    private const val CV_INTER_LANCZOS4 = 4
    private const val CV_MOP_ERODE = 0
    private const val CV_MOP_DILATE = 1
    private const val CV_MOP_OPEN = 2
    private const val CV_MOP_CLOSE = 3
    private const val CV_MOP_GRADIENT = 4
    private const val CV_MOP_TOPHAT = 5
    private const val CV_MOP_BLACKHAT = 6
    private const val CV_RETR_EXTERNAL = 0
    private const val CV_RETR_LIST = 1
    private const val CV_RETR_CCOMP = 2
    private const val CV_RETR_TREE = 3
    private const val CV_RETR_FLOODFILL = 4
    private const val CV_CHAIN_APPROX_NONE = 1
    private const val CV_CHAIN_APPROX_SIMPLE = 2
    private const val CV_CHAIN_APPROX_TC89_L1 = 3
    private const val CV_CHAIN_APPROX_TC89_KCOS = 4
    private const val CV_THRESH_BINARY = 0
    private const val CV_THRESH_BINARY_INV = 1
    private const val CV_THRESH_TRUNC = 2
    private const val CV_THRESH_TOZERO = 3
    private const val CV_THRESH_TOZERO_INV = 4
    private const val CV_THRESH_MASK = 7
    private const val CV_THRESH_OTSU = 8
    private const val CV_THRESH_TRIANGLE = 16
    const val LINE_AA = 16
    const val LINE_8 = 8
    const val LINE_4 = 4
    const val CV_BLUR_NO_SCALE = 0
    const val CV_BLUR = 1
    const val CV_GAUSSIAN = 2
    const val CV_MEDIAN = 3
    const val CV_BILATERAL = 4
    const val CV_GAUSSIAN_5x5 = 7
    const val CV_SCHARR = -1
    const val CV_MAX_SOBEL_KSIZE = 7
    const val CV_RGBA2mRGBA = 125
    const val CV_mRGBA2RGBA = 126
    const val CV_WARP_FILL_OUTLIERS = 8
    const val CV_WARP_INVERSE_MAP = 16
    const val CV_SHAPE_RECT = 0
    const val CV_SHAPE_CROSS = 1
    const val CV_SHAPE_ELLIPSE = 2
    const val CV_SHAPE_CUSTOM = 100
    const val CV_CHAIN_CODE = 0
    const val CV_LINK_RUNS = 5
    const val CV_POLY_APPROX_DP = 0
    const val CV_CONTOURS_MATCH_I1 = 1
    const val CV_CONTOURS_MATCH_I2 = 2
    const val CV_CONTOURS_MATCH_I3 = 3
    const val CV_CLOCKWISE = 1
    const val CV_COUNTER_CLOCKWISE = 2
    const val CV_COMP_CORREL = 0
    const val CV_COMP_CHISQR = 1
    const val CV_COMP_INTERSECT = 2
    const val CV_COMP_BHATTACHARYYA = 3
    const val CV_COMP_HELLINGER =
        CV_COMP_BHATTACHARYYA
    const val CV_COMP_CHISQR_ALT = 4
    const val CV_COMP_KL_DIV = 5
    const val CV_DIST_MASK_3 = 3
    const val CV_DIST_MASK_5 = 5
    const val CV_DIST_MASK_PRECISE = 0
    const val CV_DIST_LABEL_CCOMP = 0
    const val CV_DIST_LABEL_PIXEL = 1
    const val CV_DIST_USER = -1
    const val CV_DIST_L1 = 1
    const val CV_DIST_L2 = 2
    const val CV_DIST_C = 3
    const val CV_DIST_L12 = 4
    const val CV_DIST_FAIR = 5
    const val CV_DIST_WELSCH = 6
    const val CV_DIST_HUBER = 7
    const val CV_CANNY_L2_GRADIENT = 1 shl 31
    const val CV_HOUGH_STANDARD = 0
    const val CV_HOUGH_PROBABILISTIC = 1
    const val CV_HOUGH_MULTI_SCALE = 2
    const val CV_HOUGH_GRADIENT = 3
    const val MORPH_ERODE = 0
    const val MORPH_DILATE = 1
    const val MORPH_OPEN = 2
    const val MORPH_CLOSE = 3
    const val MORPH_GRADIENT = 4
    const val MORPH_TOPHAT = 5
    const val MORPH_BLACKHAT = 6
    const val MORPH_HITMISS = 7
    const val MORPH_RECT = 0
    const val MORPH_CROSS = 1
    const val MORPH_ELLIPSE = 2
    const val INTER_NEAREST = 0
    const val INTER_LINEAR = 1
    const val INTER_CUBIC = 2
    const val INTER_AREA = 3
    const val INTER_LANCZOS4 = 4
    const val INTER_LINEAR_EXACT = 5
    const val INTER_MAX = 7
    const val WARP_FILL_OUTLIERS = 8
    const val WARP_INVERSE_MAP = 16
    const val WARP_POLAR_LINEAR = 0
    const val WARP_POLAR_LOG = 256
    const val INTER_BITS = 5
    const val INTER_BITS2 = INTER_BITS * 2
    const val INTER_TAB_SIZE = 1 shl INTER_BITS
    const val INTER_TAB_SIZE2 = INTER_TAB_SIZE * INTER_TAB_SIZE
    const val DIST_USER = -1
    const val DIST_L1 = 1
    const val DIST_L2 = 2
    const val DIST_C = 3
    const val DIST_L12 = 4
    const val DIST_FAIR = 5
    const val DIST_WELSCH = 6
    const val DIST_HUBER = 7
    const val DIST_MASK_3 = 3
    const val DIST_MASK_5 = 5
    const val DIST_MASK_PRECISE = 0
    const val THRESH_BINARY = 0
    const val THRESH_BINARY_INV = 1
    const val THRESH_TRUNC = 2
    const val THRESH_TOZERO = 3
    const val THRESH_TOZERO_INV = 4
    const val THRESH_MASK = 7
    const val THRESH_OTSU = 8
    const val THRESH_TRIANGLE = 16
    const val ADAPTIVE_THRESH_MEAN_C = 0
    const val ADAPTIVE_THRESH_GAUSSIAN_C = 1
    const val PROJ_SPHERICAL_ORTHO = 0
    const val PROJ_SPHERICAL_EQRECT = 1
    const val GC_BGD = 0
    const val GC_FGD = 1
    const val GC_PR_BGD = 2
    const val GC_PR_FGD = 3
    const val GC_INIT_WITH_RECT = 0
    const val GC_INIT_WITH_MASK = 1
    const val GC_EVAL = 2
    const val GC_EVAL_FREEZE_MODEL = 3
    const val DIST_LABEL_CCOMP = 0
    const val DIST_LABEL_PIXEL = 1
    const val FLOODFILL_FIXED_RANGE = 1 shl 16
    const val FLOODFILL_MASK_ONLY = 1 shl 17
    const val CC_STAT_LEFT = 0
    const val CC_STAT_TOP = 1
    const val CC_STAT_WIDTH = 2
    const val CC_STAT_HEIGHT = 3
    const val CC_STAT_AREA = 4
    const val CC_STAT_MAX = 5
    const val CCL_WU = 0
    const val CCL_DEFAULT = -1
    const val CCL_GRANA = 1
    const val RETR_EXTERNAL = 0
    const val RETR_LIST = 1
    const val RETR_CCOMP = 2
    const val RETR_TREE = 3
    const val RETR_FLOODFILL = 4
    const val CHAIN_APPROX_NONE = 1
    const val CHAIN_APPROX_SIMPLE = 2
    const val CHAIN_APPROX_TC89_L1 = 3
    const val CHAIN_APPROX_TC89_KCOS = 4
    const val CONTOURS_MATCH_I1 = 1
    const val CONTOURS_MATCH_I2 = 2
    const val CONTOURS_MATCH_I3 = 3
    const val HOUGH_STANDARD = 0
    const val HOUGH_PROBABILISTIC = 1
    const val HOUGH_MULTI_SCALE = 2
    const val HOUGH_GRADIENT = 3
    const val LSD_REFINE_NONE = 0
    const val LSD_REFINE_STD = 1
    const val LSD_REFINE_ADV = 2
    const val HISTCMP_CORREL = 0
    const val HISTCMP_CHISQR = 1
    const val HISTCMP_INTERSECT = 2
    const val HISTCMP_BHATTACHARYYA = 3
    const val HISTCMP_HELLINGER =
        HISTCMP_BHATTACHARYYA
    const val HISTCMP_CHISQR_ALT = 4
    const val HISTCMP_KL_DIV = 5
    const val COLOR_BGR2BGRA = 0
    const val COLOR_RGB2RGBA = COLOR_BGR2BGRA
    const val COLOR_BGRA2BGR = 1
    const val COLOR_RGBA2RGB = COLOR_BGRA2BGR
    const val COLOR_BGR2RGBA = 2
    const val COLOR_RGB2BGRA = COLOR_BGR2RGBA
    const val COLOR_RGBA2BGR = 3
    const val COLOR_BGRA2RGB = COLOR_RGBA2BGR
    const val COLOR_BGR2RGB = 4
    const val COLOR_RGB2BGR = COLOR_BGR2RGB
    const val COLOR_BGRA2RGBA = 5
    const val COLOR_RGBA2BGRA = COLOR_BGRA2RGBA
    const val COLOR_BGR2GRAY = 6
    const val COLOR_RGB2GRAY = 7
    const val COLOR_GRAY2BGR = 8
    const val COLOR_GRAY2RGB = COLOR_GRAY2BGR
    const val COLOR_GRAY2BGRA = 9
    const val COLOR_GRAY2RGBA = COLOR_GRAY2BGRA
    const val COLOR_BGRA2GRAY = 10
    const val COLOR_RGBA2GRAY = 11
    const val COLOR_BGR2BGR565 = 12
    const val COLOR_RGB2BGR565 = 13
    const val COLOR_BGR5652BGR = 14
    const val COLOR_BGR5652RGB = 15
    const val COLOR_BGRA2BGR565 = 16
    const val COLOR_RGBA2BGR565 = 17
    const val COLOR_BGR5652BGRA = 18
    const val COLOR_BGR5652RGBA = 19
    const val COLOR_GRAY2BGR565 = 20
    const val COLOR_BGR5652GRAY = 21
    const val COLOR_BGR2BGR555 = 22
    const val COLOR_RGB2BGR555 = 23
    const val COLOR_BGR5552BGR = 24
    const val COLOR_BGR5552RGB = 25
    const val COLOR_BGRA2BGR555 = 26
    const val COLOR_RGBA2BGR555 = 27
    const val COLOR_BGR5552BGRA = 28
    const val COLOR_BGR5552RGBA = 29
    const val COLOR_GRAY2BGR555 = 30
    const val COLOR_BGR5552GRAY = 31
    const val COLOR_BGR2XYZ = 32
    const val COLOR_RGB2XYZ = 33
    const val COLOR_XYZ2BGR = 34
    const val COLOR_XYZ2RGB = 35
    const val COLOR_BGR2YCrCb = 36
    const val COLOR_RGB2YCrCb = 37
    const val COLOR_YCrCb2BGR = 38
    const val COLOR_YCrCb2RGB = 39
    const val COLOR_BGR2HSV = 40
    const val COLOR_RGB2HSV = 41
    const val COLOR_BGR2Lab = 44
    const val COLOR_RGB2Lab = 45
    const val COLOR_BGR2Luv = 50
    const val COLOR_RGB2Luv = 51
    const val COLOR_BGR2HLS = 52
    const val COLOR_RGB2HLS = 53
    const val COLOR_HSV2BGR = 54
    const val COLOR_HSV2RGB = 55
    const val COLOR_Lab2BGR = 56
    const val COLOR_Lab2RGB = 57
    const val COLOR_Luv2BGR = 58
    const val COLOR_Luv2RGB = 59
    const val COLOR_HLS2BGR = 60
    const val COLOR_HLS2RGB = 61
    const val COLOR_BGR2HSV_FULL = 66
    const val COLOR_RGB2HSV_FULL = 67
    const val COLOR_BGR2HLS_FULL = 68
    const val COLOR_RGB2HLS_FULL = 69
    const val COLOR_HSV2BGR_FULL = 70
    const val COLOR_HSV2RGB_FULL = 71
    const val COLOR_HLS2BGR_FULL = 72
    const val COLOR_HLS2RGB_FULL = 73
    const val COLOR_LBGR2Lab = 74
    const val COLOR_LRGB2Lab = 75
    const val COLOR_LBGR2Luv = 76
    const val COLOR_LRGB2Luv = 77
    const val COLOR_Lab2LBGR = 78
    const val COLOR_Lab2LRGB = 79
    const val COLOR_Luv2LBGR = 80
    const val COLOR_Luv2LRGB = 81
    const val COLOR_BGR2YUV = 82
    const val COLOR_RGB2YUV = 83
    const val COLOR_YUV2BGR = 84
    const val COLOR_YUV2RGB = 85
    const val COLOR_YUV2RGB_NV12 = 90
    const val COLOR_YUV2BGR_NV12 = 91
    const val COLOR_YUV2RGB_NV21 = 92
    const val COLOR_YUV2BGR_NV21 = 93
    const val COLOR_YUV420sp2RGB = COLOR_YUV2RGB_NV21
    const val COLOR_YUV420sp2BGR = COLOR_YUV2BGR_NV21
    const val COLOR_YUV2RGBA_NV12 = 94
    const val COLOR_YUV2BGRA_NV12 = 95
    const val COLOR_YUV2RGBA_NV21 = 96
    const val COLOR_YUV2BGRA_NV21 = 97
    const val COLOR_YUV420sp2RGBA =
        COLOR_YUV2RGBA_NV21
    const val COLOR_YUV420sp2BGRA =
        COLOR_YUV2BGRA_NV21
    const val COLOR_YUV2RGB_YV12 = 98
    const val COLOR_YUV2BGR_YV12 = 99
    const val COLOR_YUV2RGB_IYUV = 100
    const val COLOR_YUV2BGR_IYUV = 101
    const val COLOR_YUV2RGB_I420 = COLOR_YUV2RGB_IYUV
    const val COLOR_YUV2BGR_I420 = COLOR_YUV2BGR_IYUV
    const val COLOR_YUV420p2RGB = COLOR_YUV2RGB_YV12
    const val COLOR_YUV420p2BGR = COLOR_YUV2BGR_YV12
    const val COLOR_YUV2RGBA_YV12 = 102
    const val COLOR_YUV2BGRA_YV12 = 103
    const val COLOR_YUV2RGBA_IYUV = 104
    const val COLOR_YUV2BGRA_IYUV = 105
    const val COLOR_YUV2RGBA_I420 =
        COLOR_YUV2RGBA_IYUV
    const val COLOR_YUV2BGRA_I420 =
        COLOR_YUV2BGRA_IYUV
    const val COLOR_YUV420p2RGBA =
        COLOR_YUV2RGBA_YV12
    const val COLOR_YUV420p2BGRA =
        COLOR_YUV2BGRA_YV12
    const val COLOR_YUV2GRAY_420 = 106
    const val COLOR_YUV2GRAY_NV21 =
        COLOR_YUV2GRAY_420
    const val COLOR_YUV2GRAY_NV12 =
        COLOR_YUV2GRAY_420
    const val COLOR_YUV2GRAY_YV12 =
        COLOR_YUV2GRAY_420
    const val COLOR_YUV2GRAY_IYUV =
        COLOR_YUV2GRAY_420
    const val COLOR_YUV2GRAY_I420 =
        COLOR_YUV2GRAY_420
    const val COLOR_YUV420sp2GRAY =
        COLOR_YUV2GRAY_420
    const val COLOR_YUV420p2GRAY = COLOR_YUV2GRAY_420
    const val COLOR_YUV2RGB_UYVY = 107
    const val COLOR_YUV2BGR_UYVY = 108
    const val COLOR_YUV2RGB_Y422 = COLOR_YUV2RGB_UYVY
    const val COLOR_YUV2BGR_Y422 = COLOR_YUV2BGR_UYVY
    const val COLOR_YUV2RGB_UYNV = COLOR_YUV2RGB_UYVY
    const val COLOR_YUV2BGR_UYNV = COLOR_YUV2BGR_UYVY
    const val COLOR_YUV2RGBA_UYVY = 111
    const val COLOR_YUV2BGRA_UYVY = 112
    const val COLOR_YUV2RGBA_Y422 =
        COLOR_YUV2RGBA_UYVY
    const val COLOR_YUV2BGRA_Y422 =
        COLOR_YUV2BGRA_UYVY
    const val COLOR_YUV2RGBA_UYNV =
        COLOR_YUV2RGBA_UYVY
    const val COLOR_YUV2BGRA_UYNV =
        COLOR_YUV2BGRA_UYVY
    const val COLOR_YUV2RGB_YUY2 = 115
    const val COLOR_YUV2BGR_YUY2 = 116
    const val COLOR_YUV2RGB_YVYU = 117
    const val COLOR_YUV2BGR_YVYU = 118
    const val COLOR_YUV2RGB_YUYV = COLOR_YUV2RGB_YUY2
    const val COLOR_YUV2BGR_YUYV = COLOR_YUV2BGR_YUY2
    const val COLOR_YUV2RGB_YUNV = COLOR_YUV2RGB_YUY2
    const val COLOR_YUV2BGR_YUNV = COLOR_YUV2BGR_YUY2
    const val COLOR_YUV2RGBA_YUY2 = 119
    const val COLOR_YUV2BGRA_YUY2 = 120
    const val COLOR_YUV2RGBA_YVYU = 121
    const val COLOR_YUV2BGRA_YVYU = 122
    const val COLOR_YUV2RGBA_YUYV =
        COLOR_YUV2RGBA_YUY2
    const val COLOR_YUV2BGRA_YUYV =
        COLOR_YUV2BGRA_YUY2
    const val COLOR_YUV2RGBA_YUNV =
        COLOR_YUV2RGBA_YUY2
    const val COLOR_YUV2BGRA_YUNV =
        COLOR_YUV2BGRA_YUY2
    const val COLOR_YUV2GRAY_UYVY = 123
    const val COLOR_YUV2GRAY_YUY2 = 124
    const val COLOR_YUV2GRAY_Y422 =
        COLOR_YUV2GRAY_UYVY
    const val COLOR_YUV2GRAY_UYNV =
        COLOR_YUV2GRAY_UYVY
    const val COLOR_YUV2GRAY_YVYU =
        COLOR_YUV2GRAY_YUY2
    const val COLOR_YUV2GRAY_YUYV =
        COLOR_YUV2GRAY_YUY2
    const val COLOR_YUV2GRAY_YUNV =
        COLOR_YUV2GRAY_YUY2
    const val COLOR_RGBA2mRGBA = 125
    const val COLOR_mRGBA2RGBA = 126
    const val COLOR_RGB2YUV_I420 = 127
    const val COLOR_BGR2YUV_I420 = 128
    const val COLOR_RGB2YUV_IYUV = COLOR_RGB2YUV_I420
    const val COLOR_BGR2YUV_IYUV = COLOR_BGR2YUV_I420
    const val COLOR_RGBA2YUV_I420 = 129
    const val COLOR_BGRA2YUV_I420 = 130
    const val COLOR_RGBA2YUV_IYUV =
        COLOR_RGBA2YUV_I420
    const val COLOR_BGRA2YUV_IYUV =
        COLOR_BGRA2YUV_I420
    const val COLOR_RGB2YUV_YV12 = 131
    const val COLOR_BGR2YUV_YV12 = 132
    const val COLOR_RGBA2YUV_YV12 = 133
    const val COLOR_BGRA2YUV_YV12 = 134
    const val COLOR_BayerBG2BGR = 46
    const val COLOR_BayerGB2BGR = 47
    const val COLOR_BayerRG2BGR = 48
    const val COLOR_BayerGR2BGR = 49
    const val COLOR_BayerBG2RGB = COLOR_BayerRG2BGR
    const val COLOR_BayerGB2RGB = COLOR_BayerGR2BGR
    const val COLOR_BayerRG2RGB = COLOR_BayerBG2BGR
    const val COLOR_BayerGR2RGB = COLOR_BayerGB2BGR
    const val COLOR_BayerBG2GRAY = 86
    const val COLOR_BayerGB2GRAY = 87
    const val COLOR_BayerRG2GRAY = 88
    const val COLOR_BayerGR2GRAY = 89
    const val COLOR_BayerBG2BGR_VNG = 62
    const val COLOR_BayerGB2BGR_VNG = 63
    const val COLOR_BayerRG2BGR_VNG = 64
    const val COLOR_BayerGR2BGR_VNG = 65
    const val COLOR_BayerBG2RGB_VNG =
        COLOR_BayerRG2BGR_VNG
    const val COLOR_BayerGB2RGB_VNG =
        COLOR_BayerGR2BGR_VNG
    const val COLOR_BayerRG2RGB_VNG =
        COLOR_BayerBG2BGR_VNG
    const val COLOR_BayerGR2RGB_VNG =
        COLOR_BayerGB2BGR_VNG
    const val COLOR_BayerBG2BGR_EA = 135
    const val COLOR_BayerGB2BGR_EA = 136
    const val COLOR_BayerRG2BGR_EA = 137
    const val COLOR_BayerGR2BGR_EA = 138
    const val COLOR_BayerBG2RGB_EA =
        COLOR_BayerRG2BGR_EA
    const val COLOR_BayerGB2RGB_EA =
        COLOR_BayerGR2BGR_EA
    const val COLOR_BayerRG2RGB_EA =
        COLOR_BayerBG2BGR_EA
    const val COLOR_BayerGR2RGB_EA =
        COLOR_BayerGB2BGR_EA
    const val COLOR_BayerBG2BGRA = 139
    const val COLOR_BayerGB2BGRA = 140
    const val COLOR_BayerRG2BGRA = 141
    const val COLOR_BayerGR2BGRA = 142
    const val COLOR_BayerBG2RGBA = COLOR_BayerRG2BGRA
    const val COLOR_BayerGB2RGBA = COLOR_BayerGR2BGRA
    const val COLOR_BayerRG2RGBA = COLOR_BayerBG2BGRA
    const val COLOR_BayerGR2RGBA = COLOR_BayerGB2BGRA
    const val COLOR_COLORCVT_MAX = 143
    const val INTERSECT_NONE = 0
    const val INTERSECT_PARTIAL = 1
    const val INTERSECT_FULL = 2
    const val TM_SQDIFF = 0
    const val TM_SQDIFF_NORMED = 1
    const val TM_CCORR = 2
    const val TM_CCORR_NORMED = 3
    const val TM_CCOEFF = 4
    const val TM_CCOEFF_NORMED = 5
    const val COLORMAP_AUTUMN = 0
    const val COLORMAP_BONE = 1
    const val COLORMAP_JET = 2
    const val COLORMAP_WINTER = 3
    const val COLORMAP_RAINBOW = 4
    const val COLORMAP_OCEAN = 5
    const val COLORMAP_SUMMER = 6
    const val COLORMAP_SPRING = 7
    const val COLORMAP_COOL = 8
    const val COLORMAP_HSV = 9
    const val COLORMAP_PINK = 10
    const val COLORMAP_HOT = 11
    const val COLORMAP_PARULA = 12
    const val MARKER_CROSS = 0
    const val MARKER_TILTED_CROSS = 1
    const val MARKER_STAR = 2
    const val MARKER_DIAMOND = 3
    const val MARKER_SQUARE = 4
    const val MARKER_TRIANGLE_UP = 5
    const val MARKER_TRIANGLE_DOWN = 6
}