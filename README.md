## Some Useful Utils Code for Android Develop

### Activity Utils
方法签名|描述
---|---
isActivityExists(String packageName, String className)|判断是否存在某Activity
-|
launchActivity(String packageName, String className)|打开Activity
launchActivity(String packageName, String className, Bundle bundle)|打开Activity
-|
getLauncherActivity(String packageName)|获取某包下的 Launcher Activity
getTopActivity()|获取栈顶 Activity

### Animation Utils
方法签名|描述
---|---
inFromTopAnimation(long durationMills)|从顶部进入动画
inFromBottomAnimation(long durationMills)|从底部进入动画
inFromLeftAnimation(long durationMills)|从左边进入动画
inFromRightAnimation(long durationMills)|从右边进入动画
inFromCenterAnimation(long durationMills)|从中部进入动画
-|
outToTopAnimation(long durationMills)|从顶部退出动画
outToBottomAnimation(long durationMills)|从底部退出动画
outToLeftAnimation(long durationMills)|从左边退出动画
outToRightAnimation(long durationMills)|从右边退出动画
outToCenterAnimation(long durationMills)|从中部退出动画
-|
shakeAnimation(int counts, long durationMills)|抖动动画

### App Utils
方法签名|描述
---|---
installApp(Context context, String filePath)|安装app(支持6.0)
installApp(Context context, File file)|安装app(支持6.0)
installApp(Activity activity, String filePath, int requestCode)|安装App(支持6.0)
installApp(Activity activity, File file, int requestCode)|安装App(支持6.0)
installAppSilent(String filePath)|静默安装App
-|
uninstallApp(Context context, String packageName)|卸载App
uninstallApp(Activity activity, String packageName, int requestCode)|卸载App
uninstallAppSilent(Context context, String packageName, boolean isKeepData)|静默卸载App
-|
launchApp(String packageName)|打开App
launchApp(Activity activity, String packageName, int requestCode)|打开App
-|
getAppDetailsSettings(Context context)|获取App具体设置
getAppDetailsSettings(Context context, String packageName)|获取App具体设置
getAppName(Context context)|获取App名称
getAppName(Context context, String packageName)|获取App名称
getAppIcon(Context context)|获取App图标
getAppIcon(Context context, String packageName)|获取App图标
getAppPath(Context context)|获取App路径
getAppPath(Context context, String packageName)|获取App路径
getAppVersionName(Context context)|获取App版本号
getAppVersionName(Context context, String packageName)|获取App版本号
getAppVersionCode(Context context)|获取App版本码
getAppVersionCode(Context context, String packageName)|获取App版本码
-|
getAppSignature(Context context)|获取App签名
getAppSignature(Context context, String packageName)|获取App签名
getAppSignatureSHA1(Context context)|获取应用签名的SHA1值
getAppSignatureSHA1(Context context, String packageName)|获取应用签名的SHA1值
-|
getAppInfo(Context context)|获取App信息
getAppInfo(Context context, String packageName)|获取App信息
getAppsInfo(Context context)|获取所有已安装App的信息
-|
isInstallApp(Context context, String packageName)|判断App是否安装
isAppRoot()|判断App是否有root权限
isSystemApp(Context context)|判断App是否为系统应用
isSystemApp(Context context, String packageName)|判断App是否为系统应用
isAppDebug(Context context)|判断App是否为Debug版本
isAppDebug(Context context, String packageName)|判断App是否为Debug版本
isAppForeground(Context context)|判断App是否处于前台
-|
cleanAppData(String... dirPaths)|清除App所有数据
cleanAppData(File... dirs)|清除App所有数据

### Clean Utils
方法签名|描述
---|---
cleanInternalCache()|清除内部缓存
cleanInternalFiles()|清除内部文件
cleanInternalDBs()|清除内部数据库
cleanInternalDB(String dbName)|根据名称清除数据库
cleanInternalSP()|清除内部SP文件
-|
cleanExternalCache()|清除外部缓存
cleanCustomCache(String dirPath)|清除自定义目录下的文件
cleanCustomCache(File dir)|清除自定义目录下的文件

