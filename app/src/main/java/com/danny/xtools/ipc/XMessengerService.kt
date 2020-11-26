package com.danny.xtools.ipc

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

/**
 * messenger服务端
 *
 * @author danny
 * @since 2020-11-26
 */
class XMessengerService: Service() {

    private inner class MessengerHandle: Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                1 -> {
                    Toast.makeText(applicationContext, "客户端发送了消息", Toast.LENGTH_SHORT).show()
                    val clientMessenger = msg.replyTo
                    val message = Message.obtain(null, 2)
                    try {
                        clientMessenger.send(message)
                    } catch (e: RemoteException) {

                    }
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    private val messenger = Messenger(MessengerHandle())

    override fun onBind(intent: Intent?): IBinder? {
        return messenger.binder
    }
}
