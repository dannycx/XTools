# XTools
[常见问题](https://github.com/dannycx/XTools/blob/main/QUESTION.md)

[IPC通信](https://github.com/dannycx/XTools/blob/main/notes/IPC.md)

[Flutter](https://github.com/dannycx/XTools/blob/main/notes/flutter/flutter.md)

[Android四大组件](https://github.com/dannycx/XTools/blob/main/notes/component/component.md)

[通信](https://github.com/dannycx/XTools/blob/main/notes/communication/communication.md)

[动画](https://github.com/dannycx/XTools/blob/main/notes/Animation.md)

[设计模式](https://github.com/dannycx/XTools/blob/main/design/design.md)

[应用框架](https://github.com/dannycx/XTools/blob/main/frame/frame.md)

[issue](https://github.com/dannycx/XTools/blob/main/notes/issue.md)

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

## gradle命令
* gradlew build --refresh dependencies
* gradlew :app:denpendencies
* =file:///d:/gradle-5.4.1.zip

