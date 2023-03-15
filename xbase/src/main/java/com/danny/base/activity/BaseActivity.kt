package com.danny.base.activity

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import java.util.ArrayList

/**
 *
 *
 * @author danny
 * @since 2022/9/2
 */
abstract class BaseActivity: AppCompatActivity() {
    private var manager: ActivityModuleManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            if (manager == null) {
                val time = System.currentTimeMillis()
                manager = ActivityModuleManager()
                manager?.initModules(savedInstanceState, this, moduleConfig())

            }
        }
    }

    abstract fun moduleConfig(): ArrayMap<String, ArrayList<Int>>?

    override fun onResume() {
        super.onResume()
        manager?.onResume()
    }

    override fun onStop() {
        super.onStop()
        manager?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        manager?.onDestroy()
    }
}