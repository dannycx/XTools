# XTools
[常见问题](https://github.com/dannycx/XTools/blob/main/QUESTION.md)

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

## git常用命令
> git提交命令
* git pull
* git status
* git add .
* git commit -m ""
* git push
> git合并冲突
- git merge --abort
- git reset --merge
> 其他
* git remote update origin --p

## gradle命令
* gradlew build --refresh dependencies
* gradlew :app:denpendencies
* =file:///d:/gradle-5.4.1.zip