### Clipboard Utils
方法签名|描述
---|---
copyText(CharSequence text)|复制文本到剪贴板
getText()|获取剪贴板的文本
-|
copyUri(Uri uri)|复制 Uri 到剪贴板
getUri()|获取剪贴板的 Uri
-|
copyIntent(Intent intent)|复制 Intent 到剪贴板
getIntent()|获取剪贴板的 Intent

### Close Utils
方法签名|描述
---|---
closeIO(Closeable... closeables)|关闭IO
closeIOQuietly(Closeable... closeables)|安静的关闭IO

### Const Utils
常量名|描述
---|---
与存储相关|
KB|KB与Byte的倍数
MB|MB与Byte的倍数
GB|GB与Byte的倍数
与时间相关|
SEC|秒与毫秒的倍数
MIN|分与毫秒的倍数
HOUR|时与毫秒的倍数
DAY|天与毫秒的倍数
与正则相关|
REGEX_MOBILE|正则: 手机号(简单)
REGEX_TEL|正则: 电话号码
REGEX_ID_CARD15|正则: 身份证号码15位
REGEX_ID_CARD18|正则: 身份证号码18位
REGEX_EMAIL|正则: 邮箱
REGEX_URL|正则: URL
REGEX_ZH|正则: 汉字
REGEX_USERNAME|正则: 用户名(取值范围为a-z,A-Z,0-9,"\_",汉字，不能以"\_"结尾,用户名必须是6-20位)
REGEX_DATE|正则: yyyy-MM-dd格式的日期校验(已考虑平闰年)
REGEX_IP|正则: IP地址

### Convert Utils
方法签名|描述
---|---
bytes2HexString(byte[] bytes)|byte数组转换为hex字符串
hexString2Bytes(String hexString)|hex字符串转换为byte数组
hex2Dec(char hexChar)|hex字符转换为int
chars2Bytes(char[] chars)|char数组转换为byte数组
bytes2Chars(byte[] bytes)|byte数组转换为char数组
bytes2Bits(byte[] bytes)|byte数组转换为bit字符串
bits2Bytes(String bits)|bit字符串转换为byte数组
-|
memorySize2Byte(long memorySize, ConstUtils.MemoryUnit unit)|以unit为单位的内存大小, 转换为字节数
byte2MemorySize(long byteSize, ConstUtils.MemoryUnit unit)|字节数转换为以unit为单位的内存大小
byte2FitMemorySize(long byteSize)|字节数转换为合适显示的内存大小字符串
-|
timeSpan2Millis(long timeSpan, ConstUtils.TimeUnit unit)|以unit为单位的时间长度, 转换为毫秒时间
millis2TimeSpan(long millis, ConstUtils.TimeUnit unit)|将毫秒时间转换为以unit为单位的时间长度
-|
input2OutputStream(InputStream is)|inputStream转换为outputStream
output2InputStream(OutputStream os)|outputStream转换为inputStream
-|
inputStream2Bytes(InputStream is)|inputStream转换为byte数组
bytes2InputStream(byte[] bytes)|byte数组转换为inputStream
outputStream2Bytes(OutputStream os)|outputStream转换为byte数组
bytes2OutputStream(byte[] bytes)|byte数组转换为outputStream
-|
inputStream2String(InputStream is, String charsetName)|inputStream按编码格式转换为string
string2InputStream(String string, String charsetName)|string按编码格式转换为inputStream
outputStream2String(OutputStream os, String charsetName)|outputStream按编码格式转换为string
string2OutputStream(String string, String charsetName)|string按编码格式转换为outputStream
-|
bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format)|bitmap转换为byte数组
bytes2Bitmap(byte[] bytes)|byte数组转换为bitmap
drawable2Bitmap(Drawable drawable)|drawable转换为bitmap
bitmap2Drawable(Resources res, Bitmap bitmap)|bitmap转换为drawable
drawable2Bytes(Drawable drawable, Bitmap.CompressFormat format)|drawable转换为byte数组
bytes2Drawable(Resources res, byte[] bytes)|byte数组转换为drawable
-|
view2Bitmap(View view)|view转换为bitmap

