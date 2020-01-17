# YwUtils
    此library还在功能迭代中,功能不尽完善...

## lib库说明
* 每一个Android开发者在日常开发中都会积累一些自己的代码片段，这里将尽可能收集到的代码整理成lib库。
* 目的：
   * 1.将常用功能模块做成工具类。
   * 2.封装Android系统api,简化api的使用。
   * 3.收集一些高效的正确的代码片段避免下次踩坑。
   * 4.尽量少依赖第三方。
* 能力一般,水平有限,难免有Bug,如果有任何问题,请<a href="https://github.com/qiuqiu01/common-lib/issues">反馈</a>
* 如果你有更好的代码,请提交<a href="https://github.com/qiuqiu01/common-lib/pulls">Pull request</a>
* 感谢各位的star，你们的支持是我继续的动力，好了，本着多一点真诚，少一点套路的原则，完善一下文档，并尽可能是排版更加友好，方便阅读。


## Android Studio中引入库
* 项目app的build.gradle中dependencies节点下添加如下代码：
	> implementation 'com.github.qiuqiu01:common-lib:1.0.0.2'

## 待解决
	1、Cache01Utils和CacheUtils。
	3、合并File01Utils和FileUtils，以及搞明白和FileIOUtils的关系
	4、合并Image01Utils和ImageUtils。（以及BitmapUtils）
	5、合并NetUtils和NetworkUtils。
	6、合并RegexUtils和VerificationUtils。
	7、合并SP01Utils和SPUtils。
	8、合并String01Utils和StringUtils。
	9、合并YwLog和LogUtils。
	10、补充md说明文档（将未标注的补充上来）


## How to use
>
	大部分可直接引用，例如：
		AppUtils.getAppName(context, packageName);
	少部分代码需要先在Application类中初始化（代码中有说明） 
		// init it in the function of onCreate in Application
    	Utils.init(application);


## APIs

### Activity 相关  -> ActivityUtils.java
>
    isActivityExists               : 判断 Activity 是否存在
    startActivity                  : 启动 Activity
    startActivities                : 启动多个 Activity
    startHomeActivity              : 回到桌面
    getActivityList                : 获取 Activity 栈链表
    getLauncherActivity            : 获取启动项 Activity
    getTopActivity                 : 获取栈顶 Activity
    isActivityExistsInStack        : 判断 Activity 是否存在栈中
    finishActivity                 : 结束 Activity
    finishToActivity               : 结束到指定 Activity
    finishOtherActivities          : 结束所有其他类型的 Activity
    finishAllActivities            : 结束所有 Activity
    finishAllActivitiesExceptNewest: 结束除最新之外的所有 Activity

---

### App 相关 -> AppUtils.java
>
	直接调用：
>>
	getAppName 			: 获取 App 名称
    getAppIcon 			: 获取 App 图标
    getAppDate 			: 获取 App 更新日期
    getAppSize 			: 获取 App 大小
    getAppApk 			: 获取 App apk文件
    getAppVersionName 	: 获取 App 版本名称
    getAppVersionCode 	: 获取 App 版本号
    getAppInstaller 	: 获取 App 的安装市场
    getAppPackageName	: 获取 App 包名
    hasPermission 		: 是否有权限
    isInstalled 		: 应用是否安装
    installApk 			: 安装 App 
    uninstallApk 		: 卸载 App 
    isSystemApp 		: 是否是系统 App 
    isServiceRunning 	: 服务是否在运行
    stopRunningService	: 停止服务
    getNumCores 		: 获取Cpu内核数
    killProcesses 		: 结束进程
    runScript 			: 运行脚本
    getRootPermission 	: 获得root权限
>
	需要先在Application中初始化：YwUtils.init(application)再调用
>>
    isInstallApp         : 判断 App 是否安装
    installApp           : 安装 App（支持 8.0）
    installAppSilent     : 静默安装 App
    uninstallApp         : 卸载 App
    uninstallAppSilent   : 静默卸载 App
    isAppRoot            : 判断 App 是否有 root 权限
    launchApp            : 打开 App
    exitApp              : 关闭应用
    getAppPackageName    : 获取 App 包名
    getAppDetailsSettings: 获取 App 具体设置
    getAppName           : 获取 App 名称
    getAppIcon           : 获取 App 图标
    getAppPath           : 获取 App 路径

---

### Bitmap工具类 -> BitmapUtils.java
	resize 				: 修改Bitmap图片尺寸
	toRoundBitmap 		: 将Bitmap对象转换成圆形Bitmap（切出来的圆形较大）
	toRoundCorner 		: 将Bitmap对象转换成圆形Bitmap（切出来的圆形很小）
	cutBitmap 			: 裁剪图片
	decodeFile 			: 解析文件为bitmap
	getImageThumbnail 	: 获取图片缩略图
	createCircularClip	: 

### 栏相关 -> BarUtils.java
>
    getStatusBarHeight                   : 获取状态栏高度（px）
    setStatusBarVisibility               : 设置状态栏是否可见
    isStatusBarVisible                   : 判断状态栏是否可见
    addMarginTopEqualStatusBarHeight     : 为 view 增加 MarginTop 为状态栏高度
    subtractMarginTopEqualStatusBarHeight: 为 view 减少 MarginTop 为状态栏高度
    setStatusBarColor                    : 设置状态栏颜色
    setStatusBarAlpha                    : 设置状态栏透明度
    setStatusBarColor4Drawer             : 为 DrawerLayout 设置状态栏颜色
    setStatusBarAlpha4Drawer             : 为 DrawerLayout 设置状态栏透明度
    getActionBarHeight                   : 获取 ActionBar 高度
    setNotificationBarVisibility         : 设置通知栏是否可见
    getNavBarHeight                      : 获取导航栏高度
    setNavBarVisibility                  : 设置导航栏是否可见
    setNavBarImmersive                   : 设置导航栏沉浸式
    isNavBarVisible                      : 判断导航栏是否可见

