# XTools

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
> + adb root              

> 装载
> + adb remount

> shell命令
> + adb shell

> 目录
> + ls

> 系统应用
> + cd system/app/

> 上层目录
> + cd ..

> 系统内置应用
> + cd priv-app/

> apk目录
> + adb shell pm path package

> 进入apk目录
> + cd path

> 删除apk
> + rm xx.apk

> 重启
> + adb reboot

## git常用命令

