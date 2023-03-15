package com.danny.bt.scan

import com.danny.bt.util.BtFWKUtil
import java.util.*
import java.util.concurrent.Semaphore

/**
 *
 *
 * @author danny
 * @since 2022/3/4
 */
class ScanManage {
    /**
     * 资源量
     */
    private val semaphoreCount = 1

    /**
     * 信号量
     */
    private val semaphore: Semaphore = Semaphore(semaphoreCount)

    /**
     * 定时器
     */
    private var timer: Timer? = null

    /**
     * 与底层交互工具类
     */
    private var btFWKUtil: BtFWKUtil? = null

    inner class ScanRunnable(var filter: List<ScanFilter>?, var strategy: ScanStrategy?)
        : Runnable {

        override fun run() {
            try {
                semaphore.acquire()
            } catch (e: InterruptedException) {

            }
        }
    }

    fun scan(type: ScanType, filter: List<ScanFilter>, callback: ScanCallback) {
        if (btFWKUtil?.isBtEnable() != true) {
            return
        }

    }
}