---

### 缓存相关 -> Cache01Utils.java
>
    getInstance    : 获取缓存实例
    put            : 缓存中写入数据
    getBytes       : 缓存中读取字节数组
    getString      : 缓存中读取 String
    getJSONObject  : 缓存中读取 JSONObject
    getJSONArray   : 缓存中读取 JSONArray
    getBitmap      : 缓存中读取 Bitmap
    getDrawable    : 缓存中读取 Drawable
    getParcelable  : 缓存中读取 Parcelable
    getSerializable: 缓存中读取 Serializable
    getCacheSize   : 获取缓存大小
    getCacheCount  : 获取缓存个数
    remove         : 根据键值移除缓存
    clear          : 清除所有缓存

---

### 清除相关 -> CleanUtils.java
>
    cleanInternalCache   : 清除内部缓存
    cleanInternalFiles   : 清除内部文件
    cleanInternalDbs     : 清除内部数据库
    cleanInternalDbByName: 根据名称清除数据库
    cleanInternalSP      : 清除内部 SP
    cleanExternalCache   : 清除外部缓存
    cleanCustomCache     : 清除自定义目录下的文件

---

### 关闭相关 -> CloseUtils.java
>
    closeIO       : 关闭 IO
    closeIOQuietly: 安静关闭 IO

---

### 转换相关 -> ConvertUtils.java
>
    bytes2HexString, hexString2Bytes        : byteArr 与 hexString 互转
    chars2Bytes, bytes2Chars                : charArr 与 byteArr 互转
    memorySize2Byte, byte2MemorySize        : 以 unit 为单位的内存大小与字节数互转
    byte2FitMemorySize                      : 字节数转合适内存大小
    timeSpan2Millis, millis2TimeSpan        : 以 unit 为单位的时间长度与毫秒时间戳互转
    millis2FitTimeSpan                      : 毫秒时间戳转合适时间长度
    bytes2Bits, bits2Bytes                  : bytes 与 bits 互转
    input2OutputStream, output2InputStream  : inputStream 与 outputStream 互转
    inputStream2Bytes, bytes2InputStream    : inputStream 与 byteArr 互转
    outputStream2Bytes, bytes2OutputStream  : outputStream 与 byteArr 互转
    inputStream2String, string2InputStream  : inputStream 与 string 按编码互转
    outputStream2String, string2OutputStream: outputStream 与 string 按编码互转
    bitmap2Bytes, bytes2Bitmap              : bitmap 与 byteArr 互转
    drawable2Bitmap, bitmap2Drawable        : drawable 与 bitmap 互转
    drawable2Bytes, bytes2Drawable          : drawable 与 byteArr 互转
    view2Bitmap                             : view 转 Bitmap

---

### GPS坐标转换工具 -> CoordinateTransformUtils.java
>
	百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、和WGS84坐标系之间的转换的工具
	bd09towgs84 	: 百度坐标系(BD-09)转WGS坐标(百度坐标纬度,百度坐标经度),WGS84坐标数组
    wgs84tobd09		: WGS坐标转百度坐标系(BD-09)(WGS84坐标系的经度,WGS84坐标系的纬度),百度坐标数组
    gcj02tobd09		: 火星坐标系(GCJ-02)转百度坐标系(BD-09)(火星坐标经度,火星坐标纬度),百度坐标数组
    bd09togcj02		: 百度坐标系(BD-09)转火星坐标系(GCJ-02)(百度坐标纬度,百度坐标经度),火星坐标数组
    wgs84togcj02	: WGS84转GCJ02(火星坐标系)(WGS84坐标系的经度,WGS84坐标系的纬度),火星坐标数组
    gcj02towgs84	: GCJ02(火星坐标系)转GPS84(火星坐标系的经度,火星坐标系纬度),WGS84坐标数组
    transformlat	: 纬度转换
    transformlng	: 经度转换
    out_of_china	: 判断是否在国内，不在国内不做偏移	

---

### 崩溃相关 -> CrashUtils.java
>
    init: 初始化

---

### 日期工具类 -> DateUtils.java
>
    formatDataTime 		: 格式化日期时间
    formatDate 			: 格式化日期
    formatTime 			: 格式化时间
    formatDateCustom 	: 自定义格式的格式化日期时间
    string2Date 		: 将时间字符串转换成Date
    getTime 			: 获取当前系统时间，格式：12:36:41
    subtractDate 		: 计算两个时间差
    getDateAfter 		: 得到几天后的时间
	getYear 			: 获取年份
	getMonth 			: 获取月份
	getDayOfMonth 		: 获取自然月的日
	getDayOfYear 		: 获取自然年的日
    getWeekOfMonth 		: 获取当前时间为本月的第几周
    getDayOfWeek 		: 获取当前时间为本周的第几天

---

