package com.danny.xtools.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danny.xtools.R
import com.danny.xtools.bean.TestBean
import com.danny.xtools.databinding.ActivityMainBinding
import com.danny.xtools.dialog.XTipsFragmentDialog
import com.danny.xtools.util.XClone
import com.danny.xtools.util.XFrameworkUtil
import com.danny.xtools.util.XSystemUtil

/**
 * 测试页面
 *
 * @author danny
 * @since 2020-11-08
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        testClone()
        initListener()
    }

    private fun initListener() {
        mainBinding.tips.setOnClickListener {
            val tipsDialog = XTipsFragmentDialog()
            tipsDialog.show(supportFragmentManager, "Tips")
        }

        XFrameworkUtil.getScreenType(this, object: XFrameworkUtil.ScreenTypeCallback {
            override fun screenType(screenType: Int) {
                Toast.makeText(this@MainActivity, "屏幕类型-$screenType", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun testClone() {
        val b = TestBean()
        b.name = "zhangsan"
        b.age = 13
        b.id = "123"
        val a = XClone.deepClone(b) as TestBean
        a.name = "lisi"
        println("${b.toString()}")
        println("${a.toString()}")
        val c = modify(b)
        println("${b.toString()}")
        println("${c.toString()}")
    }

    private fun modify(b: TestBean): TestBean {
        b.name = "wangwu"
        return b
    }

    override fun onBackPressed() {
        XSystemUtil.getInstance().doubleExit(this)
    }

}