### Device Utils
方法签名|描述
---|---
isRooted()|判断设备是否已root
-|
getSDKVersion()|获取设备系统版本号
getAndroidID()|获取设备的AndroidID
getManufacturer()|获取设备厂商信息
getModel()|获取设备型号
-|
getMacAddress()|获取设备的MAC地址
getMacAddressByWifiInfo()|通过Wifi信息获取设备的MAC地址
getMacAddressByNetworkInterface()|通过网卡信息获取设备的Mac地址
getMacAddressByFile()|通过系统文件信息获取设备的Mac地址
-|
shutdown()|关机
reboot()|重启
reboot(String reason)|重启到某引导模式
reboot2Recovery()|重启到Recovery模式
reboot2Bootloader()|重启到bootloader模式

### Empty Utils
方法签名|描述
---|---
isEmpty(Object obj)|判断对象是否为空
isNotEmpty(Object obj)|判断对象是否非空

### Encode Utils
方法签名|描述
---|---
urlEncode(String input)|URL编码(使用UTF-8编码)
urlEncode(String input, String charset)|URL编码(若系统不支持指定的编码字符集,则直接将input原样返回)
urlDecode(String input)|URL解码(使用UTF-8编码)
urlDecode(String input, String charset)|URL解码(若系统不支持指定的解码字符集,则直接将input原样返回)
-|
base64Encode(String input)|Base64编码
base64Encode(byte[] input)|Base64编码
base64Encode2String(byte[] input)|Base64编码为字符串
base64Decode(String input)|Base64解码
base64Decode(byte[] input)|Base64解码
base64UrlSafeEncode(String input)|Base64URL安全编码
-|
htmlEncode(CharSequence input)|Html编码
htmlDecode(String input)|Html解码