### 屏幕工具类 -> DensityUtils.java
>
    dip2px 				: dp转像素
    px2dip 				: 像素转dp
    px2sp 				: 像素转sp 
    sp2px 				: sp转像素
    getScreenW 			: 获取屏幕宽度
    getScreenH 			: 获取屏幕高度
    getScreenRealSize 	: 获取屏幕的真实高度
    getStatusBarH 		: 获取状态栏高度
    getNavigationBarrH 	: 获取导航栏高度

---

### 设备相关 -> DeviceUtils.java
>
	直接调用：
>>
	getAndroidID				: 获取AndroidID
    getIMEI 					: 获取设备IMEI码
    getIMSI 					: 获取设备IMSI码
    getWifiMacAddr 				: 获取MAC地址
    getIP 						: 获取网络IP地址(优先获取wifi地址)
    getWifiIP 					: 获取WIFI连接下的ip地址
    getGPRSIP 					: 获取GPRS连接下的ip地址
    getSerial 					: 获取设备序列号
    getSIMSerial 				: 获取SIM序列号
    getPhoneNumber 				: 获取手机号码(未获取成功)
    getMNC 						: 获取网络运营商 46000,46002,46007 中国移动,46001 中国联通,46003 中国电信
    getCarrier 					: 获取网络运营商：中国电信,中国移动,中国联通
    getModel 					: 获取硬件型号
    getBuildBrand 				: 获取编译厂商
    getBuildHost 				: 获取编译服务器主机
    getBuildTags 				: 获取描述Build的标签
    getBuildTime 				: 获取系统编译时间
    getBuildUser 				: 获取系统编译作者
    getBuildVersionRelease 		: 获取编译系统版本(5.1)
    getBuildVersionCodename 	: 获取开发代号
    getBuildVersionIncremental	: 获取源码控制版本号  
    getBuildVersionSDK 			: 获取编译的SDK
    getBuildID 					: 获取修订版本列表(LMY47D)
    getSupportedABIS 			: CPU指令集
    getManufacturer 			: 获取硬件制造厂商
    getBootloader 				: 获取系统启动程序版本号
    getScreenDisplayID			:  
    getDisplayVersion 			: 获取系统版本号
    getLanguage 				: 获取语言
    getCountry 					: 获取国家
    getOSVersion 				: 获取系统版本:5.1.1
    getGSFID 					: 获取GSF序列号
    getBluetoothMAC 			: 获取蓝牙地址
    getPsuedoUniqueID 			: Android设备物理唯一标识符
    getFingerprint 				: 构建标识,包括brand,name,device,
		version.release,id,version.incremental,type,tags这些信息
    getHardware 				: 获取硬件信息
    getProduct 					: 获取产品信息
    getDevice 					: 获取设备信息
    getBoard 					: 获取主板信息
    getRadioVersion 			: 获取基带版本(无线电固件版本 Api14以上)
    getUA 						: 获取的浏览器指纹(User-Agent)
    getDensity 					: 获取得屏幕密度
    getGoogleAccounts 			: 获取google账号
>
	需要先在Application中初始化：YwUtils.init(application)再调用
>>
    isDeviceRooted   : 判断设备是否 rooted
    shutdown         : 关机
    reboot           : 重启
    reboot2Recovery  : 重启到 recovery
    reboot2Bootloader: 重启到 bootloader
    getAndroidID     : 获取设备 AndroidID
    getIMEI     	 : 获取设备 IMEI
    getIMSI     	 : 获取设备 IMSI
    getWifiMacAddr   : 获取设备连接Wifi时的 MAC 地址
    getIP     		 : 获取设备的ip地址
    intToIp     	 : 将int数据转化成ip地址
    getSerial     	 : 获取硬件序列号
	getSIMSerial     : 获取SIM卡序列号
    getSDKVersion    : 获取设备系统版本号
    getMacAddress    : 获取设备 MAC 地址
    getManufacturer  : 获取设备厂商
    getModel         : 获取设备型号

---

### 判空相关 -> EmptyUtils.java
>
    isEmpty   : 判断对象是否为空
    isNotEmpty: 判断对象是否非空

---

### 编码解码相关 -> EncodeUtils.java
>
    urlEncode          : URL 编码
    urlDecode          : URL 解码
    base64Encode       : Base64 编码
    base64Encode2String: Base64 编码
    base64Decode       : Base64 解码
    base64UrlSafeEncode: Base64URL 安全编码
    htmlEncode         : Html 编码
    htmlDecode         : Html 解码

---

### 加密解密相关 -> EncryptUtils.java
>
    encryptMD2, encryptMD2ToString                        : MD2 加密
    encryptMD5, encryptMD5ToString                        : MD5 加密
    encryptMD5File, encryptMD5File2String                 : MD5 加密文件
    encryptSHA1, encryptSHA1ToString                      : SHA1 加密
    encryptSHA224, encryptSHA224ToString                  : SHA224 加密
    encryptSHA256, encryptSHA256ToString                  : SHA256 加密
    encryptSHA384, encryptSHA384ToString                  : SHA384 加密
    encryptSHA512, encryptSHA512ToString                  : SHA512 加密
    encryptHmacMD5, encryptHmacMD5ToString                : HmacMD5 加密
    encryptHmacSHA1, encryptHmacSHA1ToString              : HmacSHA1 加密
    encryptHmacSHA224, encryptHmacSHA224ToString          : HmacSHA224 加密
    encryptHmacSHA256, encryptHmacSHA256ToString          : HmacSHA256 加密
    encryptHmacSHA384, encryptHmacSHA384ToString          : HmacSHA384 加密
    encryptHmacSHA512, encryptHmacSHA512ToString          : HmacSHA512 加密
    encryptDES, encryptDES2HexString, encryptDES2Base64   : DES 加密
    decryptDES, decryptHexStringDES, decryptBase64DES     : DES 解密
    encrypt3DES, encrypt3DES2HexString, encrypt3DES2Base64: 3DES 加密
    decrypt3DES, decryptHexString3DES, decryptBase64_3DES : 3DES 解密
    encryptAES, encryptAES2HexString, encryptAES2Base64   : AES 加密
    decryptAES, decryptHexStringAES, decryptBase64AES     : AES 解密

