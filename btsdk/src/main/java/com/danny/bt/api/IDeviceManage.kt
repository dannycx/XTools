package com.danny.bt.api

import com.danny.bt.scan.ScanCallback
import com.danny.bt.scan.ScanFilter
import com.danny.bt.scan.ScanType

/**
 *
 *
 * @author danny
 * @since 2022/2/17
 */
interface IDeviceManage {
    /**
     * 初始化设备通用模块
     */
    fun initUniteService()

    /**
     * 扫描设备
     */
    fun scanDevice(type: ScanType, filter: List<ScanFilter>, callback: ScanCallback)

    /**
     * 绑定设备
     */
    fun bindDevice()

    /**
     * 系统设备连接
     */
    fun connectDevice()

    /**
     * 解绑设备
     */
    fun unbindDevice()

    /**
     * 销毁
     */
    fun destroy()
}