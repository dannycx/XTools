package com.danny.bt.scan

/**
 *
 *
 * @author danny
 * @since 2022/3/4
 */
class ScanFilter(type: Int, var matchFilter: String?){
    /**
     * 不过滤
     */
    val NO_FILTER = 0

    /**
     * 精准过滤
     */
    val EXACT_FILTER = 1

    /**
     * 前缀过滤
     */
    val PRE_FILTER = 2

    /**
     * 后缀过滤
     */
    val SUF_FILTER = 3

    /**
     * 前缀过滤contains
     */
    val CONTAINS_FILTER = 4

    /**
     * mac地址过滤
     */
    val MAC_FILTER = 3
}