---

### 文件相关 -> FileIOUtils.java
>
    writeFileFromIS            : 将输入流写入文件
    writeFileFromBytesByStream : 将字节数组写入文件
    writeFileFromBytesByChannel: 将字节数组写入文件
    writeFileFromBytesByMap    : 将字节数组写入文件
    writeFileFromString        : 将字符串写入文件
    readFile2List              : 读取文件到字符串链表中
    readFile2String            : 读取文件到字符串中
    readFile2BytesByStream     : 读取文件到字节数组中
    readFile2BytesByChannel    : 读取文件到字节数组中
    readFile2BytesByMap        : 读取文件到字节数组中
    setBufferSize              : 设置缓冲区尺寸

---

### 文件相关 -> File01Utils.java
>
    getFileByPath             : 根据文件路径获取文件
    isFileExists              : 判断文件是否存在
    rename                    : 重命名文件
    isDir                     : 判断是否是目录
    isFile                    : 判断是否是文件
    createOrExistsDir         : 判断目录是否存在，不存在则判断是否创建成功
    createOrExistsFile        : 判断文件是否存在，不存在则判断是否创建成功
    createFileByDeleteOldFile : 判断文件是否存在，存在则在创建之前删除
    copyDir                   : 复制目录
    copyFile                  : 复制文件
    moveDir                   : 移动目录
    moveFile                  : 移动文件
    deleteDir                 : 删除目录
    deleteFile                : 删除文件
    deleteAllInDir            : 删除目录下所有东西
    deleteFilesInDir          : 删除目录下所有文件
    deleteFilesInDirWithFilter: 删除目录下所有过滤的文件
    listFilesInDir            : 获取目录下所有文件
    listFilesInDirWithFilter  : 获取目录下所有过滤的文件
    getFileLastModified       : 获取文件最后修改的毫秒时间戳
    getFileCharsetSimple      : 简单获取文件编码格式
    getFileLines              : 获取文件行数
    getDirSize                : 获取目录大小
    getFileSize               : 获取文件大小
    getDirLength              : 获取目录长度
    getFileLength             : 获取文件长度
    getFileMD5                : 获取文件的 MD5 校验码
    getFileMD5ToString        : 获取文件的 MD5 校验码
    getDirName                : 根据全路径获取最长目录
    getFileName               : 根据全路径获取文件名
    getFileNameNoExtension    : 根据全路径获取文件名不带拓展名
    getFileExtension          : 根据全路径获取文件拓展名

---

### 文件相关 -> File01Utils.java
>
	closeIO 		: 关闭IO流
    isFileExist 	: 文件是否存在
    writeFile 		: 将字符串写入到文件
    readFile 		: 从文件中读取字符串
    copyFileFast 	: 快速复制
    shareFile 		: 分享文件
    zip 			: zip压缩
    unzip 			: zip解压
    formatFileSize 	: 格式化文件大小
    Stream2File 	: 将输入流写入到文件
    createFolder 	: 创建文件夹
    createFolder 	: 创建文件夹(支持覆盖已存在的同名文件夹)
    getFolderName 	: 获取文件夹名称
    deleteFile 		: 删除目录下的文件
    openImage 		: 打开图片
    openVideo 		: 打开视频
    openURL 		: 打开URL

---

### Fragment 相关 -> FragmentUtils.java
>
    add                   : 新增 fragment
    show                  : 显示 fragment
    hide                  : 隐藏 fragment
    showHide              : 先显示后隐藏 fragment
    replace               : 替换 fragment
    pop                   : 出栈 fragment
    popTo                 : 出栈到指定 fragment
    popAll                : 出栈所有 fragment
    remove                : 移除 fragment
    removeTo              : 移除到指定 fragment
    removeAll             : 移除所有 fragment
    getTop                : 获取顶部 fragment
    getTopInStack         : 获取栈中顶部 fragment
    getTopShow            : 获取顶部可见 fragment
    getTopShowInStack     : 获取栈中顶部可见 fragment
    getFragments          : 获取同级别的 fragment
    getFragmentsInStack   : 获取同级别栈中的 fragment
    getAllFragments       : 获取所有 fragment
    getAllFragmentsInStack: 获取栈中所有 fragment
    findFragment          : 查找 fragment
    dispatchBackPress     : 处理 fragment 回退键
    setBackgroundColor    : 设置背景色
    setBackgroundResource : 设置背景资源
    setBackground         : 设置背景

---

