# 环境搭建

## 1. flutter sdk下载
- https://mirrors.tuna.tsinghua.edu.cn/flutter/flutter_infra/releases/stable/windows/
### 解压，配置环境变量
- path=futter\bin
## 2. 设置代理(window环境变量)
### 清华
- PUB_HOSTED_URL=https://mirrors.tuna.tsinghua.edu.cn/dart-pub
- FLUTTER_STORAGE_BASE_URL=https://mirrors.tuna.tsinghua.edu.cn/flutter
### 上交
- FLUTTER_STORAGE_BASE_URL: https://mirrors.sjtug.sjtu.edu.cn/
- PUB_HOSTED_URL: https://dart-pub.mirrors.sjtug.sjtu.edu.cn/
### 社区
- PUB_HOSTED_URL=https://pub.flutter-io.cn
- FLUTTER_STORAGE_BASE_URL=https://storage.flutter-io.cn
### 版本检测
- flutter --version

## 3. 环境监测
- flutter doctor
```
Flutter assets will be downloaded from https://storage.flutter-io.cn. Make sure you trust this source!
Doctor summary (to see all details, run flutter doctor -v):
[√] Flutter (Channel stable, 3.13.8, on Microsoft Windows [版本 10.0.22000.527], locale zh-CN)
[√] Windows Version (Installed version of Windows is version 10 or higher)
[!] Android toolchain - develop for Android devices (Android SDK version 33.0.2)
    X cmdline-tools component is missing
      Run `path/to/sdkmanager --install "cmdline-tools;latest"`
      See https://developer.android.com/studio/command-line for more details.
    X Android license status unknown.
      Run `flutter doctor --android-licenses` to accept the SDK licenses.
      See https://flutter.dev/docs/get-started/install/windows#android-setup for more details.
[√] Chrome - develop for the web
[X] Visual Studio - develop Windows apps
    X Visual Studio not installed; this is necessary to develop Windows apps.
      Download at https://visualstudio.microsoft.com/downloads/.
      Please install the "Desktop development with C++" workload, including all of its default components
[√] Android Studio (version 2022.2)
[√] Connected device (3 available)
[√] Network resources

! Doctor found issues in 2 categories.
```
### Android toolchain - develop for Android devices (Android SDK version xx.x.x)
- 下载SDK Tools. AS -> Settings ->Android SDK -> SDK Tools -> Android SDK Command-line Tools
### X Android license status unknown
- 执行 flutter doctor --android-licenses
### Visual Studio - develop Windows apps（不开发Windows phone app可忽略）

## 4. Android studio下载插件，版本要对应，否则会不兼容，不允许安装
- as内安装
- https://plugins.jetbrains.com 官网下载指定版本

# 5. 创建flutter应用
## as创建flutter项目选择sdk
## 编译问题
- 配置代理
- Pub get失败，删除flutter\bin\cache\lockfile文件



# Other
## 运行flutter/flutter_console.bat
- flutter doctor下载dart-sdk




