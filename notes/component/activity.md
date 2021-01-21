## 生命周期
- onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroy()

## 属性
- 屏幕旋转不会重走各生命周期
```
android:configChanges="keyboardHidden|orientation|screenSize"
```

## 启动模式
- standard
```
默认启动模式
```
- singleTop
```
栈顶复用,若启动activity位于栈顶时，不会创建新activity，只会走onNewIntent()
```
- singleTask
```
栈内复用,若启动activity位于栈内，不会创建新activity，复用栈内实例，同时移除位于其顶部activity，只会走onNewIntent()
```
- singleInstance
```
创建新任务栈，并将实例添加其中
```