# 知识积累

### 运行报错：java.lang.NoClassDefFoundError: Failed resolution of: Lorg/apache/http/impl/client/DefaultHttpClient
* AndroidManifest.xml修改如下
> 注意：Android 10+ 需 android:requestLegacyExternalStorage="true" 才能访问存储 R/W
> Android 6.0 在 AndroidManifest.xml 的 application 元素下引入了 useCleartextTraffic。 Android P 中的默认值为“false”。 将此设置为 true 表示该应用打算使用清晰的网络流量
> 如您的应用在 Android 10 设备上运行时选择退出分区存储，建议在AndroidManifest.xml中将 requestLegacyExternalStorage 设置为 true。 这样，应用在 Android10 设备上正常运行
```
<application
        ...
        ...
        android:usesCleartextTraffic="true"
        android:requestLegacyExternalStorage="true">

       <uses-library
            android:name="org.apache.http.legacy"
            android:required="false" />

</application>
```

### startActivity 找不到对应activity
* Android13目标activity设置data属性，跳转增加Intent.setData(Uri.parse("scheme://host:port/pathPattern"))设置
```
val intent = Intent()
intent.setClass(pkgName, activityClass)
intent.setData(Uri.parse("scheme://host:port/pathPattern"))
// intent.setDataAndType(Uri, mimeType)
startActivity(intent)
```

### CallOnClick
* 主动调用view.onClick()

### git下载代码报错：SSL certificate problem: self signed certificate
* windows：git config --global http.sslVerify false

### 单复数数字单位换行
* 使用\u00A0拼接，例%d\u00A0day

### 镜像化语言位置翻转（乌尔都）
* 使用\u202A拼接，例\u202A %d-%sday\u202A

### Launcher Activity注意事项
* 启动的activity，intent-filter不可设置Default属性，否则会导致安装后activity被启动问题

### 图片深色模式适配
###### 填充模式
* 内部填充src_in、src_atop
* 方形 src-over
* 外部填充screen、add
* 正常填充multiply
```java
<style name="SrcNightStyle">
    <android:tint="@color/src_night_color"/>
    <android:tintMode="src_in"/>
</style>
```

### 当前activity
* adb -d shell dumpsys activity activities | grep mResumedActivity

### 资源冲突
* AndroidManifest 冲突（tools:replace="android:name"）
* 包冲突（exclude排除，使用统一依赖版本）
* 资源名冲突（模块前缀命名）

### 启动应用
* adb shell am start -n "com.danny.x/com.danny.x.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
