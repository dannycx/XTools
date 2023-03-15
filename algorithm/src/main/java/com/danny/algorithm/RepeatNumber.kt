package com.danny.algorithm

/**
 *
 * @author danny
 * @since 2022/4/11
 */

fun main() {
//    println(repeatNumber(intArrayOf(0, 2, 4, 4, 7 , 6, 5, 3)))
//    println(repeatNumberTwo(intArrayOf(1, 2, 5, 4, 3 , 6, 5, 3)))
//    println(Math.pow(2.0, 3.0))

    Test.x3()
}

/**
 */
fun repeatNumber(array: IntArray?) : Int {
    when(val length = array?.size ?: 0) {
        0 -> return -1
        else -> {
            for (index in 0 until length) {
                if (array!![index] < 0 || array[index] > length - 1) {
                    return -1
                }
            }
            for (index in 0 until length) {
                while (array!![index] != index) {
                    if (array[index] == array[array[index]]) {
                        return array[index]
                    }

                    // 异或
                    val temp = array[index]
                    array[index] = array[index] xor array[temp]
                    array[temp] = array[index] xor array[temp]
                    array[index] = array[index] xor array[temp]
                }
            }
            return -1
        }
    }
}


/**
 * 长度为n+1数组，数字范围1 ~ n，求重复数字
 */
fun repeatNumberTwo(array: IntArray?) : Int {
    when(val length = array?.size ?: 0) {
        0 -> return -1
        else -> {
            var start = 1
            var end = length - 1
            while (start <= end) {
                // 右移
                val middle = ((end - start) shr 1) + start
                val count = getCount(array!!, length, start, middle)

                if (start == end) {
                    if (count > 1) {
                        return start
                    } else {
                        break
                    }
                }

                if (count > middle - start + 1) {
                    end = middle
                } else {
                    start = middle + 1
                }
            }
            return -1
        }
    }
}

fun getCount(array: IntArray, length: Int, start: Int, end: Int): Int {
    var count = 0
    for (index in 0 until length) {
        if (array[index] in start..end) {
            count++
        }
    }
    return count
}
