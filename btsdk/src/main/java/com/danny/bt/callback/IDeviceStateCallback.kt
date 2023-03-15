package com.danny.bt.callback

/**
 * 设备链接状态变化回调
 *
 * @author danny
 * @since 2022/2/20
 */
interface IDeviceStateCallback {
    /**
     * 连接中
     */
    fun pairDeviceDoing()

    /**
     * 已连接
     */
    fun pairDeviceComplete()
}
