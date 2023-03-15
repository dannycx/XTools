package com.danny.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

/**
 * 服务端socket
 *
 * @author danny
 * @since 2022/11/13
 */
object SocketServer {
    private const val PORT = 50001

    private var socket: Socket? = null

    private var serverSocket: ServerSocket? = null

    private lateinit var mCallback: ServerCallback

    private lateinit var outputStream: OutputStream

    private var result = true

    fun startServer(callback: ServerCallback) : Boolean {
        mCallback = callback
        Thread {
            try {
                serverSocket = ServerSocket(PORT)
                while (result) {
                    socket = serverSocket?.accept()
                    ServerThread(socket!!, callback).start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                result = false
            }
        }.start()
        return result
    }

    fun stopServer() {
        socket?.apply {
            shutdownInput()
            shutdownOutput()
            close()
        }
        serverSocket?.close()
    }

    fun sendToClient(msg: String) {
        thread {
            if (socket?.isClosed != false) {
                println("sendToClient: socket is closeed")
                return@thread
            }
            outputStream = socket!!.getOutputStream()
            try {
                outputStream.write(msg.toByteArray())
                outputStream.flush()
                println("sendToClient: success")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    class ServerThread(private val socket: Socket, private val callback: ServerCallback): Thread() {
        override fun run() {
            val inputStream: InputStream?
            try {
                inputStream = socket.getInputStream()
                val buffer = ByteArray(2048)
                var len: Int
                var receiverStr = ""
                if (inputStream.available() == 0) {
                    println("ServerThread: inputStream.available() == 0")
                }
                while (inputStream.read(buffer).also { len = it } != -1) {
                    receiverStr += String(buffer, 0, len, Charsets.UTF_8)
                    if (len < 2048) {
                        callback.onResponse(receiverStr)
                    }
                }
            } catch (e: IOException) {
                callback.onResponse("")
            }
        }
    }

    fun startAppService(context: Context) {
        var service = Intent(context, AppService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(service)
        } else {
            context.startService(service)
        }
    }
}

interface ServerCallback {
    fun onResponse(receiver: String)
}

class AppService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, createNotify())
        }
        SocketServer.startServer(object: ServerCallback {
            override fun onResponse(receiver: String) {

            }
        })
        return START_STICKY
    }

    private fun createNotify(): Notification {
        val build: Notification.Builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val manager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
                    val channel = NotificationChannel("init", "app_service",
                        NotificationManager.IMPORTANCE_LOW)
                    manager?.createNotificationChannel(channel)
                    Notification.Builder(this, "init")
                } else {
                    Notification.Builder(this)
                }
        return build.setContentTitle("")
                .setContentText("")
                .setAutoCancel(true)
                .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        SocketServer.stopServer()
    }
}

class AppActivity() : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SocketServer.startAppService(this)
    }
}

class AppReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            when(intent?.action) {
                "android.intent.action.BOOT_COMPLETED" -> SocketServer.startAppService(it)
            }
        }
    }

}