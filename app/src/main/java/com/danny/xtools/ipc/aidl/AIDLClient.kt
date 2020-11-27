package com.danny.xtools.ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danny.xtools.R
import com.danny.xtools.databinding.XActivityAidlBinding

/**
 * AIDL客户端
 *
 * @author danny
 * @since 2020-11-27
 */
class AIDLClient: AppCompatActivity() {
    companion object {
        private val TAG = AIDLClient::javaClass.name
    }
    private lateinit var aidlBinding: XActivityAidlBinding
    private var lifeManager: LifeManager? = null
    private var bind = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aidlBinding = DataBindingUtil.setContentView(this, R.layout.x_activity_aidl)

        initListener()
    }

    private fun initListener() {
        aidlBinding.insert.setOnClickListener {
            if (!bind) {
                bindLife()
                return@setOnClickListener
            }

            val life = Life()
            life.name = "人生如棋"
            lifeManager?.lifeIn(life)
        }

        aidlBinding.query.setOnClickListener {
            if (!bind) {
                bindLife()
                return@setOnClickListener
            }

            val lifeList = lifeManager?.lifes
            lifeList?.run {
                Toast.makeText(this@AIDLClient, "$size", Toast.LENGTH_SHORT).show()
                for (life in this) {
                    Log.d(TAG, life.name)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (!bind) {
            bindLife()
        }
    }

    private fun bindLife() {
        val intent = Intent()
        intent.action = "com.danny.aidl"
        intent.`package` = "com.danny.xtools"
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (bind) {
            unbindService(conn)
            bind = false
        }
    }

    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bind = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            lifeManager = LifeManager.Stub.asInterface(service)
            bind = true
        }
    }
}