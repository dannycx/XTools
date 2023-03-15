package com.danny.bt.scan

/**
 * 扫描类型
 *
 * @author danny
 * @since 2022/2/18
 */
enum class ScanType(value: Int) {
    /**
     * ble
     */
    BLE(1),

    /**
     * br
     */
    BR(2),

    /**
     * ble and br
     */
    BLE_BR(3);

    private var value: Int = 1

    fun getValue(): Int = value

    fun getScanMode(value: Int) : ScanType? {
        if (0 <= value && value < values().size) {
            return values()[value]
        }
        return null
    }
}