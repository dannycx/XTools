# ADB命令

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
