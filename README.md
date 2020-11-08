# XTools

##键盘监听
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

##退出应用
```kotlin
XSystemUtil.getInstance().doubleExit(this)
```