### Encrypt Utils
方法签名|描述
---|---
encryptMD2ToString(String data)|MD2加密
encryptMD2ToString(byte[] data)|MD2加密
encryptMD2(byte[] data)|MD2加密
-|
encryptMD5ToString(String data)|MD5加密
encryptMD5ToString(String data, String salt)|MD5加密
encryptMD5ToString(byte[] data)|MD5加密
encryptMD5ToString(byte[] data, byte[] salt)|MD5加密
encryptMD5(byte[] data)|MD5加密
-|
encryptMD5FileToString(String filePath)|MD5加密文件(实质为做散列计算)
encryptMD5FileToString(File file)|MD5加密文件(实质为做散列计算)
encryptMD5File(String filePath)|MD5加密文件(实质为做散列计算)
encryptMD5File(File file)|MD5加密文件(实质为做散列计算)
-|
encryptSHA1ToString(String data)|SHA1加密
encryptSHA1ToString(byte[] data)|SHA1加密
encryptSHA1(byte[] data)|SHA1加密
-|
encryptSHA224ToString(String data)|SHA224加密
encryptSHA224ToString(byte[] data)|SHA224加密
encryptSHA224(byte[] data)|SHA224加密
-|
encryptSHA256ToString(String data)|SHA256加密
encryptSHA256ToString(byte[] data)|SHA256加密
encryptSHA256(byte[] data)|SHA256加密
-|
encryptSHA384ToString(String data)|SHA384加密
encryptSHA384ToString(byte[] data)|SHA384加密
encryptSHA384(byte[] data)|SHA384加密
-|
encryptSHA512ToString(String data)|SHA512加密
encryptSHA512ToString(byte[] data)|SHA512加密
encryptSHA512(byte[] data)|SHA512加密
-|
encryptHmacMD5ToString(String data, String key)|Hmac-MD5加密
encryptHmacMD5ToString(byte[] data, byte[] key)|Hmac-MD5加密
encryptHmacMD5(byte[] data, byte[] key)|Hmac-MD5加密
-|
encryptHmacSHA1ToString(String data, String key)|Hmac-SHA1加密
encryptHmacSHA1ToString(byte[] data, byte[] key)|Hmac-SHA1加密
encryptHmacSHA1(byte[] data, byte[] key)|Hmac-SHA1加密
-|
encryptHmacSHA224ToString(String data, String key)|Hmac-SHA224加密
encryptHmacSHA224ToString(byte[] data, byte[] key)|Hmac-SHA224加密
encryptHmacSHA224(byte[] data, byte[] key)|Hmac-SHA224加密
-|
encryptHmacSHA256ToString(String data, String key)|Hmac-SHA256加密
encryptHmacSHA256ToString(byte[] data, byte[] key)|Hmac-SHA256加密
encryptHmacSHA256(byte[] data, byte[] key)|Hmac-SHA256加密
-|
encryptHmacSHA384ToString(String data, String key)|Hmac-SHA384加密
encryptHmacSHA384ToString(byte[] data, byte[] key)|Hmac-SHA384加密
encryptHmacSHA384(byte[] data, byte[] key)|Hmac-SHA384加密
-|
encryptHmacSHA512ToString(String data, String key)|Hmac-SHA512加密
encryptHmacSHA512ToString(byte[] data, byte[] key)|Hmac-SHA512加密
encryptHmacSHA512(byte[] data, byte[] key)|Hmac-SHA512加密
-|
encryptDESToBase64(byte[] data, byte[] key)|DES加密后转为Base64编码
encryptDESToHexString(byte[] data, byte[] key)|DES加密后转为16进制
encryptDES(byte[] data, byte[] key)|DES加密
-|
decryptBase64ViaDES(byte[] data, byte[] key)|DES解密Base64编码密文
decryptHexStringViaDES(String data, byte[] key)|DES解密16进制密文
decryptDES(byte[] data, byte[] key)|DES解密
-|
encrypt3DESToBase64(byte[] data, byte[] key)|3DES加密后转为Base64编码
encrypt3DESToHexString(byte[] data, byte[] key)|3DES加密后转为16进制
encrypt3DES(byte[] data, byte[] key)|3DES加密
-|
decryptBase64Via3DES(byte[] data, byte[] key)|3DES解密Base64编码密文
decryptHexStringVia3DES(String data, byte[] key)|3DES解密16进制密文
decrypt3DES(byte[] data, byte[] key)|3DES解密
-|
encryptAESToBase64(byte[] data, byte[] key)|AES加密后转为Base64编码
encryptAESToHexString(byte[] data, byte[] key)|AES加密后转为16进制
encryptAES(byte[] data, byte[] key)|AES加密
-|
decryptBase64ViaAES(byte[] data, byte[] key)|AES解密Base64编码密文
decryptHexStringViaAES(String data, byte[] key)|AES解密16进制密文
decryptAES(byte[] data, byte[] key)|AES解密
-|
hashTemplate(byte[] data, String algorithm)|hash加密模板
hmacTemplate(byte[] data, byte[] key, String algorithm)|Hmac加密模版
desTemplate(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt)|DES加密模板(适用于DES/3DES/AES)