### 图片相关 -> Image01Utils.java
>
    bitmap2Bytes, bytes2Bitmap      : bitmap 与 byteArr 互转
    drawable2Bitmap, bitmap2Drawable: drawable 与 bitmap 互转
    drawable2Bytes, bytes2Drawable  : drawable 与 byteArr 互转
    view2Bitmap                     : view 转 bitmap
    getBitmap                       : 获取 bitmap
    scale                           : 缩放图片
    clip                            : 裁剪图片
    skew                            : 倾斜图片
    rotate                          : 旋转图片
    getRotateDegree                 : 获取图片旋转角度
    toRound                         : 转为圆形图片
    toRoundCorner                   : 转为圆角图片
    addCornerBorder                 : 添加圆角边框
    addCircleBorder                 : 添加圆形边框
    addReflection                   : 添加倒影
    addTextWatermark                : 添加文字水印
    addImageWatermark               : 添加图片水印
    toAlpha                         : 转为 alpha 位图
    toGray                          : 转为灰度图片
    fastBlur                        : 快速模糊
    renderScriptBlur                : renderScript 模糊图片
    stackBlur                       : stack 模糊图片
    save                            : 保存图片
    isImage                         : 根据文件名判断文件是否为图片
    getImageType                    : 获取图片类型
    compressByScale                 : 按缩放压缩
    compressByQuality               : 按质量压缩
    compressBySampleSize            : 按采样大小压缩

---

### 图片相关 -> ImageUtils.java
>
	calculateInSampleSize	: 计算图片的压缩比率
    getPictureDegree 		: 获取图片的角度
    rotaingImageView 		: 旋转图片
    decodeScaleImage 		: 加载图片并压缩
    getRoundedCornerBitmap 	: 获取圆角图片
    //* decodeUriAsBitmap 	: 解析URL流为图片
    bitmap2File 			: bitmap存为文件
    compressImage 			: 质量压缩
    compressFixBitmap 		: 固定大小压缩

---

### 意图相关 -> IntentUtils.java
>
    getInstallAppIntent        : 获取安装 App（支持 6.0）的意图
    getUninstallAppIntent      : 获取卸载 App 的意图
    getLaunchAppIntent         : 获取打开 App 的意图
    getAppDetailsSettingsIntent: 获取 App 具体设置的意图
    getShareTextIntent         : 获取分享文本的意图
    getShareImageIntent        : 获取分享图片的意图
    getComponentIntent         : 获取其他应用组件的意图
    getShutdownIntent          : 获取关机的意图
    getCaptureIntent           : 获取拍照的意图

---

### Json工具类 -> JsonUtils.java
>
	toJson 				: 对象转json
    fromJson 			: json转对象
    mapToJson 			: Map转为JSONObject
    collection2Json 	: 集合转换为JSONArray
    object2Json 		: Object对象转换为JSONArray
    string2JSONObject 	: json字符串生成JSONObject对象

---

### 键盘相关 -> KeyboardUtils.java
>
    showSoftInput                   : 动态显示软键盘
    hideSoftInput                   : 动态隐藏软键盘
    toggleSoftInput                 : 切换键盘显示与否状态
    isSoftInputVisible              : 判断软键盘是否可见
    registerSoftInputChangedListener: 注册软键盘改变监听器
    clickBlankArea2HideSoftInput    : 点击屏幕空白区域隐藏软键盘

---

### 日志相关 -> LogUtils.java
>
    getConfig               : 获取 log 配置
    Config.setLogSwitch     : 设置 log 总开关
    Config.setConsoleSwitch : 设置 log 控制台开关
    Config.setGlobalTag     : 设置 log 全局 tag
    Config.setLogHeadSwitch : 设置 log 头部信息开关
    Config.setLog2FileSwitch: 设置 log 文件开关
    Config.setDir           : 设置 log 文件存储目录
    Config.setFilePrefix    : 设置 log 文件前缀
    Config.setBorderSwitch  : 设置 log 边框开关
    Config.setConsoleFilter : 设置 log 控制台过滤器
    Config.setFileFilter    : 设置 log 文件过滤器
    Config.setStackDeep     : 设置 log 栈深度
    v                       : tag 为类名的 Verbose 日志
    vTag                    : 自定义 tag 的 Verbose 日志
    d                       : tag 为类名的 Debug 日志
    dTag                    : 自定义 tag 的 Debug 日志
    i                       : tag 为类名的 Info 日志
    iTag                    : 自定义 tag 的 Info 日志
    w                       : tag 为类名的 Warn 日志
    wTag                    : 自定义 tag 的 Warn 日志
    e                       : tag 为类名的 Error 日志
    eTag                    : 自定义 tag 的 Error 日志
    a                       : tag 为类名的 Assert 日志
    aTag                    : 自定义 tag 的 Assert 日志
    file                    : log 到文件
    json                    : log 字符串之 json
    xml                     : log 字符串之 xml

---

### 吐司相关 -> MultiToastUtils.java
>
    setGravity     : 设置吐司位置
    setBgColor     : 设置背景颜色
    setBgResource  : 设置背景资源
    setMessageColor: 设置消息颜色
    showShort      : 显示短时吐司
    showLong       : 显示长时吐司
    showCustomShort: 显示短时自定义吐司
    showCustomLong : 显示长时自定义吐司
    cancel         : 取消吐司显示

---

