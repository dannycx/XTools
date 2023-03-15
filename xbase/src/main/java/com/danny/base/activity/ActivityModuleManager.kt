package com.danny.base.activity

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.SparseArrayCompat

/**
 *
 *
 * @author danny
 * @since 2022/8/31
 */
open class ActivityModuleManager: XModuleManager() {
    /**
     * 模块初始化
     */
    fun initModules(saveInstance: Bundle?, activity: Activity?,
        modules: ArrayMap<String, ArrayList<Int>>?) {
        if (activity == null || modules == null) {
            return
        }
        moduleConfig(modules = modules)
        initModules(saveInstance, activity)
    }

    /**
     * 模块初始化
     */
    fun initModules(saveInstance: Bundle?, activity: Activity) {
        for (moduleName: String in getModuleNames().keys) {
            val xModule = XModuleFactory.newModuleInstance(moduleName)
            xModule?.let {
                val moduleContext = XModuleContext()
                moduleContext.setActivity(activity)
                moduleContext.setSaveInstance(saveInstance)
                val viewGroups: SparseArrayCompat<ViewGroup> = SparseArrayCompat()
                val viewIds: ArrayList<Int>? = getModuleNames()[moduleName]
                if (viewIds != null && viewIds.size > 0) {
                    for (i in viewIds.indices) {
                        viewGroups.put(i, activity.findViewById(viewIds[i]))
                    }
                }

                // 保存视图
                moduleContext.setViewGroups(viewGroups)

                // 初始化每个module
                it.init(moduleContext, bundle = saveInstance)

                // 记录module
                allModules.put(moduleName, it)
            }
        }
    }
}