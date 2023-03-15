package com.danny.base.activity

import android.text.TextUtils

/**
 *
 *
 * @author danny
 * @since 2022/9/1
 */
object XModuleFactory {
    fun newModuleInstance(moduleName: String?): XAbsModule? {
        if (TextUtils.isEmpty(moduleName)) {
            return null
        }
        try {
            val moduleClazz = Class.forName(moduleName!!) as Class<out XAbsModule?>
            return moduleClazz.newInstance()
        } catch (e: ClassNotFoundException) {

        } catch (e: InstantiationException) {

        } catch (e: IllegalAccessException) {

        }
        return null
    }
}