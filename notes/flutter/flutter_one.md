# 环境搭建

## flutter sdk下载
https://mirrors.tuna.tsinghua.edu.cn/flutter/flutter_infra/releases/stable/windows/
## 解压，配置环境变量
path=futter\bin
## 设置代理(环境变量)
### 清华
PUB_HOSTED_URL=https://mirrors.tuna.tsinghua.edu.cn/dart-pub
FLUTTER_STORAGE_BASE_URL=https://mirrors.tuna.tsinghua.edu.cn/flutter
### 上交
FLUTTER_STORAGE_BASE_URL: https://mirrors.sjtug.sjtu.edu.cn/
PUB_HOSTED_URL: https://dart-pub.mirrors.sjtug.sjtu.edu.cn/
### 社区
PUB_HOSTED_URL=https://pub.flutter-io.cn
FLUTTER_STORAGE_BASE_URL=https://storage.flutter-io.cn
## 运行flutter/flutter_console.bat
flutter doctor下载dart-sdk
## Android studio下载插件，版本要对应，否则会不兼容，不允许安装
### as内安装
### https://plugins.jetbrains.com官网下载指定版本

# 创建flutter应用
## as创建flutter项目选择sdk
## 编译问题
### 配置代理
### Pub get失败，删除flutter\bin\cache\lockfile文件