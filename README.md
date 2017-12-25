## Some Useful Utils Code for Android Develop

### Activity Utils
```java
isActivityExists        判断是否存在某Activity
launchActivity          打开Activity

getLauncherActivity     获取某包下的Launcher Activity
getTopActivity          获取栈顶Activity
```

### Animation Utils
```java
inFromTopAnimation      从顶部进入动画
inFromBottomAnimation   从底部进入动画
inFromLeftAnimation     从左边进入动画
inFromRightAnimation    从右边进入动画
inFromCenterAnimation   从中部进入动画

outToTopAnimation       从顶部退出动画
outToBottomAnimation    从底部退出动画
outToLeftAnimation      从左边退出动画
outToRightAnimation     从右边退出动画
outToCenterAnimation    从中部退出动画

shakeAnimation          抖动动画
```

### Clipboard Utils
```java
copyText                复制文本到剪贴板
getText                 获取剪贴板的文本

copyUri                 复制Uri到剪贴板
getUri                  获取剪贴板的Uri

copyIntent              复制Intent到剪贴板
getIntent               获取剪贴板的Intent
```

### Close Utils
```java
closeIO                 关闭IO
closeIOQuietly          安静的关闭IO
```

### Empty Utils
```java
isEmpty                 判断对象是否为空
isNotEmpty              判断对象是否非空
```

### Encode Utils
```java
urlEncode               URL编码
urlDecode               URL解码

base64Encode            Base64编码
base64Encode2String     Base64编码为字符串
base64Decode            Base64解码
base64UrlSafeEncode     Base64 URL安全编码

htmlEncode              Html编码
htmlDecode              Html解码
```

### File Utils
```java
getFileByPath           根据文件路径获取文件

isFileExists            判断文件是否存在
```

### Intent Utils
```java
getUninstallAppIntent       获取卸载App的Intent
getLaunchAppIntent          获取打开App的Intent
getAppDetailsSettingsIntent 获取App具体设置的Intent

getShareTextIntent          获取分享文本的Intent
getShareImageIntent         获取分享图片的Intent

getComponentIntent          获取其它应用组件的Intent
getShutdownIntent           获取关机的Intent
getHomeCategoryIntent       获取返回系统主界面的Intent
getOpenURLIntent            获取打开URL的Intent

getDialIntent               获取跳转至拨号界面的Intent
getCallIntent               获取拨打电话的Intent
getSendSmsIntent            获取跳转至发送短信界面的Intent
getSendEmailIntent          获取跳转至发送邮件界面的Intent

getCaptureIntent            获取拍照的Intent
getPickImageIntent          获取选择系统图库中图片的Intent
getPickVideoIntent          获取选择系统媒体库中视频的Intent
getCropImageIntent          获取裁剪图片的Intent
```

### String Utils
```java
isEmpty                 判断字符串是否为null或长度为0
isSpace                 判断字符串是否为null或全都为空格

equals                  判断两字符串是否相等
equalsIgnoreCase        判断两字符串忽略大小写是否相等
length                  返回字符串的长度

null2Length0            null转为长度为0的字符串
upperFirstLetter        字符串首字母转大写
lowerFirstLetter        字符串首字母转小写
reverse                 反转字符串
toDBC                   转化为半角字符
toSBC                   转化为全角字符
```