### 网络相关 -> NetUtils.java
>
    getNetworkType 			: 获取网络类型
    getNetworkTypeName 		: 获取网络名称
    isConnected 			: 检查网络状态
    isNetworkAvailable 		: 网络可用性
    isWiFi 					: 是否wifi
    openNetSetting 			: 打开网络设置界面
    setWifiEnabled 			: 设置wifi状态
    getWifiScanResults 		: 获取wifi列表
    getScanResultsByBSSID 	: 过滤扫描结果
    getWifiConnectionInfo 	: 获取wifi连接信息

---

### 网络相关 -> NetworkUtils.java
>
    openWirelessSettings  : 打开网络设置界面
    isConnected           : 判断网络是否连接
    isAvailableByPing     : 判断网络是否可用
    getMobileDataEnabled  : 判断移动数据是否打开
    setMobileDataEnabled  : 打开或关闭移动数据
    isMobileData          : 判断网络是否是移动数据
    is4G                  : 判断网络是否是 4G
    getWifiEnabled        : 判断 wifi 是否打开
    setWifiEnabled        : 打开或关闭 wifi
    isWifiConnected       : 判断 wifi 是否连接状态
    isWifiAvailable       : 判断 wifi 数据是否可用
    getNetworkOperatorName: 获取移动网络运营商名称
    getNetworkType        : 获取当前网络类型
    getIPAddress          : 获取 IP 地址
    getDomainAddress      : 获取域名 ip 地址

---

### 对象相关 -> ObjectUtils.java
>
    isEmpty   : 判断对象是否为空
    isNotEmpty: 判断对象是否非空
    equals    : 判断对象是否相等

---

### 手机相关 -> PhoneUtils.java
>
    isPhone            : 判断设备是否是手机
    getIMEI            : 获取 IMEI 码
    getIMSI            : 获取 IMSI 码
    getPhoneType       : 获取移动终端类型
    isSimCardReady     : 判断 sim 卡是否准备好
    getSimOperatorName : 获取 Sim 卡运营商名称
    getSimOperatorByMnc: 获取 Sim 卡运营商名称
    getPhoneStatus     : 获取手机状态信息
    dial               : 跳至拨号界面
    call               : 拨打 phoneNumber
    sendSms            : 跳至发送短信界面
    sendSmsSilent      : 发送短信
    getAllContactInfo  : 获取手机联系人
    getContactNum      : 打开手机联系人界面点击联系人后便获取该号码
    getAllSMS          : 获取手机短信并保存到 xml 中

---

### 进程相关 -> ProcessUtils.java
>
    getForegroundProcessName  : 获取前台线程包名
    killAllBackgroundProcesses: 杀死所有的后台服务进程
    killBackgroundProcesses   : 杀死后台服务进程

---

### 正则相关 -> RegexUtils.java
>
    isMobileSimple : 验证手机号（简单）
    isMobileExact  : 验证手机号（精确）
    isTel          : 验证电话号码
    isIDCard15     : 验证身份证号码 15 位
    isIDCard18     : 验证身份证号码 18 位
    isEmail        : 验证邮箱
    isURL          : 验证 URL
    isZh           : 验证汉字
    isUsername     : 验证用户名
    isDate         : 验证 yyyy-MM-dd 格式的日期校验，已考虑平闰年
    isIP           : 验证 IP 地址
    isMatch        : 判断是否匹配正则
    getMatches     : 获取正则匹配的部分
    getSplits      : 获取正则匹配分组
    getReplaceFirst: 替换正则匹配的第一部分
    getReplaceAll  : 替换所有正则匹配的部分

---

### 屏幕相关 -> ScreenUtils.java
>
    getScreenWidth     : 获取屏幕的宽度（单位：px）
    getScreenHeight    : 获取屏幕的高度（单位：px）
    getScreenDensity   : 获取屏幕密度
    getScreenDensityDpi: 获取屏幕密度 DPI
    setFullScreen      : 设置屏幕为全屏
    setLandscape       : 设置屏幕为横屏
    setPortrait        : 设置屏幕为竖屏
    isLandscape        : 判断是否横屏
    isPortrait         : 判断是否竖屏
    getScreenRotation  : 获取屏幕旋转角度
    screenShot         : 截屏
    isScreenLock       : 判断是否锁屏
    setSleepDuration   : 设置进入休眠时长
    getSleepDuration   : 获取进入休眠时长
    isTablet           : 判断是否是平板

---

### SD 卡相关 -> SDCardUtils.java
>
    isSDCardEnable: 判断 SD 卡是否可用
    getSDCardPaths: 获取 SD 卡路径

---

### 栏服务相关 -> ServiceUtils.java
>
    getAllRunningService: 获取所有运行的服务
    startService        : 启动服务
    stopService         : 停止服务
    bindService         : 绑定服务
    unbindService       : 解绑服务
    isServiceRunning    : 判断服务是否运行

---

### Shell 相关 -> ShellUtils.java
>
    execCmd: 是否是在 root 下执行命令

---

### 尺寸相关 -> SizeUtils.java
>
    dp2px, px2dp     : dp 与 px 转换
    sp2px, px2sp     : sp 与 px 转换
    applyDimension   : 各种单位转换
    forceGetViewSize : 在 onCreate 中获取视图的尺寸
    measureView      : 测量视图尺寸
    getMeasuredWidth : 获取测量视图宽度
    getMeasuredHeight: 获取测量视图高度

---

