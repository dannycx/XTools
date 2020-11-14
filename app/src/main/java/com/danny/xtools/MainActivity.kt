package com.danny.xtools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danny.xtools.bean.TestBean
import com.danny.xtools.util.XClone
import com.danny.xtools.util.XSystemUtil

/**
 * 测试页面
 *
 * @author danny
 * @since 2020-11-08
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testClone();
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
