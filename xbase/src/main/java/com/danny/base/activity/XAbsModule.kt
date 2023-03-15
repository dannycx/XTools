package com.danny.base.activity

import android.os.Bundle

/**
 *
 *
 * @author danny
 * @since 2022/8/31
 */
abstract class XAbsModule {
    abstract fun init(moduleContext: XModuleContext, bundle: Bundle?)

    abstract fun onSaveInstanceState(outState: Bundle)

    abstract fun onResume()

    abstract fun onPause()

    abstract fun onStop()

    abstract fun onConfigurationChanged(isLandscape: Boolean)

    abstract fun onDestroy()
}