### Snackbar 相关 -> SnackbarUtils.java
>
    with           : 设置 snackbar 依赖 view
    setMessage     : 设置消息
    setMessageColor: 设置消息颜色
    setBgColor     : 设置背景色
    setBgResource  : 设置背景资源
    setDuration    : 设置显示时长
    setAction      : 设置行为
    setBottomMargin: 设置底边距
    show           : 显示 snackbar
    showSuccess    : 显示预设成功的 snackbar
    showWarning    : 显示预设警告的 snackbar
    showError      : 显示预设错误的 snackbar
    dismiss        : 消失 snackbar
    getView        : 获取 snackbar 视图
    addView        : 添加 snackbar 视图

---

### SpannableString 相关 -> SpanUtils.java
>
    setFlag           : 设置标识
    setForegroundColor: 设置前景色
    setBackgroundColor: 设置背景色
    setLineHeight     : 设置行高
    setQuoteColor     : 设置引用线的颜色
    setLeadingMargin  : 设置缩进
    setBullet         : 设置列表标记
    setIconMargin     : 设置图标
    setFontSize       : 设置字体尺寸
    setFontProportion : 设置字体比例
    setFontXProportion: 设置字体横向比例
    setStrikethrough  : 设置删除线
    setUnderline      : 设置下划线
    setSuperscript    : 设置上标
    setSubscript      : 设置下标
    setBold           : 设置粗体
    setItalic         : 设置斜体
    setBoldItalic     : 设置粗斜体
    setFontFamily     : 设置字体系列
    setTypeface       : 设置字体
    setAlign          : 设置对齐
    setClickSpan      : 设置点击事件
    setUrl            : 设置超链接
    setBlur           : 设置模糊
    setShader         : 设置着色器
    setShadow         : 设置阴影
    setSpans          : 设置样式
    append            : 追加样式字符串
    appendLine        : 追加一行样式字符串
    appendImage       : 追加图片
    appendSpace       : 追加空白
    create            : 创建样式字符串

---

### SP 相关 -> SPUtils.java
>
    getInstance: 获取 SP 实例
    put        : SP 中写入数据
    getString  : SP 中读取 String
    getInt     : SP 中读取 int
    getLong    : SP 中读取 long
    getFloat   : SP 中读取 float
    getBoolean : SP 中读取 boolean
    getAll     : SP 中获取所有键值对
    contains   : SP 中是否存在该 key
    remove     : SP 中移除该 key
    clear      : SP 中清除所有数据

---

### 字符串相关 -> String01Utils.java
>
    isEmpty         : 判断字符串是否为 null 或长度为 0
    isTrimEmpty     : 判断字符串是否为 null 或全为空格
    isSpace         : 判断字符串是否为 null 或全为空白字符
    equals          : 判断两字符串是否相等
    equalsIgnoreCase: 判断两字符串忽略大小写是否相等
    null2Length0    : null 转为长度为 0 的字符串
    length          : 返回字符串长度
    upperFirstLetter: 首字母大写
    lowerFirstLetter: 首字母小写
    reverse         : 反转字符串
    toDBC           : 转化为半角字符
    toSBC           : 转化为全角字符

---

### 字符串相关 -> StringUtils.java
>
    getChsAscii			: 汉字转成ASCII码
    convert 			: 单字解析
    getSelling 			: 词组解析
    parseEmpty 			: 将null转化为""
    isEmpty 			: 是否是空字符串
    chineseLength 		: 中文长度
    strLength 			: 字符串长度
    subStringLength 	: 获取指定长度的字符所在位置
    isChinese 			: 是否是中文
    isContainChinese 	: 是否包含中文
    strFormat2 			: 不足2位前面补0
    convert2Int 		: 类型安全转换
    decimalFormat 		: 指定小数输出

---

### 系统工具 -> SystemUtils.java
>
    sendSMS					: 调用系统发送短信
    forwardToDial 			: 跳转到拨号
    callPhone 				: 直接呼叫号码
    sendMail 				: 发邮件
    hideKeyBoard 			: 隐藏系统键盘
    isBackground 			: 判断当前应用程序是否后台运行
    isSleeping 				: 判断手机是否处理睡眠
    installApk 				: 安装apk
    isRooted 				: 是否root
    isRunningOnEmulator 	: 当前设备是否是模拟器
    getAppVersionName 		: 获取当前应用程序的版本名称
    getAppVersionCode 		: 获取当前应用程序的版本号
    goHome 					: 返回Home
    getSign 				: 获取应用签名
    hexdigest 				: 32位签名
    getDeviceUsableMemory 	: 获取设备可用空间
    gc 						: 清理后台进程和服务
    createDeskShortCut 		: 创建桌面快捷方式
    createShortcut 			: 创建快捷方式
    shareText 				: 分享文本
    shareFile 				: 分享文件(此方法是调用FileUtils.shareFile中的方式)
    getShareTargets 		: 获取可接受分享的应用
    getCurrentLanguage 		: 获取当前系统的语言 
    getLanguage 			: 获取当前系统的语言
    isGpsEnabled 			: GPS是否打开
    showSoftInputMethod 	: 显示软键盘
    closeSoftInputMethod 	: 关闭软键盘
    showSoftInput 			: 显示软键盘
    closeSoftInput 			: 关闭软键盘

---

