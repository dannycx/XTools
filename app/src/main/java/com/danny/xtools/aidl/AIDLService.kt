package com.danny.xtools.aidl

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
                    lifes.add(life)
                }
            }
        }

        override fun getLifes(): MutableList<Life> {
            return lifes
        }
    }

    override fun onCreate() {
        super.onCreate()
        val life = Life()
        life.name = "一切未可知"
        lifes.add(life)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return lifeManager
    }
}