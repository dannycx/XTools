package com.danny.utils

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException
import kotlin.experimental.and

/**
 *
 *
 * @author danny
 * @since 2022/9/7
 */
class XByteUtil {

    fun getByteLength(byte: ByteArray): Int {
        val sb = StringBuilder()
        for (i in byte.indices) {
//            sb.append(byte[i].and(0xFF))
        }
        return sb.length
    }

    fun calculateDataSize(list: List<Any>): ByteArray? {
        var dos: DataOutputStream? = null
        var baos: ByteArrayOutputStream? = null
        try {
            baos = ByteArrayOutputStream()
            dos = DataOutputStream(baos)
            for (i in list.indices) {
                dos.writeUTF(list[i].toString())
            }
            return baos.toByteArray()
        } catch (e: IOException) {
            XIoUtil.close(dos)
            XIoUtil.close(baos)
        }
        return null
    }
}