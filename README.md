## Some Useful Utils Code for Android Develop

### Activity Utils
```java
isActivityExists        判断是否存在某 Activity
launchActivity          打开 Activity

getLauncherActivity     获取某包下的 Launcher Activity
getTopActivity          获取栈顶 Activity
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

copyUri                 复制 Uri 到剪贴板
getUri                  获取剪贴板的 Uri

copyIntent              复制 Intent 到剪贴板
getIntent               获取剪贴板的 Intent
```

### Close Utils
```java
closeIO                 关闭 IO
closeIOQuietly          安静的关闭 IO
```

### Empty Utils
```java
isEmpty                 判断对象是否为空
isNotEmpty              判断对象是否非空
```

### Encode Utils
```java
urlEncode               URL 编码
urlDecode               URL 解码

base64Encode            Base64 编码
base64Encode2String     Base64 编码为字符串
base64Decode            Base64 解码
base64UrlSafeEncode     Base64 URL 安全编码

htmlEncode              Html 编码
htmlDecode              Html 解码
```

### Intent Utils
```java
getComponentIntent      获取其它应用组件的意图
```

### String Utils
```java
isEmpty                 判断字符串是否为 null 或长度为 0
isSpace                 判断字符串是否为 null 或全为空格

equals                  判断两字符串是否相等
equalsIgnoreCase        判断两字符串忽略大小写是否相等
length                  返回字符串的长度

null2Length0            null 转为长度为 0 的字符串
upperFirstLetter        字符串首字母转大写
lowerFirstLetter        字符串首字母转小写
reverse                 反转字符串
toDBC                   转化为半角字符
toSBC                   转化为全角字符
```