### File Utils
方法签名|描述
---|---
getFileByPath(String filePath)|根据文件路径获取文件
-|
isFileExists(String filePath)|判断文件是否存在
isFileExists(File file)|判断文件是否存在
-|
rename(String filePath, String newName)|重命令文件
rename(File file, String newName)|重命令文件
-|
isDir(String dirPath)|判断是否为目录
isDir(File file)|判断是否为目录
isFile(String filePath)|判断是否为文件
isFile(File file)|判断是否为文件
-|
createOrExistsDir(String dirPath)|判断目录是否存在, 不存在则判断是否创建目录成功
createOrExistsDir(File file)|判断目录是否存在, 不存在则判断是否创建目录成功
createOrExistsFile(String filePath)|判断文件是否存在, 不存在则判断是否创建成功
createOrExistsFile(File file)|判断文件是否存在, 不存在则判断是否创建成功
createFileByDeleteOldFile(String filePath)|判断文件是否存在, 存在则在创建之前删除旧文件, 然后返回创建的结果
createFileByDeleteOldFile(File file)|判断文件是否存在, 存在则在创建之前删除旧文件, 然后返回创建的结果
-|
copyOrMoveDir(String srcDirPath, String destDirPath, boolean isMove)|复制或移动目录
copyOrMoveDir(File srcDir, File destDir, boolean isMove)|复制或移动目录
copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove)|复制或移动文件
copyOrMoveFile(File srcFile, File destFile, boolean isMove)|复制或移动文件
copyDir(String srcDirPath, String destDirPath)|复制目录
copyDir(File srcDir, File destDir)|复制目录
copyFile(String srcFilePath, String destFilePath)|复制文件
copyFile(File srcFile, File destFile)|复制文件
-|
moveDir(String srcDirPath, String destDirPath)|移动目录
moveDir(File srcDir, File destDir)|移动目录
moveFile(String srcFilePath, String destFilePath)|移动文件
moveFile(File srcFile, File destFile)|移动文件
-|
deleteDir(String dirPath)|删除目录
deleteDir(File dir)|删除目录
deleteFile(String filePath)|删除文件
deleteFile(File file)|删除文件
deleteFilesInDir(String dirPath)|删除目录下的所有文件
deleteFilesInDir(File dir)|删除目录下的所有文件
-|
listFilesInDir(String dirPath, boolean isRecursive)|获取目录下所有文件
listFilesInDir(File dir, boolean isRecursive)|获取目录下所有文件
listFilesInDir(String dirPath)|获取目录下所有文件(包括子目录)
listFilesInDir(File dir)|获取目录下所有文件(包括子目录)
-|
listFilesInDirWithFilter(String dirPath, String suffix, boolean isRecursive)|获取目录下所有后缀名为 suffix 的文件(忽略大小写)
listFilesInDirWithFilter(File dir, String suffix, boolean isRecursive)|获取目录下所有后缀名为 suffix 的文件(忽略大小写)
listFilesInDirWithFilter(String dirPath, String suffix)|获取目录下所有后缀名为 suffix 的文件(包括子目录, 忽略大小写)
listFilesInDirWithFilter(File dir, String suffix)|获取目录下所有后缀名为 suffix 的文件(包括子目录, 忽略大小写)
-|
listFilesInDirWithFilter(String dirPath, FilenameFilter filter, boolean isRecursive)|获取目录下所有符合 filter 的文件
listFilesInDirWithFilter(File dir, FilenameFilter filter, boolean isRecursive)|获取目录下所有符合 filter 的文件
listFilesInDirWithFilter(String dirPath, FilenameFilter filter)|获取目录下所有符合 filter 的文件(包括子目录)
listFilesInDirWithFilter(File dir, FilenameFilter filter)|获取目录下所有符合 filter 的文件(包括子目录)
-|
writeFileFromIS(String filePath, InputStream is, boolean append)|将输入流写入文件
writeFileFromIS(File file, InputStream is, boolean append)|将输入流写入文件
writeFileFromString(String filePath, String content, boolean append)|将字符串写入文件
writeFileFromString(File file, String content, boolean append)|将字符串写入文件
-|
readFile2List(String filePath, String charsetName)|指定编码按行读取文件到链表中
readFile2List(File file, String charsetName)|指定编码按行读取文件到链表中
readFile2List(String filePath, int startLine, int endLine, String charsetName)|指定编码按行读取文件到链表中
readFile2List(File file, int startLine, int endLine, String charsetName)|指定编码按行读取文件到链表中
readFile2String(String filePath, String charsetName)|指定编码按行读取文件到字符串中
readFile2String(File file, String charsetName)|指定编码按行读取文件到字符串中
readFile2Bytes(String filePath)|读取文件到字符数组中
readFile2Bytes(File file)|读取文件到字符数组中
-|
getFileLastModified(String filePath)|获取文件最后修改的毫秒时间戳
getFileLastModified(File file)|获取文件最后修改的毫秒时间戳
getFileCharsetSimple(String filePath)|简单获取文件编码格式(当无法识别编码时, 默认会返回GBK)
getFileCharsetSimple(File file)|简单获取文件编码格式(当无法识别编码时, 默认会返回GBK)
-|
getFileLines(String filePath)|获取文件行数
getFileLines(File file)|获取文件行数
getDirSize(String dirPath)|获取目录大小
getDirSize(File dir)|获取目录大小
getFileSize(String filePath)|获取文件大小
getFileSize(File file)|获取文件大小
getDirLength(String dirPath)|获取目录长度. 当目录不存在时, 返回-1
getDirLength(File dir)|获取目录长度. 当目录不存在时, 返回-1
getFileLength(String filePath)|获取文件长度. 当文件不存在时, 返回-1
getFileLength(File file)|获取文件长度. 当文件不存在时, 返回-1
-|
getFileMD5ToString(String filePath)|获取文件的MD5校验码
getFileMD5ToString(File file)|获取文件的MD5校验码
getFileMD5(String filePath)|获取文件的MD5校验码
getFileMD5(File file)|获取文件的MD5校验码
-|
getDirName(File file)|获取全路径中的最长目录
getDirName(String filePath)|获取全路径中的最长目录
getFileName(File file)|获取全路径中的文件名
getFileName(String filePath)|获取全路径中的文件名
getFileNameWithoutExtension(File file)|获取全路径中的不带拓展名的文件名
getFileNameWithoutExtension(String filePath)|获取全路径中的不带拓展名的文件名
getFileExtension(File file)|获取全路径中的文件拓展名
getFileExtension(String filePath)|获取全路径中的文件拓展名

