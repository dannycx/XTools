# android混淆
* 混淆包括代码压缩、代码混淆、资源压缩等过程

## 基础混淆
* 混淆改变java路径名，那么保持所在路径不被混淆就是至关重要的

### ProGuard混淆
* 压缩、优化、混淆java字节码文件工具
* 删除无用类、字段、方法、属性
* 删除无用注释
* 最大限度优化字节码文件
* 使用简短无意义名称重命名已存在类、字段、方法、属性，增加逆向工程难度
* 有助于规避64K方法数
* Shrinking（压缩）、Optimization（优化）、Obfuscation（混淆）、Preverification（预校验）四步操作

#### 基本配置
```
android {
    buildTypes {
        release {
            minifyEnabled true // 开启混淆
            shrinkResources true // 开启资源压缩
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    ...
}
```
#### 不可混淆
* bean对象
* 四大组件
* 反射使用元素
* 注解
```
-keepattributes *Annotation*
```
* 枚举中value()和valueOf()：静态添加到代码运行，会被反射使用
```
-keepclassesmembers enum * {
    public static **[] values();
    public statis ** valueOf(java.lang.String);
}
```
* JNI调用java方法：类名+方法名构成地址
* Java使用Native方法，Native是C、C++编写，无法一同混淆
```
-keepclasseswithmembernames class * {
    native <methods>;
}
```
* JS调用Java方法
```
-keepattributes *JavascriptInterface*
```
* WebView中JavaScript调用方法
```
-keepclassesmembers class xxx.xxx.xxx(包名.类名) {
    public *;
}
-keepclassesmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassesmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}
```
* 三方库使用自身混淆规则
* 实现序列化Parcelable、Serializable类和Creator静态成员变量
* Gson序列化和反序列化：使用反射

## 资源混淆
### AndResGuard微信
```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.tencent.mm:AndResGuard-gradle-plugin:1.2.3'
    }
}
```

```
apply plugin: 'AndResGuard'
andResGuard {
    mappingFile = null // mappingFile = file("./resource_mapping.txt")
    use7zip = true // v2签名失效
    useSign = true
    keepRoot = false // 打卡会keep所以资源原始路径，只混淆名称
    whiteList = [ // 白名单列表
        "R.drawable.icon",
        "R.string.*"
    ]
    compressFilePattern = [ // 重打包时是否压缩指定文件
        "*.png",
        "*.gif",
        "resources.arsc"
    ]
    sevenzip {// 压缩引用
        artifact = "com.tencent.mm:SevenZip:1.2.3" // path = "/usr/local/bin/7za"
    }
}
```
