# 生命周期
- onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroy()

# 属性
- 屏幕旋转不会重走各生命周期
```
android:configChanges="keyboardHidden|orientation|screenSize"
```