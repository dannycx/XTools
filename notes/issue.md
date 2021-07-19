# 问题积累

## 1.图片深色模式适配
### 填充模式
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
