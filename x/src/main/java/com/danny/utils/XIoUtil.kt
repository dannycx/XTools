package com.danny.utils

import android.net.Uri
import android.os.Environment
import java.io.Closeable
import java.io.File
import java.io.IOException
import java.text.DecimalFormat

/**
 *
 *
 * @author danny
 * @since 2021/1/27
 */
object XIoUtil {
    /**
     * 判断SD是否可用
     */
    fun isSdcardExist() =
        Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    /**
     * 创建根目录
     *
     * @param path 目录路径
     */
    fun createDirFile(path: String?) =
        path?.let {
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            true
        } ?: false

    /**
     * 创建文件
     *
     * @param path
     * 文件路径
     * @return 创建的文件
     */
    fun createNewFile(path: String?): File? {
        var file: File? = null
        path?.let {
            file = File(path)
            if (file?.exists() == true) {
                try {
                    file?.createNewFile()
                } catch (e: IOException) {
                    return file
                }
            }
        }
        return file
    }

    /**
     * 删除文件夹
     *
     * @param folderPath
     * 文件夹的路径
     */
    fun delFolder(folderPath: String) {
        delAllFile(folderPath)
        var filePath = folderPath
        filePath = filePath
        val myFilePath = File(filePath)
        myFilePath.delete()
    }

    /**
     * 删除文件
     *
     * @param path
     * 文件的路径
     */
    fun delAllFile(path: String) {
        val file = File(path)
        if (!file.exists()) {
            return
        }
        if (!file.isDirectory) {
            return
        }
        val tempList = file.list()
        var temp: File? = null
        for (i in tempList.indices) {
            temp = if (path.endsWith(File.separator)) {
                File(path + tempList[i])
            } else {
                File(path + File.separator + tempList[i])
            }
            if (temp.isFile) {
                temp.delete()
            }
            if (temp.isDirectory) {
                delAllFile(path + "/" + tempList[i])
                delFolder(path + "/" + tempList[i])
            }
        }
    }

    /**
     * 获取文件的Uri
     *
     * @param path
     * 文件的路径
     * @return
     */
    fun getUriFromFile(path: String?): Uri? =
        path?.let {
            val file = File(path)
            return Uri.fromFile(file)
        }

    /**
     * 换算文件大小
     *
     * @param size
     * @return
     */
    fun formatFileSize(size: Long): String? {
        val df = DecimalFormat("#.00")
        var fileSizeString = "未知大小"
        fileSizeString = if (size < 1024) {
            df.format(size.toDouble()) + "B"
        } else if (size < 1048576) {
            df.format(size.toDouble() / 1024) + "K"
        } else if (size < 1073741824) {
            df.format(size.toDouble() / 1048576) + "M"
        } else {
            df.format(size.toDouble() / 1073741824) + "G"
        }
        return fileSizeString
    }

    fun close(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (e: IOException) {

        }
    }
}
