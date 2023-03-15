package com.danny.bt.util

import android.bluetooth.BluetoothDevice
import android.text.TextUtils

/**
 *
 *
 * @author danny
 * @since 2022/2/17
 */
object BtConnectUtils {

    /**
     * 是否有效的连接设备
     */
    fun isValidDevice(device: BluetoothDevice?) = when(device) {
        null -> false
        else -> !TextUtils.isEmpty(device.address)
    }
}