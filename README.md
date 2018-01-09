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
-|
memorySize2Byte(long memorySize, ConstUtils.MemoryUnit unit)|以unit为单位的内存大小, 转换为字节数
byte2MemorySize(long byteSize, ConstUtils.MemoryUnit unit)|字节数转换为以unit为单位的内存大小
byte2FitMemorySize(long byteSize)|字节数转换为合适显示的内存大小字符串

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

### Intent Utils
方法签名|描述
---|---
getUninstallAppIntent(String packageName)|获取卸载App的Intent
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
