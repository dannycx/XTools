# 问题汇总
####. vertor转bitmap
   > 5.0以后vector无法使用BitmapFactory.decodeResource转换，原因：4.4及以下用的是png资源，vector会转png，以上无法转换
```
解决方法如下：
fun vector2Bitmap(id: Int): Bitmap? {
    when {
        Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP -> {
            val vectorDrawable = ContextCompat.getDrawable(MainApplication.get(), id) ?: return null
            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
            vectorDrawable.draw(canvas)
            return bitmap
        }
        else -> {
            return BitmapFactory.decodeResource(MainApplication.get().resources, id)
        }
    }
}
```

####. 安装出现null情况
    > 卸载原app重新安装

####. 禁用多窗口模式
    > 24.+ 清单文件application配置resizeableActivity="false"  
    
    > 24- 禁用横竖屏  
    
    > 多窗口最终停留在onPause()

####. EditView
    > 隐藏键盘showSoftInputOnFocus=false
    
    > 不允许复制longClickable="false"  imeOptions="flagNoExtractUi"
    
    > 一劳永逸禁止复制customSelectionActionModeCallback->return false
    
    > 搜索监听setOnEditActionListener()
    
    > F2键监听onKeyDown
    
    > 回车监听dispatcher,判断不等于抬起键，否则会触发两次
    
    > 焦点丢失->设置setNextFocusId

####.Error:java.lang.RuntimeException: Some file crunching failed, see logs for detail
> 出现这个错误的原因是有哪种情况？
1. 构建Gradle的时候，Gradle会去检查一下是否修改过文件的后缀名；
2. 一般大多数是出现在图片上，.jpg修改成了.png就会出现这个问题；
3. 9patch图片也可能出现这个问题。

第一种解决办法是
     查看上面的日志，我的原因是.9图片的问题,注意.9图片在androidStudio中有严格检查，可能你的这些图片不是.9格式的．解决办法就是把图片后缀改成.png，直接修改图片名称就可以改成png图片了。如果图片过多可能工作量有点大！

第二种解决办法是
    你的主工程配置文件中加入两行代码，意思是禁止gradle检查png的合法性.
```
aaptOptions.cruncherEnabled = false
aaptOptions.useNewCruncher = false

build.gradle
aaptOptions {
        cruncherEnabled = false
		useNewCruncher = false
    }
```


