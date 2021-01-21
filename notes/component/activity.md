## 生命周期
- onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroy()

## 属性
- 屏幕旋转不会重走各生命周期
```
android:configChanges="keyboardHidden|orientation|screenSize"
```
- 任务栈名称
```
android:taskAffinity=""
若application不配置该属性，默认为包名
需配合singleTask或singleInstance使用才可以在新任务栈创建

应用A-ActivityA与应用B-ActivityB有相同的taskAffinity，并且应用A-ActivityA配置android:allowTaskReparenting="true"
若此时打开应用A-ActivityA，退出打开应用B-ActivityB，则当前显示页面为应用A-ActivityA，按返回则为应用B-ActivityB页面
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

## Intent
