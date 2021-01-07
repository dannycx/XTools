# XTools
[常见问题](https://github.com/dannycx/XTools/blob/main/QUESTION.md)

[IPC通信](https://github.com/dannycx/XTools/blob/main/notes/IPC.md)

[动画](https://github.com/dannycx/XTools/blob/main/notes/Animation.md)

[Flutter](https://github.com/dannycx/XTools/blob/main/notes/flutter/flutter.md)

## 键盘监听
```kotlin
val context = this
XSoftKeyUtil.startSoftKeyListener(this, object : XSoftKeyChangedCallback {
      override fun softKeyHide(height: Int) {
          Toast.makeText(context, "hide-$height", Toast.LENGTH_SHORT).show()
      }

      override fun softKeyShow(height: Int) {
          Toast.makeText(context, "show-$height", Toast.LENGTH_SHORT).show()
      }
})
```

## 退出应用
```kotlin
XSystemUtil.getInstance().doubleExit(this)
```

## root手机删除系统应用
> 进入root
+ adb root              

> 装载
+ adb remount

> shell命令
+ adb shell

> 查看系统应用目录
+ cd system/app/

> 查看系统内置应用目录
+ cd priv-app/

> apk目录
+ adb shell pm path package

> 进入apk目录
+ cd system/priv-app/XXX/

> 删除apk
+ rm xx.apk

> 重启
+ adb reboot

## gradle命令
* gradlew build --refresh dependencies
* gradlew :app:denpendencies
* =file:///d:/gradle-5.4.1.zip

