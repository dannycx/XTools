package com.danny.base.activity

import android.content.res.Configuration
import androidx.collection.ArrayMap

/**
 * Module与Activity的管理类，生命周期
 *
 * @author danny
 * @since 2022/8/31
 */
open class XModuleManager {
    // 模块名称
    private var modules: ArrayMap<String, ArrayList<Int>> = ArrayMap()

    // 模块实体
    val allModules: ArrayMap<String, XAbsModule> = ArrayMap()

    fun getModuleNames(): ArrayMap<String, ArrayList<Int>> = modules

    /**
     * 模块配置信息
     */
    fun moduleConfig(modules: ArrayMap<String, ArrayList<Int>>) {
        this.modules = modules
    }

    fun getModuleByNames(name: String): XAbsModule? {
        if (allModules.size > 0) {
            allModules[name]
        }
        return null
    }

    fun onResume() {
        for (module: XAbsModule? in allModules.values) {
            module?.onResume()
        }
    }

    fun onPause() {
        for (module: XAbsModule? in allModules.values) {
            module?.onPause()
        }
    }

    fun onStop() {
        for (module: XAbsModule? in allModules.values) {
            module?.onStop()
        }
    }

    /**
     * 配置变更周期
     */
    fun onConfigurationChanged(newConFig: Configuration) {
        for (module: XAbsModule? in allModules.values) {
            module?.onConfigurationChanged(
                    newConFig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        }
    }

    fun onDestroy() {
        for (module: XAbsModule? in allModules.values) {
            module?.onDestroy()
        }
    }
}
