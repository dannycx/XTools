package com.danny.bt.scan

/**
 * 扫描策略
 *
 * @author danny
 * @since 2022/3/9
 */
interface ScanStrategy {
    fun init(process: DeviceDiscoveryProcess, filter: List<ScanFilter>)

    fun scan()

    fun stopScan()
}