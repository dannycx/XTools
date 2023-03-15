package com.danny.bt.util

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import com.danny.bt.callback.IDeviceStateCallback
import java.lang.IllegalArgumentException

/**
 * 与fwk交互
 *
 * @author danny
 * @since 2022/2/17
 */
class BtFWKUtil() {
    val any: Any = Any()

    private var context: Context? = null

    /**
     * 蓝牙设备句柄
     */
    var adapter: BluetoothAdapter? = null

    private val thread = HandlerThread("BtFWKUtil")

    private var handler: MessageHandler? = null

    private val deviceStateCallbackMap = HashMap<String, IDeviceStateCallback>()

    private var deviceStateCallback : IDeviceStateCallback? = null

    fun init() {
        context = null
        adapter = BluetoothAdapter.getDefaultAdapter()
        thread.start()
        handler = MessageHandler(thread.looper)
        registerDeviceChanged()
    }

    class MessageHandler(looper: Looper): Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what) {
                1 -> {
                    when(msg.obj) {
                        is BluetoothDevice -> handleBindNoneMsg(msg.obj as BluetoothDevice)
                    }
                }
            }
        }

        private fun handleBindNoneMsg(device : BluetoothDevice) {
            if (!BtConnectUtils.isValidDevice(device)) {
                return
            }
            val state = device.bondState
        }
    }

    private val deviceChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context == null || intent == null) {
                return
            }
            val action = intent.action
            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED != action) {
                return
            }
            val device : BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            device?.let {
                val identify = it.address ?: ""
                if(identify.isEmpty()) {
                    return
                }
                deviceStateCallback = deviceStateCallbackMap[identify]
                when(it.bondState) {
                    BluetoothDevice.BOND_BONDING -> {
                        deviceStateCallback?.pairDeviceDoing()
                        println("bond state doing")
                    }
                    BluetoothDevice.BOND_BONDED -> {
                        deviceStateCallback?.pairDeviceComplete()
                        println("bond state doing")
                    }
                    else -> println("bond state error")
                }
            }
        }
    }

    private fun registerDeviceChanged() {
        context?.let {
            // 避免重复注册报错
            try {
                val filter = IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
                it.registerReceiver(deviceChangedReceiver, filter)
            } catch (e1 : SecurityException) {
                println(e1.toString())
            } catch (e2 : IllegalArgumentException) {
                println(e2.toString())
            }
        }
    }

    fun isBtEnable() : Boolean {
        return adapter?.isEnabled ?: false || adapter?.state == BluetoothAdapter.STATE_ON
    }
}