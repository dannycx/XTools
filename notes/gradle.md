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
1. 非项目目录创建module -- VersionPlugin
2. 在 VersionPlugin 文件夹下的 build.gradle 文件内，添加以下内容
```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        // 因为使用的 Kotlin 需要需要添加 Kotlin 插件
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10"
    }
}

apply plugin: 'kotlin'
apply plugin: 'java-gradle-plugin'
repositories {
    // 需要添加 jcenter 否则会提示找不到 gradlePlugin
    jcenter()
}

gradlePlugin {
    plugins {
        version {
            // 在 app 模块需要通过 id 引用这个插件
            id = 'com.danny.plugin'
            // 实现这个插件的类的路径
            implementationClass = 'com.danny.plugin.VersionPlugin'
        }
    }
}
```
3.在 VersionPlugin/src/main/java/包名/ 目录下新建 VersionPlugin.kt 文件，添加以下内容
```
class VersionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
    }

    companion object {
        val appcompat = "androidx.appcompat:appcompat:1.1.0"
    }
}
```
4. 在 settings.gradle 文件内添加 includeBuild '../VersionPlugin' 重启你的 AS
5. 在 app 模块 build.gradle 文件内首行添加以下内容
```
plugins{
    // 这个 id 就是在 VersionPlugin 文件夹下 build.gradle 文件内定义的 id
    id "com.danny.plugin"
}
```

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
