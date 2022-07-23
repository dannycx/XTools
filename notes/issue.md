# 知识积累

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