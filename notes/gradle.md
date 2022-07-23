# Gradle

## gradle指令

### 查看依赖树
* gradlew module_name : dependencies

### 依赖树输出至文件dependencies.txt
* gradlew dependencies > dependencies.txt
* gradlew project:dependencies >> dependencies.txt

### 排除某仓依赖库（某仓使用高版本依赖库，常用于解决使用低版本依赖库）
```
compile('xxx.xxx.xxx:xxx:1.1.1') {
    exclude group:'com.android.support', module:'support-v4'
}
```