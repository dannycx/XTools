# 进程间通信IPC

## Messenger简单的进程间消息传递
> Messenger基于AIDL实现
> Messenger采用Handler + Message实现进程间简单的消息传递
> 适合数据量少，数据小情况
> 服务端创建Handler,客户端通过bind绑定服务,获取IBinder,创建Messenger通过send传递消息Message至服务端
> Message消息中可加入客户端replyTo对象绑定客户端Handler与客户端通信

### Service端
```
package com.danny.xtools.ipc

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

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
```

### Client端
```
package com.danny.xtools.ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danny.xtools.R
import com.danny.xtools.databinding.XActivityMessengerBinding

/**
 * Messenger客户端
 *
 * @author danny
 * @since 2020-11-26
 */
class XMessengerClient: AppCompatActivity() {
    companion object {
        private val TAG = XMessengerClient::javaClass.name
    }
    private lateinit var messengerBinding: XActivityMessengerBinding
    private var bind = false
    private var messenger: Messenger? = null

    private val conn = object: ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bind = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messenger = Messenger(service)
            bind = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messengerBinding = DataBindingUtil.setContentView(this, R.layout.x_activity_messenger)

        init()
    }

    private fun init() {
        messengerBinding.messenger.setOnClickListener {
            if (null == messenger) {
                return@setOnClickListener
            }
            val message = Message.obtain(null, 1)
            message.replyTo = Messenger(ClientHandle())
            try {
                messenger?.send(message)
            } catch (e: RemoteException) {
                Log.e(TAG, e.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent()
        intent.action = "com.danny.messenger"
        intent.`package` = "com.danny.xtools" // 隐式调用必须加包名
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (bind) {
            unbindService(conn)
        }
    }

    private inner class ClientHandle: Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                2 -> {
                    Toast.makeText(this@XMessengerClient, "服务端回复了消息", Toast.LENGTH_SHORT).show()
                }
                else -> super.handleMessage(msg)
            }
        }
    }
}
```
