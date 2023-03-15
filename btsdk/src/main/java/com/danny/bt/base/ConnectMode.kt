package com.danny.bt.base

/**
 *
 *
 * @author danny
 * @since 2022/2/18
 */
enum class ConnectMode(value: Int) {
    /**
     * 穿戴
     */
    WEAR_WATCH(0),

    /**
     * 体脂秤
     */
    SIMPLE(1),

    /**
     * 其他
     */
    OTHER(2);

    private var value: Int = 0

    fun getValue(): Int = value

    fun getScanMode(value: Int) : ConnectMode? {
        if (0 <= value && value < values().size) {
            return values()[value]
        }
        return null
    }
}