### Handler Utils
类/接口|描述
---|---
OnReceiveMessageListener|接收消息回调接口
HandlerHolder|Handler封闭类(避免GC问题)

### Ime Utils
方法签名|描述
---|---
hideIme(Activity activity)|动态隐藏软键盘
showIme(Context context, EditText editText)|动态显示软键盘
toggleSoftInput(Context context)|切换软键盘显示与否状态
isImeShowing(Context context)|检测软键盘是否已显示

### Intent Utils
方法签名|描述
---|---
getInstallAppIntent(String filePath)|获取安装App的意图(支持6.0)
getInstallAppIntent(File file)|获取安装App的意图(支持6.0)
getUninstallAppIntent(String packageName)|获取卸载App的Intent
-|
getLaunchAppIntent(String packageName)|获取打开App的Intent
getAppDetailsSettingsIntent(String packageName)|获取App具体设置的Intent
-|
getShareTextIntent(String content)|获取分享文本的Intent
getShareImageIntent(String content, String imagePath)|获取分享图片的Intent
getShareImageIntent(String content, File imageFile)|获取分享图片的Intent
getShareImageIntent(String content, Uri uri)|获取分享图片的Intent
-|
getComponentIntent(String packageName, String className)|获取其它应用组件的Intent
getComponentIntent(String packageName, String className, Bundle bundle)|获取其它应用组件的Intent
-|
getShutdownIntent()|获取关机的Intent(需添加权限 \<uses-permission android:name="android.permission.SHUTDOWN"/>)
getHomeCategoryIntent()|获取返回系统主界面的Intent
getOpenURLIntent(String url)|获取打开URL的Intent
-|
getDialIntent(String phoneNumber)|获取跳转至拨号界面的Intent
getCallIntent(String phoneNumber)|获取拨打电话的Intent(需添加权限 \<uses-permission android:name="android.permission.CALL_PHONE"/>)
getSendSmsIntent(String phoneNumber, String content)|获取跳转至发送短信界面的Intent
getSendEmailIntent(String address, String subject, String text)|获取跳转至发送邮件界面的Intent
-|
getCaptureIntent(Uri outputUri)|获取拍照的Intent
getPickImageIntent()|获取选择系统图库中图片的Intent
getPickVideoIntent()|获取选择系统媒体库中视频的Intent
getCropImageIntent(Uri inputUri, Uri outputUri, int aspectX, int aspectY, int outputX, int outputY)|获取裁剪图片的Intent

