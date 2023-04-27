# ADB命令

> app线程优先级查看
+ adb shell 
+ ps -A | grep 包名 // 根据包名找到Pid
+ top -H -p PID    // 查看线程优先级命令

> adb断开连接设备
+ adb kill-server

> adb连接设备（同一网络下）
+ adb connect 10.100.101.130

> 返回键adb shell
+ input keyevent BACK

> adb安装应用	
+ adb install -r -d -t apk路径

> adb卸载应用
+ adb shell pm uninstall --user 0 tw.com.newline.whiteboard
	
> adb启动应用
+ adb shell am start -n tw.com.newline.whiteboard.android.MainActivity

+ getprop
+ getprop lock.fb

> 卸载后重启
+ remount /system

> 复制文件
+ cp -r /sdcard/MyScript /data/ocr/

> 修改文件访问权限
+ chmod 666 ocr

> 查看是否安装应用adb shell进入
+ pm list package -f | grep com.danny.xx
 
 > 重启
 + reboot
 
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
