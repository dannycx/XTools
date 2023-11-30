# Flutter问题记录

## 为什么要将 build 方法放在 State 中，而不是放在StatefulWidget中？
* 状态访问不方便：状态在State中，StatefulWidget中加了state，会导致state公开，状态不好管理，不可控
* 继承StatefulWidget不便：AnimatedWidget中build有state参数，StatefulWidget中build许额外定义_state，导致不便

## 无法找到Android SDK(已安装，无法找到，配置绝对路径)
* flutter config --android-sdk "C:\xxx\android\sdk"

## flutter Error: Unable to find git in your PATH
* 将flutter sdk目录加入git安全目录：git config --global --add safe.directory C:/xxx/flutter

## A problem was found with the configuration of task':app:processDebugResources' (type 'LinkApplicationAndroidResourcesTask').
* 构建版本和sdk版本不一致，flutter doctor -v查看Android sdk version，修改项目build.gradle文件的compileSdkVersion与其一致