### Log Utils
方法签名|描述
---|---
v(String tag, String msg)|输出Verbose日志消息
d(String tag, String msg)|输出Debug日志消息
i(String tag, String msg)|输出Info日志消息
w(String tag, String msg)|输出Warn日志消息
e(String tag, String msg)|输出Error日志消息
-|
v(Object object, String msg)|输出Verbose日志消息
d(Object object, String msg)|输出Debug日志消息
i(Object object, String msg)|输出Info日志消息
w(Object object, String msg)|输出Warn日志消息
e(Object object, String msg)|输出Error日志消息

### Network Utils
方法签名|描述
---|---
openWirelessSettings()|打开网络设置界面
-|
isConnected()|判断网络是否连接
isAvailableByPing()|判断网络是否可用(通过Ping的检测方式)
-|
isDataEnabled()|判断移动数据是否打开
setDataEnabled(boolean enabled)|打开/关闭移动数据
is4G()|判断网络是否是4G
-|
isWifiEnabled()|判断wifi是否打开
setWifiEnabled(boolean enabled)|打开/关闭wifi
isWifiConnected()|判断wifi是否连接状态
isWifiAvailable()|判断wifi数据是否可用
-|
getNetworkOperatorName()|获取网络运营商名称
getNetworkType()|获取当前网络类型
getIPAddress(boolean useIPv4)|获取IP地址
getDomainAddress(final String domain)|获取域名的IP地址

### Process Utils
方法签名|描述
---|---
getAllBackgroundProcesses()|获取后台服务进程
killAllBackgroundProcesses()|杀死所有的后台服务进程
killBackgroundProcesses(String packageName)|杀死后台服务进程

### SDCard Utils
方法签名|描述
---|---
isSDCardEnable()|判断SD卡是否可用
-|
getSDCardPath()|获取SD卡路径
getDataPath()|获取SD卡的data路径
getFreeSpace()|获取SD卡剩余空间
getSDCardInfo()|获取SD卡信息

### Service Utils
方法签名|描述
---|---
startService(Context context, String className)|启动服务
startService(Context context, Class<?> cls)|启动服务
-|
stopService(Context context, String className)|停止服务
stopService(Context context, Class<?> cls)|停止服务
-|
bindService(Context context, String className, ServiceConnection conn, int flags)|绑定服务
bindService(Context context, Class<?> cls, ServiceConnection conn, int flags)|绑定服务
-|
unbindService(Context context, ServiceConnection conn)|解绑服务
-|
getAllRunningService(Context context)|获取所有运行的服务
isServiceRunning(Context context, String className)|判断服务是否运行

### Shell Utils
方法签名|描述
---|---
execCmd(String command, boolean isNeedRoot)|执行命令
execCmd(List\<String> commands, boolean isNeedRoot)|执行命令
execCmd(String[] commands, boolean isNeedRoot)|执行命令
execCmd(String command, boolean isNeedRoot, boolean isNeedResult)|执行命令
execCmd(List\<String> commands, boolean isNeedRoot, boolean isNeedResult)|执行命令
execCmd(String[] commands, boolean isNeedRoot, boolean isNeedResult)|执行命令

