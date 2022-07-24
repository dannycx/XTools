# 多渠道打包
* 编译优先级 BuildType > productFlavor > Main > dependencies

## 示例
```
productFlavors {
    // beta
    beta {
        if (!project.ext.isLib) {
            applicationId project.ext.applicationId + '.beta'
        }
        manifestPlaceholders = [
            channel:"1000",
            verNum:"1",
            appName:"Danny_Beta"
        ]
        buildConfigField "String", "url", "\"https://github.com/dannycx\""
    }
    // release
    release {
        manifestPlaceholders = [
            channel:"1001",
            verNum:"1",
            appName:"Danny_Release"
        ]
        buildConfigField "String", "url", "\"https://github.com/dannycx/release\""
    }
}
```

```
<application
    android:name=".XApplication"
    android:label="${appName}">

    <meta-data android:name="verNum" android:value="${verNum}" />

    <meta-data android:name="channel" android:value="${channel}" />
</application>
```

```
public static Object getMetaData(Context context, String metaDataName) {
    Object o = null;
    if (context = null) {
        return o;
    }
    try {
        String packageName = context.getPackageName();
        ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        o = appInfo.metaData.get(metaDataName);
    } catch (Exception e) {

    } finally {
        return o;
    }
}
```
