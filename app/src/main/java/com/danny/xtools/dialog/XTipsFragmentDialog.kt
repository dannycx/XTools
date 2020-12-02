package com.danny.xtools.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.DialogFragment
import com.danny.xtools.R
import com.danny.xtools.util.XUiUtil

class XTipsFragmentDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_tips, container, false)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setWindowAnimations(R.style.TipsDialogStyle)

        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)

        val lp = window!!.attributes
        lp.gravity = Gravity.CENTER
        lp.width = XUiUtil.getDisplayWidth(activity!!) - XUiUtil.getDimensionPixelSize(activity!!, R.dimen.x_dp_30)
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
    }
}