### 时间相关 -> TimeUtils.java
>
    millis2String           : 将时间戳转为时间字符串
    string2Millis           : 将时间字符串转为时间戳
    string2Date             : 将时间字符串转为 Date 类型
    date2String             : 将 Date 类型转为时间字符串
    date2Millis             : 将 Date 类型转为时间戳
    millis2Date             : 将时间戳转为 Date 类型
    getTimeSpan             : 获取两个时间差（单位：unit）
    getFitTimeSpan          : 获取合适型两个时间差
    getNowMills             : 获取当前毫秒时间戳
    getNowString            : 获取当前时间字符串
    getNowDate              : 获取当前 Date
    getTimeSpanByNow        : 获取与当前时间的差（单位：unit）
    getFitTimeSpanByNow     : 获取合适型与当前时间的差
    getFriendlyTimeSpanByNow: 获取友好型与当前时间的差
    getMillis               : 获取与给定时间等于时间差的时间戳
    getString               : 获取与给定时间等于时间差的时间字符串
    getDate                 : 获取与给定时间等于时间差的 Date
    getMillisByNow          : 获取与当前时间等于时间差的时间戳
    getStringByNow          : 获取与当前时间等于时间差的时间字符串
    getDateByNow            : 获取与当前时间等于时间差的 Date
    isToday                 : 判断是否今天
    isLeapYear              : 判断是否闰年
    getChineseWeek          : 获取中式星期
    getUSWeek               : 获取美式式星期
    getWeekIndex            : 获取星期索引
    getWeekOfMonth          : 获取月份中的第几周
    getWeekOfYear           : 获取年份中的第几周
    getChineseZodiac        : 获取生肖
    getZodiac               : 获取星座

---

### 验证工具类 -> VerificationUtils.java
>
    matchRealName		: 判断姓名格式  
    	```
    	真实姓名可以是汉字,也可以是字母,但是不能两者都有,也不能包含任何符号和数字
    		1.如果是英文名,可以允许英文名字中出现空格
    		2.英文名的空格可以是多个,但是不能连续出现多个
    		3.汉字不能出现空格
    	```	
    matchPhoneNum 		: 判断手机号格式  (匹配11数字,并且13-19开头)
    matchAccount 		: 判断账号格式 (4-20位字符)
    matchPassword 		: 判断密码格式 (6-12位字母或数字)
    matchPassword2 		: 判断密码格式 (6-12位字母或数字,必须同时包含字母和数字)
    matchEmail 			: 判断邮箱格式
    matchIP 			: 判断IP地址
    matchUrl 			: 判断URL (http,https,ftp)
    matchVehicleNumber 	: 判断中国民用车辆号牌
    matchIdentityCard 	: 判断身份证号码格式
    matchPostcode 		: 匹配中国邮政编码
    matchNumeric 		: 是否数值型
    matchRegex 			: 是否匹配正则

---

```shell 

    /**
     * 身份证校验
     * <p>
     * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定,公民身份号码是特征组合码,由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码,八位数字出生日期码,三位数字顺序码和一位数字校验码。
     * 地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。
     * 出生日期码表示编码对象出生的年、月、日,其中年份用四位数字表示,年、月、日之间不用分隔符。
     * 顺序码表示同一地址码所标识的区域范围内,对同年、月、日出生的人员编定的顺序号。顺序码的奇数分给男性,偶数分给女性。
     * 校验码是根据前面十七位数字码,按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。
     * 出生日期计算方法。
     * 15位的身份证编码首先把出生年扩展为4位,简单的就是增加一个19或18,这样就包含了所有1800-1999年出生的人;
     * 2000年后出生的肯定都是18位的了没有这个烦恼,至于1800年前出生的,那啥那时应该还没身份证号这个东东,⊙﹏⊙b汗...
     * 下面是正则表达式:
     * 出生日期1800-2099  /(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])/
     * 身份证正则表达式 /^[1-9]\d{5}((1[89]|20)\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dx]$/i
     * 15位校验规则 6位地址编码+6位出生日期+3位顺序号
     * 18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
     * 校验位规则     公式:∑(ai×Wi)(mod 11)……………………………………(1)
     * 公式(1)中：
     * i----表示号码字符从由至左包括校验码在内的位置序号;
     * ai----表示第i位置上的号码字符值；
     * Wi----示第i位置上的加权因子,其数值依据公式Wi=2^(n-1）(mod 11)计算得出。
     * i 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
     * Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
     * </P>
     *
     * @author Yoojia.Chen (yoojia.chen@gmail.com)
     * @version version 2015-05-21
     * @since 2.0
     */
```

### View工具 -> ViewUtils.java
>
    removeSelfFromParent	: 
    requestLayoutParent		: 
    isTouchInView			: 
    bigImage				: 
    setTVUnderLine 			: 给TextView设置下划线
    showPopupWindow			: 
    dismissPopup			: 
    captureView 			: 截图
    createViewBitmap 		: 截图
    convertViewToBitmap 	: 截图
    getActivityBitmap 		: 获取Activity的截图
    getStatusBarHeight 		: 获取状态栏高度
    getToolbarHeight 		: 获取工具栏高度
    getNavigationBarHeight 	: 获取导航栏高度
    measureView 			: 测量view
    getViewWidth 			: 获取view的宽度
    getViewHeight 			: 获取view的高度

---

### 压缩相关 -> ZipUtils.java
>
    zipFile           : 压缩文件
    unzipFile         : 解压文件
    unzipFileByKeyword: 解压带有关键字的文件
    getFilesPath      : 获取压缩文件中的文件路径链表
    getComments       : 获取压缩文件中的注释链表

---


# 这个库参考了众多网络的中的代码,在此对这些无私奉献的人致以最诚挚的感谢。

