# Bitmap内存管理

* 在Android 8.0之后，Bitmap的内存分配从Java堆转移到了Native堆中，所以我们可以通过Android profiler性能检测工具查看内存使用情况
* 内存泄漏：程序申请内存后，无法释放已申请的内存空间，导致内存资源浪费和占用。
* 内存溢出：程序申请内存时，没有足够的内存空间供其使用，或者申请的内存空间小于存储的数据大小，导致程序无法正常运行或报错。
* 内存泄漏可能会导致内存溢出

## 前言
这篇文章中，将讲解以下内容
* 一张Bitmap对内存的占用
* AS如何分析图片占用内存
* 介绍Glide、Picasso加载图片使用内存方式
* 分析不同drawable目录下图片的显示策略
* 基于手机内存、版本，提出一种优化内存分配的方案

### 内存占用
* 图片大小 == 占用内存大小，这种想法是不对的
* 图片占用内存的大小并不取决于它本身的大小，而取决于图片加载框架所采用的加载方式所申请的内存大小
+ 图片举例：
    图片尺寸：350*350
    图片大小：36KB

+ 创建一个简单的Demo，页面正中央是一个ImageView，用于显示这张图片。

* 通过AS进行heap dump，从而看图片所占用的内存。首先我们将显示图片时的内存快照保存下来。操作路径为Profiler -> Memory -> Heap Dump，这会生成一个dump文件，在其中可以看到当前堆的使用情况。
* 通过查看，程序运行时，这张图片占用的内存（Retained Size）是2560000bytes，约等于2.4MB内存。与它在磁盘上36KB的大小，相差了整整70倍！

小技巧：如何查看dump文件中的图片
* 在调试时，如果我们手头只有一个dump文件，往往需要还原图片内容，以帮助定位问题。有两种方式可以从dump文件里提取原图片，这里只介绍AS方式。

方式一：通过AS直接查看
* 如果dump文件来源自Android版本为7.1.1（Android N，API=25）及以下的设备，可以使用这种方法。选中Bitmap对象，直接在窗口的Bitmap Preview中查看图片内容，非常方便。

### 内存占用计算
```
图片占用内存 = 图片质量 * 宽 * 高
```
图片质量、宽、高三因素针对于不同的图片加载框架均有不同的实现，默认取值也是不一样的，下面以当前最流行的Picasso和Glide为例

#### Glide
```
在Glide中图片默认显示的宽高与控件宽高一致。仍以这张图为例，图片尺寸是350*350，当加载到200*200的ImageView中时最终展示的宽高是目标控件的宽高
占用空间只有200*200*4bytes=0.16MB。
```

#### Picasso
```
Picasso采用截然不同的处理方式，默认情况下如果我们把同样一张图片加载到200*200的ImageView中，占用空间是350*350*4bytes=0.49MB
Picasso也可实现类似Glide效果，并提供一系列方法用来调整最终加载出来的图片尺寸，例：fit()
Picasso().get().load(url).fit().into(iv)

因此，在目标ImageView小于图片尺寸的情况下，最好的做法是使用不超过该控件尺寸的图片源，一方面可以缩短图片下载时间，另一方面有助于优化内存占用
```

#### 小图加载到大控件
```
通常为了界面清晰，防止因拉伸失真模糊，UX图都是高分辨率的，大部分场景都是将大图加载到小控件中。但也不排除相反的可能：将小图加载到大控件里。
由于Glide默认采用的内存策略是采用目标控件的尺寸作为最终的宽和高，所以存在明显不足。
例：把350*350的图片加载到500*500的ImageView中时，占用的内存高达500*500*4bytes=1MB。
Glide提供了一种方法centerInside()，可以达到“在原图片和目标ImageView中取最小宽高作为最终加载图片的尺寸”效果
Glide.with(this).load(url).centerInside().into(iv)
```

#### 图片质量
```
简单说就是一个像素点的颜色用多少字节来表示，它的学名叫做“位深度”，在图片属性当中可以看到。
图片位深度通常有1位、8位、16位、24位、32位。
PNG格式：8位、24位、32位三种形式
    8位PNG支持两种不同的透明形式（索引透明和alpha透明）
    24位PNG不支持透明
    32位PNG在24位基础上增加了8位透明通道，因此可展现256级透明程度。
    
Glide和Picasso默认采用的图片质量都是ARGB_8888、也就是带透明度的32位深度，一个像素点需要占用4bytes的内存，这也解释了为什么上文中的计算都是采用宽*高*4bytes的公式。

对客户端使用的大部分图片来说，32位深度、16位深度的显示质量是肉眼较难分辨的，但它们在占用内存上相差了整整一倍。因此，笔者建议在大部分场景下，使用RGB_565作为加载图片的模式。以下两种场景除外：
1）含透明部分的图片：如果采用RGB_565图片格式来显示图片，是无法正常展现透明区域的。比如上方这个钢铁侠图片，原本透明的部分会被显示为黑色。
2）含渐变色并且对显示质量要求高的图片：32位比16位可以支持更多的颜色，在渐变的显示上呈现更加自然的过渡（如下图）。这时我们应当在显示质量和应用性能之间作取舍。对于低端设备，应用的稳定性比显示质量更加重要，强烈建议采用16位深度来显示。
```

### drawable目录下图片的加载方式








