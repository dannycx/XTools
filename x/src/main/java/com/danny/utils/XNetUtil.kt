package com.danny.utils

import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*

/**
 *
 *
 * @author danny
 * @since 2022/8/10
 */
object XNetUtil {

    /**
     * 获取局域网ip地址
     */
    fun getLanIp() = InetAddress.getLocalHost().hostAddress

    /**
     * 获取全部 IPV4/IPV6 IP地址
     */
    fun getIpv4OrIpv6() : List<String> {
        val list = LinkedList<String>()
        val enumeration = NetworkInterface.getNetworkInterfaces()
        while (enumeration.hasMoreElements()) {
            val network = enumeration.nextElement() as NetworkInterface
            if (network.isVirtual || !network.isUp) {
                continue
            }
            val addresses = network.inetAddresses as Enumeration
            while (addresses.hasMoreElements()) {
                val address = addresses.nextElement() as InetAddress
                if (address is Inet4Address || address is Inet6Address) {
                    list.add(address.hostAddress)
                }
            }
        }
        return list
    }

    /**
     * 获取全部存放本机IP地址
     */
    fun getAllLocalIp(): List<String> {
        val list = LinkedList<String>()
        val enumeration = NetworkInterface.getNetworkInterfaces()
        while (enumeration.hasMoreElements()) {
            val network = enumeration.nextElement() as NetworkInterface
            val addresses = network.inetAddresses as Enumeration
            while (addresses.hasMoreElements()) {
                val address = addresses.nextElement() as InetAddress
                if (address is Inet4Address || address is Inet6Address) {
                    list.add(address.hostAddress)
                }
            }
        }
        return list
    }
}
