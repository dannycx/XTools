package com.danny.xtools.utils.data

import android.content.Context
import androidx.collection.SimpleArrayMap
import com.danny.xtools.X

class XSP(): XSPModule() {
    companion object {
        private val sam = SimpleArrayMap<String, XSP>()

        fun getInstance() = getInstance("")

        fun getInstance(spName: String): XSP {
            var name = spName
            when(spName.isBlank()) {
                true -> name = "spName"
            }
            var xsp = sam[name]
            if (null == xsp) {
                xsp = XSP(name)
                sam.put(name, xsp)
            }
            return xsp
        }
    }

    constructor(spName: String): this() {
        sp = X.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE)
    }
}
