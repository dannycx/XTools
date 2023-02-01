# Gradle

## gradle指令

### 6.0以下版本依赖管理
* kotlin+buildSrc
1. 项目根目录下新建文件夹buildSrc
2. 在 buildSrc 文件夹里创建名为 build.gradle.kts 的文件，并添加以下内容
```
plugins {
    'kotlin-dsl'
}
repositories{
    jcenter()
}
```
3. 在 buildSrc/src/main/java/包名/ 目录下新建 Version.kt 文件，添加以下内容
```
object Versions {
    val appcompat = "1.1.0"
}

object Deps {
    val appcompat =  "androidx.appcompat:appcompat:${Versions.appcompat}"
}
```
4. 重启AS，多出buildSrc module，引用其中版本信息，统一管理

### 6.0以上版本依赖管理
* Composing Builds

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

### 提示命名机制（xml资源避免合并冲突）
```
android {
    resourcePrefix "组件名_"
}
```
