# 问题汇总

1. 安装出现null情况
* 卸载原app重新安装
2. 禁用多窗口模式
* 24.+ 清单文件application配置resizeableActivity="false"
* 24- 禁用横竖屏
* 多窗口最终停留在onPause()
3.EditView
- 隐藏键盘showSoftInputOnFocus=false
- 不允许复制longClickable="false"  imeOptions="flagNoExtractUi"
- 一劳永逸禁止复制customSelectionActionModeCallback->return false
- 搜索监听setOnEditActionListener()
- F2键监听onKeyDown
- 回车监听dispatcher,判断不等于抬起键，否则会触发两次
- 焦点丢失->设置setNextFocusId


