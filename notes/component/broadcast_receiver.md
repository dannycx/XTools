## 注册
- 静态注册
```
AndroidManifest.xml文件注册
开机自动注册，不需要解绑
<receiver android:name="com.danny.XReceiver"/>
```

- 动态注册
```
代码注册
val filter = IntentFilter("com.danny.x.receiver")
val receiver = XReceiver()
context.registerReceiver(receiver, filter)
需要解绑
context.unregisterReceiver(receiver)
```

## 实现
```
class XReceiver: BroadcastReceiver {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.apply {
            // do something
        }
    }
}
```