### Size Utils
方法签名|描述
---|---
dp2px(float dpValue)|dp 转 px
px2dp(float pxValue)|px 转 dp
sp2px(float spValue)|sp 转 px
px2sp(float pxValue)|px 转 sp
-|
forceGetViewSize(final View view, final onGetSizeListener listener)|在onCreate中获取视图的尺寸
measureView(View view)|测量视图尺寸
getMeasureWidth(View view)|获取测量视图宽度
getMeasureHeight(View view)|获取测量视图高度

### SP Utils
方法签名|描述
---|---
putString(String key, String value)|写入String类型值
getString(String key)|读取String类型值
getString(String key, String defValue)|读取String类型值
-|
putInt(String key, int value)|写入int类型值
getInt(String key)|读取int类型值
getInt(String key, int defValue)|读取int类型值
-|
putLong(String key, long value)|写入long类型值
getLong(String key)|读取long类型值
getLong(String key, long defValue)|读取long类型值
-|
putFloat(String key, float value)|写入float类型值
getFloat(String key)|读取float类型值
getFloat(String key, float defValue)|读取float类型值
-|
putBoolean(String key, boolean value)|写入boolean类型值
getBoolean(String key)|读取boolean类型值
getBoolean(String key, boolean defValue)|读取boolean类型值
-|
getAll()|获取SP中的所有键值对
contains(String key)|判断SP中是否存在该key
remove(String key)|移除SP中的键key
clear()|清除SP中所有数据

### String Utils
方法签名|描述
---|---
isEmpty(CharSequence s)|判断字符串是否为 null 或长度为 0
isSpace(String s)|判断字符串是否为 null 或全为空格
-|
equals(CharSequence s1, CharSequence s2)|判断两字符串是否相等
equalsIgnoreCase(String s1, String s2)|判断两字符串忽略大小写是否相等
-|
length(CharSequence s)|返回字符串的长度
-|
null2Length0(String s)|null 转为长度为 0 的字符串
upperFirstLetter(String s)|字符串首字母转大写
lowerFirstLetter(String s)|字符串首字母转小写
reverse(String s)|反转字符串
toDBC(String s)|转化为半角字符
toSBC(String s)|转化为全角字符

### Thread Utils
方法签名|描述
---|---
safeSleep(long time)|安全地进行休眠(方法内部会捕获异常)

### Vibrator Utils
方法签名|描述
---|---
vibrate(Context context, long milliseconds)|振动
vibrate(Context context, long[] pattern, int repeat)|指定以pattern的模式振动
cancel(Context context)|取消振动

### View Utils
方法签名|描述
---|---
getItem(ViewPager viewPager, int index)|根据index索引值获取viewPager中的Fragment对象
setMargins(View view, int left, int top, int right, int bottom)|设置视图控件的margin值
setListViewHeightBasedOnChildren(ListView listview)|根据子View的内容设置ListView的高度
-|
showAnimationBaseOnChildView(ViewGroup viewGroup, Animation animation)|对ViewGroup中的子视图设置动画
showView(View view, Animation animation)|设置视图出现动画
hiddenView(View view, Animation animation)|设置视图隐藏动画
dismissView(View view, Animation animation)|设置视图消失动画
-|
scrollToTopDelayed(final ScrollView scrollView)|延迟滚动视图到顶部
scrollToTopDelayed(final ScrollView scrollView, long delayMillis)|根据延迟时间delayMillis滚动视图到顶部
scrollToBottomDelayed(final ScrollView scrollView)|延迟滚动视图到底部
scrollToBottomDelayed(final ScrollView scrollView, long delayMillis)|根据延迟时间delayMillis滚动视图到底部
