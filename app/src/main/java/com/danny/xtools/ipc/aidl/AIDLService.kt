package com.danny.xtools.ipc.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * AIDL服务端
 *
 * @author danny
 * @since 2020-11-27
 */
class AIDLService: Service() {
    private var lifes = ArrayList<Life>(10)
    private val lifeManager = object : LifeManager.Stub() {
        override fun lifeIn(life: Life?) {
            synchronized(AIDLService::class.java) {
                life?.let {
                    // 修改值,看客户端反馈
                    life.name = "脚下的路"
                    lifes.add(life)
                }
            }
        }

        override fun getLifes(): MutableList<Life> {
            synchronized(AIDLService::class.java) {
                return lifes
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        // 添加一条默认数据
        val life = Life()
        life.name = "一切未可知"
        lifes.add(life)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return lifeManager
    }
}