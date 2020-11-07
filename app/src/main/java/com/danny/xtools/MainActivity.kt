package com.danny.xtools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danny.xtools.bean.TestBean
import com.danny.xtools.util.XClone

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
}